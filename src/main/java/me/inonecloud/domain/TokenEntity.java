package me.inonecloud.domain;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class TokenEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "access_token")
    private String accessToken;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "expired_in")
    private Long expiresIn;

    @Column(name = "created_at")
    private Date createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "cloud_storage")
    private CloudStorage cloudStorage;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    public TokenEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public CloudStorage getCloudStorage() {
        return cloudStorage;
    }

    public void setCloudStorage(CloudStorage cloudStorage) {
        this.cloudStorage = cloudStorage;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
