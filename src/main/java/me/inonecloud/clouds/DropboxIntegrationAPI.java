package me.inonecloud.clouds;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.inonecloud.clouds.dto.dropbox.*;
import me.inonecloud.repository.DropboxRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class DropboxIntegrationAPI implements DropboxRepository {
    @Value("${dropbox.app.key}")
    private String CLIENT_ID;
    @Value("${dropbox.app.secret}")
    private String CLIENT_SECRET;
    @Value("${dropbox.redirect.url}")
    private String REDIRECT_URL;
    private final String emptyBody = "null";
    private final URI baseURI;

    private final RestTemplate restTemplate;

    public DropboxIntegrationAPI(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
        this.baseURI = URI.create("https://api.dropboxapi.com/");
    }

    public ResponseEntity<DropboxAccessToken> getToken(String code) {
        URI resourceURI = URI.create(baseURI + "oauth2/token");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("client_id", CLIENT_ID);
        formData.add("client_secret", CLIENT_SECRET);
        formData.add("redirect_uri", REDIRECT_URL);
        var request = new HttpEntity<MultiValueMap<String, String>>(formData, httpHeaders);
        return restTemplate.postForEntity(resourceURI, request, DropboxAccessToken.class);
    }

    public ResponseEntity<SpaceInfo> getSpaceUsage(String token) {
        URI resourceURI = URI.create(baseURI + "2/users/get_space_usage");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(token);
        var httpEntity = new HttpEntity<>(emptyBody, httpHeaders);
        return restTemplate.exchange(resourceURI, HttpMethod.POST, httpEntity, SpaceInfo.class);
    }

    public ResponseEntity<GetFilesDropboxRs> getFiles(String token) {
        URI resourceURI = URI.create(baseURI + "2/files/list_folder");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(token);
        var request = new HttpEntity<>(new GetFilesDropboxRq("", true), httpHeaders);
        return restTemplate.exchange(resourceURI, HttpMethod.POST, request, GetFilesDropboxRs.class); //FixMe change Object class to DTO
    }

    public ResponseEntity<GetFilesDropboxRs> getFilesNext(String token, String cursor) throws JsonProcessingException {
        URI resourceURI = URI.create(baseURI + "2/files/list_folder/continue");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setBearerAuth(token);
        String body = "{\"cursor\" : \"" + cursor + "\"}";
        var request = new HttpEntity<>(new ObjectMapper().readTree(body), httpHeaders);
        return restTemplate.exchange(resourceURI, HttpMethod.POST, request, GetFilesDropboxRs.class);
    }

    public ResponseEntity<DownloadFileDropboxRs> downloadFile(String token, String path) {
        URI resourceURI = URI.create(baseURI + "2/files/get_temporary_link");
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setBearerAuth(token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);

        var request = new HttpEntity<>(new DownloadFileDropboxRq(path), httpHeaders);

        return restTemplate.exchange(resourceURI, HttpMethod.POST, request, DownloadFileDropboxRs.class);
    }

}
