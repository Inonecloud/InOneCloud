package me.inonecloud.repository;

import me.inonecloud.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Integer> {
}
