package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.ImgHouse;

import java.util.List;
import java.util.Set;

public interface IImgHouseService extends IGenericService<ImgHouse>{
    Set<ImgHouse> saveListImg(Set<ImgHouse> imgHouseList);
    List<ImgHouse> findAllById(Long id);
//    void deleteAllImagesByForrendId(Long id);
}
