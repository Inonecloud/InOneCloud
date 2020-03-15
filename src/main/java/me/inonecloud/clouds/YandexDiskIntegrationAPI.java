package me.inonecloud.clouds;

import me.inonecloud.clouds.dto.YandexAboutDisk;
import me.inonecloud.clouds.dto.YandexAccessToken;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Component
public class YandexDiskIntegrationAPI {

    private static final String CLIENT_ID = "de30ad4f454c4e05abd736bd871a3051";
    private static final String CLIENT_SECRET = "da34ba2d50c146f48cbbda813921ef1f";
    private RestTemplate restTemplate;

    public void getUserInfo() {
        RestTemplate restTemplate = new RestTemplate();
        String resourceUrl = "https://cloud-api.yandex.net/v1/disk/";
        ResponseEntity<String> response = restTemplate.getForEntity(resourceUrl, String.class);
    }

    public ResponseEntity<YandexAccessToken> getToken(String code) {
        restTemplate = new RestTemplate();
        String resourceUrl = "https://oauth.yandex.ru/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "authorization_code");
        formData.add("code", code);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, httpHeaders);
        return restTemplate.postForEntity(resourceUrl, request, YandexAccessToken.class);
    }

    public ResponseEntity<YandexAccessToken> refreshToken(String refreshToken) {
        restTemplate = new RestTemplate();
        String resourceUrl = "https://oauth.yandex.ru/token";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        httpHeaders.setBasicAuth(CLIENT_ID, CLIENT_SECRET);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("grant_type", "refresh_token");
        formData.add("refresh_token", refreshToken);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, httpHeaders);
        return restTemplate.postForEntity(resourceUrl, request, YandexAccessToken.class);
    }

    private ResponseEntity<YandexAboutDisk> getStorageSpace(String token) {
        restTemplate = new RestTemplate();
        String resourceUrl = "https://cloud-api.yandex.net/v1/disk/";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth("OAuth " + token);
//        ResponseEntity<String> s = restTemplate.
        return null;
    }
}
