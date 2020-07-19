package me.inonecloud.service;

import me.inonecloud.controller.exceptions.InvalidPasswordException;
import me.inonecloud.controller.exceptions.UserAlreadyExistException;
import me.inonecloud.domain.User;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.security.SecurityUtils;
import me.inonecloud.service.dto.UserDto;
import me.inonecloud.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepo, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User signUp(UserDto userDto, String password) {
        if (userRepo.findByEmail(userDto.getEmail()) != null || userRepo.findByUsername(userDto.getUsername()).isPresent()) {
            throw new UserAlreadyExistException();
        }

        User newUser = userMapper.toEntity(userDto);
        newUser.setPasswordHash(passwordEncoder.encode(password));

        userRepo.save(newUser);
        return newUser;
    }

    @Transactional
    public void changePassword(String oldPassword, String newPassword){
        SecurityUtils.getCurrentUserLogin()
                .flatMap(userRepo::findByUsername)
                .ifPresent(user -> {
                    String currentEncryptedPassword = user.getPasswordHash();
                    if(!passwordEncoder.matches(oldPassword, currentEncryptedPassword)){
                        throw new InvalidPasswordException();
                    }
                    user.setPasswordHash(passwordEncoder.encode(newPassword));
                    userRepo.save(user);
                });
    }
}
