package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.ImgHouse;
import com.cg.spb_houseforrent.repository.IImgHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ImgHouseService implements IImgHouseService {
    @Autowired
    private IImgHouseRepository iImgHouseRepository;

    @Override
    public Iterable<ImgHouse> findAll() {
        return iImgHouseRepository.findAll();
    }

    @Override
    public Optional<ImgHouse> findById(Long id) {
        return iImgHouseRepository.findById(id);
    }




    @Override
    public List<ImgHouse> findAllById(Long id) {
        return iImgHouseRepository.findAllById(id);
    }

    @Override
    public Optional<ImgHouse> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public ImgHouse save(ImgHouse imgHouse) {
        return iImgHouseRepository.save(imgHouse);
    }
    public Set<ImgHouse> saveListImg(Set<ImgHouse> imgHouseSet){
        Set<ImgHouse> imgHouseId = new HashSet<>();
        for(ImgHouse i: imgHouseSet){
             save(i);
             imgHouseId.add(i);
        }
        return imgHouseId;
    }

    @Override
    public void remove(Long id) {
        iImgHouseRepository.deleteById(id);
    }
}
