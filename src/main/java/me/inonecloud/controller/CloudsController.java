package me.inonecloud.controller;

import me.inonecloud.service.CloudsInfoService;
import me.inonecloud.service.dto.CloudsInfoDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

@CrossOrigin(origins = {"http://localhost:3000"})
@RestController
@RequestMapping("api/clouds")
public class CloudsController {
    private CloudsInfoService cloudsInfoService;

    public CloudsController(CloudsInfoService cloudsInfoService) {
        this.cloudsInfoService = cloudsInfoService;
    }

    @GetMapping("/getInfo")
    public ResponseEntity<CloudsInfoDto> getCloudsInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            CloudsInfoDto cloudsInfo = cloudsInfoService.getCloudsInfo(authentication.getName());
            return new ResponseEntity<>(cloudsInfo, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/checkTokens")
    @ResponseStatus(HttpStatus.FOUND)
    public void checkTokens(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)){
            Boolean exists = cloudsInfoService.checkTockens(authentication.getName());
            if(!exists){
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
            }
        }

    }
}
