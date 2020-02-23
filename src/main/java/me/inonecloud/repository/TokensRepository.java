package me.inonecloud.repository;

import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokensRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findTokenEntitiesByUserAndCloudStorage(User user, CloudStorage cloudStorage);
    List<TokenEntity> findTokenEntitiesByUser(User user);
}
