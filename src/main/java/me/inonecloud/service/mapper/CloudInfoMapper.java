package me.inonecloud.service.mapper;

import me.inonecloud.clouds.dto.yandex.YandexAboutDisk;
import me.inonecloud.clouds.dto.dropbox.SpaceInfo;
import me.inonecloud.clouds.dto.google.GoogleSpaceInfo;
import me.inonecloud.service.dto.CloudInfoDto;
import org.springframework.stereotype.Service;

@Service
public class CloudInfoMapper {

    public CloudInfoDto yandexToCloudDto(YandexAboutDisk yandexDto){
        CloudInfoDto cloudInfoDto = new CloudInfoDto();
        cloudInfoDto.setTotalSpace(yandexDto.getTotalSpace());
        cloudInfoDto.setUsedSpace(yandexDto.getUsedSpace());
        cloudInfoDto.setTrashSize(yandexDto.getTrashSize());
        cloudInfoDto.setPaid(yandexDto.getPaid());
        cloudInfoDto.setUsername(yandexDto.getUser().getLogin());

        return cloudInfoDto;
    }

    public CloudInfoDto dropboxToCloudDto(SpaceInfo dropboxDto){
        CloudInfoDto cloudInfoDto = new CloudInfoDto();
        cloudInfoDto.setUsedSpace(dropboxDto.getUsed());
        cloudInfoDto.setTotalSpace(dropboxDto.getSpaceAllocation().getAllocated());
        return cloudInfoDto;
    }

    public CloudInfoDto googleDriveToCloudDto(GoogleSpaceInfo googleDto) {
        CloudInfoDto cloudInfoDto = new CloudInfoDto();
        cloudInfoDto.setTotalSpace(Long.parseLong(googleDto.getStorageQuota().getLimit()));
        cloudInfoDto.setUsedSpace(Long.parseLong(googleDto.getStorageQuota().getUsageInDrive()));
        cloudInfoDto.setTrashSize(Long.parseLong(googleDto.getStorageQuota().getUsageInDriveTrash()));
        cloudInfoDto.setUsername(googleDto.getUser().getDisplayName());
        return cloudInfoDto;
    }
}
