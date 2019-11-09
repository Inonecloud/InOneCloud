package me.inonecloud.repository;

import me.inonecloud.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByUsername(String username);

    User findByEmail(String email);

    @EntityGraph(attributePaths = "authorities")
    User findOneWithAuthoritiesByUsername(String username);
}