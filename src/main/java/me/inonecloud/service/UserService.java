package me.inonecloud.service;

import me.inonecloud.controller.exceptions.UserAlreadyExistException;
import me.inonecloud.domain.User;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.service.dto.UserDto;
import me.inonecloud.service.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepo, UserMapper userMapper) {
        this.userRepo = userRepo;
        this.userMapper = userMapper;
    }

    public User signUp(UserDto userDto, String password) {
        if (userRepo.findByEmail(userDto.getEmail()) != null && userRepo.findByUsername(userDto.getUsername()) != null) {
            throw new UserAlreadyExistException();
        }

        User newUser = userMapper.toEntity(userDto);
        newUser.setPasswordHash(new BCryptPasswordEncoder().encode(password));

        userRepo.save(newUser);
        return newUser;
    }
}
