package me.inonecloud.security;

import me.inonecloud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class DomainUserDetailsService implements UserDetailsService {

    private final UserRepository userRepo;

    @Autowired
    public DomainUserDetailsService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String lowerCaseUsername = username.toLowerCase();
        return userRepo.findByUsername(lowerCaseUsername)
                .map(SecurityUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("User with username " + username + " was not found"));
    }
}
