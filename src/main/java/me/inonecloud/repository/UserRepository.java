package me.inonecloud.repository;

import me.inonecloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the User entity
 *
 * @Author Andrew Yelmanov
 * Created 02.02.2018
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);

}
