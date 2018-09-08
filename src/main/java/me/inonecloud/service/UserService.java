package me.inonecloud.service;

import me.inonecloud.domain.User;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.service.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public User signUp(UserDto userDto) {
        User user = new User();

        userRepo.save(user);
        return user;
    }
}
