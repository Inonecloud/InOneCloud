package me.inonecloud.clouds;

import me.inonecloud.clouds.dto.dropbox.DropboxAccessToken;
import me.inonecloud.clouds.dto.dropbox.SpaceInfo;
import me.inonecloud.repository.DropboxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class DropboxIntegrationAPI implements DropboxRepository {
    @Value("${dropbox.app.key}")
    private String CLIENT_ID;
    @Value("${dropbox.app.secret}")
    private String CLIENT_SECRET;
    @Value("${dropbox.redirect.url}")
    private String REDIRECT_URL;
    private String emptyBody = "null";

    private RestTemplate restTemplate;

    public ResponseEntity<DropboxAccessToken> getToken(String code){
        restTemplate = new RestTemplate();
        String resourceUrl = "https://api.dropboxapi.com/oauth2/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("redirect_uri", REDIRECT_URL);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, httpHeaders);
        return restTemplate.postForEntity(resourceUrl, request, DropboxAccessToken.class);
    }

    public ResponseEntity<SpaceInfo> getSpaceUsage(String token){
        restTemplate = new RestTemplate();
        String resourceUrl = "https://api.dropboxapi.com/2/users/get_space_usage";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(token);
        HttpEntity httpEntity = new HttpEntity(emptyBody, httpHeaders);
        return restTemplate.exchange(resourceUrl, HttpMethod.POST, httpEntity, SpaceInfo.class);
    }


}
