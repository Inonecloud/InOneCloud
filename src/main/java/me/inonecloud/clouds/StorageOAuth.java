package me.inonecloud.clouds;

import me.inonecloud.service.CloudsAuthService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("storages")
public class StorageOAuth {
    private final CloudsAuthService cloudsAuthService;

    public StorageOAuth(CloudsAuthService cloudsAuthService) {
        this.cloudsAuthService = cloudsAuthService;
    }

    @GetMapping("/yandexauth")
    public void yandexAuth(@RequestParam Integer code) {
        cloudsAuthService.getCode(code);
    }
}
