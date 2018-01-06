package me.inonecloud.service;

import me.inonecloud.entity.User;

/**
 * Service class for {@link me.inonecloud.entity.User}
 *
 * @author Andrew Yelmanov
 * created 06.01.2018
 */

public interface UserService {

    User findByUsername(String username);
    void save(User user);

}
