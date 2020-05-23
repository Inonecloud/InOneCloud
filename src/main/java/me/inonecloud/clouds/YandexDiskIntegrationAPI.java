package me.inonecloud.clouds;

import me.inonecloud.clouds.dto.yandex.YandexAboutDisk;
import me.inonecloud.clouds.dto.yandex.YandexAccessToken;
import me.inonecloud.clouds.dto.yandex.YandexFilesList;
import me.inonecloud.repository.YandexRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
public class YandexDiskIntegrationAPI implements YandexRepository {
    @Value("${yandex.client.id}")
    private String CLIENT_ID;
    @Value("${yandex.client.secret}")
    private String CLIENT_SECRET;
    private RestTemplate restTemplate;

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

    public ResponseEntity<YandexAboutDisk> getStorageSpace(String token) {
        restTemplate = new RestTemplate();
        String resourceUrl = "https://cloud-api.yandex.net/v1/disk/";
        HttpEntity httpEntity = new HttpEntity(addHttpHeaders(token));
        return restTemplate.exchange(resourceUrl, HttpMethod.GET, httpEntity, YandexAboutDisk.class);
    }

    public ResponseEntity<YandexFilesList> getFiles(String token){
        restTemplate = new RestTemplate();
        String resourceUrl = "https://cloud-api.yandex.net/v1/disk/resources/files?" +
                "fields=name,preview,created,modified,path,md5,type,mime_type,size" +
                "&preview_size=XL";
        return new RestTemplate().exchange(resourceUrl, HttpMethod.GET, new HttpEntity<>(addHttpHeaders(token)), YandexFilesList.class);
    }

    private HttpHeaders addHttpHeaders(String token){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("Authorization", "OAuth " + token);
        return httpHeaders;
    }
}
