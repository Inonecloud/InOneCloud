package me.inonecloud.domain;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "tokens")
public class TokenEntity extends BaseEntity {
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

    public boolean isExpired(){
        if(expiresIn == null){
            return false;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(this.getCreatedAt());
        calendar.add(Calendar.SECOND, this.getExpiresIn().intValue());
        return calendar.getTime().before(new Date());
    }

    public boolean containsTypeOfStorage(CloudStorage tokenType){
        return tokenType.equals(this.cloudStorage);
    }
}
