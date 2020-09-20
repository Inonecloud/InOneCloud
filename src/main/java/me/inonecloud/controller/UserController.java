package me.inonecloud.controller;

import me.inonecloud.controller.exceptions.InvalidPasswordException;
import me.inonecloud.controller.util.ManagedUser;
import me.inonecloud.domain.User;
import me.inonecloud.service.UserService;
import me.inonecloud.service.dto.PasswordChangeDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }



    @PostMapping("/signUp")
    @ResponseStatus(HttpStatus.CREATED)
    public void signUp(@Valid @RequestBody ManagedUser managedUser){
        if(!passwordChecker(managedUser.getPassword())){
            throw new InvalidPasswordException();
        }
        User user = userService.signUp(managedUser, managedUser.getPassword());
    }

    @PostMapping("/changePassword")
    @ResponseStatus(HttpStatus.OK)
    public void changPassword(@Valid @RequestBody PasswordChangeDto passwordChangeDto){
        if(!passwordChecker(passwordChangeDto.getNewPassword())){
            throw new InvalidPasswordException();
        }
        userService.changePassword(passwordChangeDto.getCurrentPassword(), passwordChangeDto.getNewPassword());
    }

    private boolean passwordChecker(String password) {
        return password.length() >= ManagedUser.PASSWORD_MIN_LENGTH &&
                password.length() <= ManagedUser.PASSWORD_MAX_LENGTH;
    }
}
