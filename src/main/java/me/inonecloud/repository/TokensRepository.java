package me.inonecloud.repository;

import me.inonecloud.domain.CloudStorage;
import me.inonecloud.domain.TokenEntity;
import me.inonecloud.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokensRepository extends JpaRepository<TokenEntity, Long> {
    TokenEntity findTokenEntitiesByUserAndCloudStorage(User user, CloudStorage cloudStorage);
    List<TokenEntity> findTokenEntitiesByUser(User user);
}
