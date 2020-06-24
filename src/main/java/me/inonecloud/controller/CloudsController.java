package me.inonecloud.controller;

import me.inonecloud.service.CloudsInfoService;
import me.inonecloud.service.dto.CloudsInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.security.Principal;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("api/clouds")
public class CloudsController {
    private final CloudsInfoService cloudsInfoService;

    public CloudsController(CloudsInfoService cloudsInfoService) {
        this.cloudsInfoService = cloudsInfoService;
    }

    @GetMapping("/getInfo")
    public ResponseEntity<CloudsInfoDto> getCloudsInfo(Principal principal) {
            CloudsInfoDto cloudsInfo = cloudsInfoService.getCloudsInfo(principal.getName());
            return new ResponseEntity<>(cloudsInfo, HttpStatus.OK);
    }

    @GetMapping("/checkTokens")
    @ResponseStatus(HttpStatus.FOUND)
    public void checkTokens(Principal principal) {
        Boolean exists = cloudsInfoService.checkTokens(principal.getName());
        if (!exists) {
            throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
        }
    }
}
