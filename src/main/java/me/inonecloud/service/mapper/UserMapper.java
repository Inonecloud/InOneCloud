package me.inonecloud.service.mapper;

import me.inonecloud.domain.Authority;
import me.inonecloud.domain.User;
import me.inonecloud.service.dto.UserDTO;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper {
    public UserDTO userToUserDTO(User user){
        return new UserDTO(user);
    }

    public User userDTOToUser(UserDTO userDTO){
        if (userDTO == null){
            return null;
        } else{
            User user = new User();
            user.setId(userDTO.getId());
            user.setUsername(userDTO.getUsername());
            user.setFirstName(userDTO.getFirstName());
            user.setLastName(userDTO.getLastName());
            user.setEmail(userDTO.getEmail());
            user.setActivate(userDTO.getActivated());
            user.setLangKey(userDTO.getLangKey());
            Set<Authority> authorities =this.authoritiesFromSettings(userDTO.getAuthorities());
            if (authorities != null){
                user.setAuthorities(authorities);
            }
            return user;
        }
    }

    public Set<Authority> authoritiesFromSettings(Set<String> strings){
        return strings.stream().map(string -> {
            Authority auth = new Authority();
            auth.setName(string);
            return auth;
        }).collect(Collectors.toSet());
    }


}
