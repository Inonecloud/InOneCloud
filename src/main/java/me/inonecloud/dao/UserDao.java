package me.inonecloud.dao;

import me.inonecloud.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<User, Long> {
    User findByUsename(String username);
}
