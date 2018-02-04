package me.inonecloud.repository;

import me.inonecloud.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for {@link Authority} entity
 *
 * @Author Andrew Yelmanov
 * Created 03.02.2018
 */

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, String> {
}
