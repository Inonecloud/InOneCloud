package me.inonecloud.service.mapper;

import me.inonecloud.clouds.dto.dropbox.Entries;
import me.inonecloud.clouds.dto.google.GoogleFile;
import me.inonecloud.domain.CloudStorage;
import me.inonecloud.service.dto.FilesListDto;
import me.inonecloud.service.dto.Item;
import me.inonecloud.service.dto.Type;
import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FilesListMapper {

    public FilesListDto yandexFilesToInOneCloudFiles(List<me.inonecloud.clouds.dto.yandex.Item> yandexItems) {
        return new FilesListDto(yandexItems.stream()
                .map(this::yandexItemToInOneCloudItem)
                .collect(Collectors.toList()));
    }

    public FilesListDto dropboxFilesToInOneCloudFiles(List<Entries> dropboxEntries) {
        return new FilesListDto(dropboxEntries.stream()
                .map(this::dropboxEntriesToInOneCloudItem)
                .collect(Collectors.toList()));
    }

    public FilesListDto googleFilesToInOneCloudFiles(List<GoogleFile> googleFiles) {
        return new FilesListDto(googleFiles.stream()
                .map(this::googleFileToInOneClodItem)
                .collect(Collectors.toList()));
    }

    private Item yandexItemToInOneCloudItem(me.inonecloud.clouds.dto.yandex.Item yandexItem) {
        var item = new Item();
        item.setName(yandexItem.getName());
        item.setCreated(Date.from(ZonedDateTime.parse(yandexItem.getCreated()).toInstant()));
        item.setModified(Date.from(ZonedDateTime.parse(yandexItem.getModified()).toInstant()));
        if (yandexItem.getType().equals("dir")) {
            item.setType(Type.FOLDER);
        } else {
            item.setType(Type.FIlE);
        }
        item.setPath(yandexItem.getPath());
        item.setSize(yandexItem.getSize());
        item.setSource(CloudStorage.YANDEX_DISK);
        return item;
    }

    private Item dropboxEntriesToInOneCloudItem(Entries entries) {
        var item = new Item();
        item.setId(entries.getId());
        item.setName(entries.getName());
        if (entries.getTag().equals("folder")) {
            item.setType(Type.FOLDER);
        } else {
            item.setType(Type.FIlE);
            item.setModified(Date.from(ZonedDateTime.parse(entries.getClientModified()).toInstant()));
            item.setSize(Long.parseLong(entries.getSize()));
        }
        item.setPath(entries.getPathLower());
        item.setSource(CloudStorage.DROPBOX);
        return item;
    }

    private Item googleFileToInOneClodItem(GoogleFile googleFile) {
        var item = new Item();
        item.setId(googleFile.getId());
        item.setName(googleFile.getName());
        item.setCreated(Date.from(ZonedDateTime.parse(googleFile.getCreatedTime()).toInstant()));
        item.setModified(Date.from(ZonedDateTime.parse(googleFile.getModifiedTime()).toInstant()));
        item.setPath(googleFile.getOriginalFileName());
        item.setSize(Long.parseLong(googleFile.getSize()));
        item.setSource(CloudStorage.GOOGLE_DRIVE);

        return item;
    }


}