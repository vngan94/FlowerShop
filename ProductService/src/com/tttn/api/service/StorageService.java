package com.tttn.api.service;

import com.tttn.api.dbresult.RGetFlower;
import com.tttn.api.dbresult.RGetStorage;
import com.tttn.api.entities.request.GetStorageByFlowerPayLoad;
import com.tttn.api.entities.request.PostStoragePayLoad;
import com.tttn.api.entities.response.DefaultResponseData;
import com.tttn.api.repository.StorageRepository;
import com.tttn.api.resources.I18n;
import com.tttn.api.utils.Global;

public class StorageService {
    private static StorageService storageService = null;

    public static StorageService ins() {
        if (storageService == null) {
            storageService = new StorageService();
        }
        return storageService;
    }

    public static Integer getStorageByFlower(GetStorageByFlowerPayLoad request) throws Exception {
        return StorageRepository.getStorageByFlower(request);
    }

    public static Integer addStorage_CancelOrder(PostStoragePayLoad request) throws Exception {
        return StorageRepository.addStorage_CancelOrder(request);
    }
}
