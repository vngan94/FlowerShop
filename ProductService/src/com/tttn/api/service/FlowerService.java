package com.tttn.api.service;

import com.tttn.api.dbresult.RGetCategory;
import com.tttn.api.dbresult.RGetColor;
import com.tttn.api.dbresult.RGetFlower;
import com.tttn.api.entities.request.*;
import com.tttn.api.repository.AccountRepository;
import com.tttn.api.repository.FlowerRepository;

import java.util.List;

public class FlowerService {

  public static List<RGetColor> getAllColor() throws Exception {
    return FlowerRepository.getAllColor();
  }

  public static List<RGetCategory> getAllCategory() throws Exception {
    return FlowerRepository.getAllCategory();
  }

  public static int addFlower(PostFlowerPayLoad request) throws Exception {
    return FlowerRepository.addFlower(request);
  }

  public static List<RGetFlower> getAllFlower() throws Exception {
    return FlowerRepository.getAllFlower();
  }

  public static RGetFlower getFlowerById(GetFlowerByIdPayLoad request) throws Exception {
    return FlowerRepository.getFlowerById(request);
  }

  public static List<RGetFlower> getListFlowerByKey(GetListFlowerByKeyPayLoad request) throws Exception {
    return FlowerRepository.getListFlowerByKey(request);
  }

  public static Integer addStorage(PostStoragePayLoad request) throws Exception {
    return FlowerRepository.addStorage(request);
  }

  public static Integer updateFlower(UpdateFlowerPayLoad request) throws Exception {
    return FlowerRepository.updateFlower(request);
  }

  public static void blockAnUnblockFlower(BlockAndUnblockPayLoad request) {
     FlowerRepository.blockAndUnblockFlower(request);
  }
}
