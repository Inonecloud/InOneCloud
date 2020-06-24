package me.inonecloud.clouds;

import me.inonecloud.clouds.dto.google.GetFilesGoogleDrive;
import me.inonecloud.clouds.dto.google.GoogleAccessToken;
import me.inonecloud.clouds.dto.google.GoogleSpaceInfo;
import me.inonecloud.repository.GoogleDriveRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.Collections;

@Component
public class GoogleDriveIntegrationAPI implements GoogleDriveRepository {
    @Value("${google.client.id}")
    private String CLIENT_ID;
    @Value("${google.client.secret}")
    private String CLIENT_SECRET;
    @Value("${google.redirect.url}")
    private String REDIRECT_URL;

    private final URI baseURI;

    private final RestTemplate restTemplate;

    public GoogleDriveIntegrationAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.baseURI = URI.create("https://www.googleapis.com/drive/v3/");
    }

    @Override
    public ResponseEntity<GoogleAccessToken> getToken(String code) {
        URI resourceURL = URI.create("https://oauth2.googleapis.com/token");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", REDIRECT_URL);
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, httpHeaders);
        return restTemplate.postForEntity(resourceURL, request, GoogleAccessToken.class);
    }

    @Override
    public ResponseEntity<GoogleAccessToken> refreshToken(String refreshToken) {
        URI resourceURL = URI.create("https://oauth2.googleapis.com/token");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("refresh_token", refreshToken);
        formData.add("grant_type", "refresh_token");
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(formData, httpHeaders);
        return restTemplate.postForEntity(resourceURL, request, GoogleAccessToken.class);
    }

    @Override
    public ResponseEntity<GoogleSpaceInfo> getStorageSpace(String token) {
        URI resourceURI = URI.create(baseURI + "about?fields=storageQuota,user&key=" + CLIENT_ID);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setBearerAuth(token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return restTemplate.exchange(resourceURI, HttpMethod.GET, httpEntity, GoogleSpaceInfo.class);
    }

    public ResponseEntity<GetFilesGoogleDrive> getFiles(String token) {
        URI resourceURI = URI.create(baseURI + "files?spaces=drive&fields=kind,nextPageToken,files,incompleteSearch&key=" + CLIENT_ID);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        httpHeaders.setBearerAuth(token);
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        return restTemplate.exchange(resourceURI, HttpMethod.GET, httpEntity, GetFilesGoogleDrive.class);
    }

    //https://accounts.google.com/o/oauth2/v2/auth?scope=https%3A//www.googleapis.com/auth/drive&access_type=offline&include_granted_scopes=true&response_type=code&state=state_parameter_passthrough_value&redirect_uri=http%3A//localhost:3000/google/auth&client_id=135842521742-l0793cjhtc3k2sh5gng2q34r3i8iv13h.apps.googleusercontent.com
}
