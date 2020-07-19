package me.inonecloud.service;

import com.github.javafaker.Faker;
import me.inonecloud.controller.exceptions.UserAlreadyExistException;
import me.inonecloud.domain.User;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.service.dto.UserDto;
import me.inonecloud.service.mapper.UserMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class UserServiceTest {

    private final UserRepository userRepository = Mockito.mock(UserRepository.class);

    private final Authentication authentication = Mockito.mock(Authentication.class);

    private final SecurityContext securityContext = Mockito.mock(SecurityContext.class);

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(11);

    private final UserMapper userMapper = new UserMapper();

    private final UserService userService = new UserService(userRepository, userMapper, passwordEncoder);

    private final Faker faker = new Faker();

    private UserDto userDto;


    @BeforeEach
    void setUp(){
        userDto = new EasyRandom(new EasyRandomParameters()
                .randomize(FieldPredicates.named("email"), () -> faker.internet().emailAddress())
                .randomize(FieldPredicates.named("username"), () -> faker.cat().name())
                .randomize(FieldPredicates.named("firstName"), () -> faker.name().firstName())
                .randomize(FieldPredicates.named("lastName"), () -> faker.name().lastName())
        ).nextObject(UserDto.class);

        SecurityContextHolder.setContext(securityContext);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(SecurityContextHolder.getContext().getAuthentication().getPrincipal())
                .thenReturn(userDto.getUsername());
    }

    @Test
    void signUp() {
        String password = faker.internet().password();

        when(userRepository.findByEmail(eq(userDto.getUsername()))).thenReturn(null);
        when(userRepository.findByUsername(eq(userDto.getUsername()))).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(fakeUser(userDto, password));

        User actual = userService.signUp(userDto, password);

        assertThat(actual).isNotNull();
        assertThat(actual.getPasswordHash()).isNotEmpty();
        assertThat(actual.getUsername()).isEqualTo(userDto.getUsername());
        assertThat(actual.getFirstName()).isEqualTo(userDto.getFirstName());
        assertThat(actual.getLastName()).isEqualTo(userDto.getLastName());
        assertThat(actual.getActivation()).isEqualTo(userDto.getActivation());
    }

    @Test
    void signUp_userExists(){
        when(userRepository.findByEmail(eq(userDto.getEmail()))).thenReturn(fakeUser(userDto, ""));
        when(userRepository.findByUsername(userDto.getUsername())).thenReturn(null);

        UserAlreadyExistException thrown = assertThrows(UserAlreadyExistException.class, () -> userService.signUp(userDto, faker.internet().password()));

        assertThat(thrown).isNotNull();
    }

    @Test
    void changePassword(){
        User fakeUser = fakeUser(userDto, "12345678");
        when(userRepository.findByUsername(userDto.getUsername()))
                .thenReturn(Optional.of(fakeUser));
        when(userRepository.save(any(User.class))).thenReturn(fakeUser);

        userService.changePassword("12345678", "87654321");

        verify(userRepository, times(1)).findByUsername(userDto.getUsername());
        verify(userRepository, times(1)).save(fakeUser);
    }

    private User fakeUser(UserDto userDto, String password) {
        var user = new User();
        user.setId(faker.random().nextInt(100));
        user.setEmail(userDto.getEmail());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPasswordHash(new BCryptPasswordEncoder().encode(password));
        user.setActivation(userDto.getActivation());
        return user;
    }
}