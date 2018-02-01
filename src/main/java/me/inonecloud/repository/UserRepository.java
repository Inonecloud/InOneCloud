package me.inonecloud.repository;

import me.inonecloud.domen.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the User entity
 *
 * @Author Andrew Yelmanov
 * Created 02.02.2018
 */

public interface UserRepository extends JpaRepository<User, Long> {
}
