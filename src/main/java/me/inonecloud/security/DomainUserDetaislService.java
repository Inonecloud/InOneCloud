package me.inonecloud.security;

import me.inonecloud.domain.User;
import me.inonecloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DomainUserDetaislService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public DomainUserDetaislService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowerCaseUsername = username.toLowerCase();
        Optional<User> user = userRepo.findByUsername(lowerCaseUsername);

        return user.map(user1 -> new SecurityUserDetails(user1))
                .orElseThrow(() -> new UsernameNotFoundException("User with " + username + " username not found"));
    }
}
