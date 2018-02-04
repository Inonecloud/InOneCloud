package me.inonecloud.service;

import me.inonecloud.repository.AuthorityRepository;
import me.inonecloud.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Service class for {@link me.inonecloud.domain.User}
 *
 * @author Andrew Yelmanov
 * created 06.01.2018
 */

@Service

public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
    }
}
