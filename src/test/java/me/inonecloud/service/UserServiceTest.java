package me.inonecloud.service;

import com.github.javafaker.Faker;
import me.inonecloud.controller.exceptions.InvalidPasswordException;
import me.inonecloud.controller.exceptions.UserAlreadyExistException;
import me.inonecloud.domain.User;
import me.inonecloud.repository.UserRepository;
import me.inonecloud.service.dto.UserDto;
import me.inonecloud.service.mapper.UserMapper;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;
import org.jeasy.random.FieldPredicates;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
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
    void setUp() {
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
    @DisplayName("Sign up user test")
    void signUp() {
        String password = faker.internet().password();

        when(userRepository.findByEmail(eq(userDto.getUsername()))).thenReturn(null);
        when(userRepository.findByUsername(eq(userDto.getUsername()))).thenReturn(Optional.empty());
        when(userRepository.save(any(User.class))).thenReturn(fakeUser(userDto, password));

        User actual = userService.signUp(userDto, password);

        Assertions.assertAll(
                () -> assertNotNull(actual),
                () -> assertFalse(actual.getPasswordHash().isEmpty()),
                () -> assertEquals(userDto.getUsername(), actual.getUsername()),
                () -> assertEquals(userDto.getFirstName(), actual.getFirstName()),
                () -> assertEquals(userDto.getLastName(), actual.getLastName()),
                () -> assertEquals(userDto.getActivation(), actual.getActivation()));
    }

    @Test
    @DisplayName("User already exists sign up test")
    void signUp_userExists() {
        when(userRepository.findByEmail(eq(userDto.getEmail()))).thenReturn(fakeUser(userDto, ""));
        when(userRepository.findByUsername(userDto.getUsername())).thenReturn(null);

        Assertions.assertThrows(UserAlreadyExistException.class, () -> userService.signUp(userDto, faker.internet().password()));
    }

    @Test
    @DisplayName("Change password test")
    void changePassword() {
        User fakeUser = fakeUser(userDto, "12345678");
        when(userRepository.findByUsername(userDto.getUsername()))
                .thenReturn(Optional.of(fakeUser));
        when(userRepository.save(any(User.class))).thenReturn(fakeUser);

        userService.changePassword("12345678", "87654321");

        verify(userRepository, times(1)).findByUsername(userDto.getUsername());
        verify(userRepository, times(1)).save(fakeUser);
    }

    @Test
    @DisplayName("Invalid password exception password change test")
    void changePasswordException() {
        User fakeUser = fakeUser(userDto, "12345678");
        when(userRepository.findByUsername(userDto.getUsername()))
                .thenReturn(Optional.of(fakeUser));
        when(userRepository.save(any(User.class))).thenReturn(fakeUser);

        Assertions.assertThrows(InvalidPasswordException.class, () -> userService.changePassword("87654321", "12345678"));
    }

    @Test
    @DisplayName("Get current user test")
    void getCurrentUser() {
        User fakeUser = fakeUser(userDto, "12345678");

        when(userRepository.findByUsername(any()))
                .thenReturn(Optional.of(fakeUser));
        when(securityContext.getAuthentication())
                .thenReturn(new AnonymousAuthenticationToken("key", "r", List.of(new SimpleGrantedAuthority("admin"))));

        User currentUser = userService.getCurrentUser();

        Assertions.assertAll(
                () -> assertEquals(fakeUser.getId(), currentUser.getId()),
                () -> assertEquals(fakeUser.getUsername(), currentUser.getUsername()),
                () -> assertTrue(currentUser.getActivation()));

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