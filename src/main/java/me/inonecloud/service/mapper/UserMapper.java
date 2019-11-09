package me.inonecloud.service.mapper;

import me.inonecloud.domain.Authority;
import me.inonecloud.domain.User;
import me.inonecloud.service.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserMapper implements EntityMapper<UserDto, User> {
    @Override
    public User toEntity(UserDto dto) {
        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setActivation(dto.getActivation());
        return user;
    }

    @Override
    public UserDto toDto(User entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> toEntity(List<UserDto> dtoList) {
        return dtoList.stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> toDto(List<User> entityList) {
        throw new UnsupportedOperationException();
    }


    private Set<Authority> authoritiesFromStrings(Set<String> authoritiesAsStrings) {
        Set<Authority> authorities = new HashSet<>();

        if (authoritiesAsStrings != null) {
            authorities = authoritiesAsStrings.stream()
                    .map(string -> {
                        Authority auth = new Authority();
                        auth.setName(string);
                        return auth;
                    })
                    .collect(Collectors.toSet());
        }

        return authorities;
    }
}
