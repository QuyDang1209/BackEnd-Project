package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.ImgHouse;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.repository.IForrentRepository;
import com.cg.spb_houseforrent.repository.ITypeRepository;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ForrentService implements IForrentService {
    @Autowired
    private IForrentRepository forrentRepository;
    @Autowired
    private ITypeService typeService;
    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private IImgHouseService imgHouseService;

    @Override
    public Iterable<Forrent> findAll() {
        return forrentRepository.findAll();
    }

    @Override
    public Optional<Forrent> findById(Long id) {
        return forrentRepository.findById(id);
    }

    @Override
    public Optional<Forrent> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Forrent save(Forrent forrent) {
        return forrentRepository.save(forrent);
    }

    @Override
    public Forrent saveForrentDto(ForrentDTO forrentDTO) {
            Forrent forrent = null;
        if(forrentDTO.getId() != null ){
            forrent = forrentRepository.findById(forrentDTO.getId()).get();
            forrent.setNamehouse(forrentDTO.getNamehouse());
            forrent.setAddress(forrentDTO.getAddress());
            forrent.setImg(forrentDTO.getImg());
            forrent.setDecription(forrentDTO.getDecription());
            forrent.setRentingprice(forrentDTO.getRentingprice());
            forrent.setBedroom(forrentDTO.getBedroom());
            forrent.setBathroom(forrentDTO.getBathroom());
            forrent.setType(typeService.findByid(forrentDTO.getType()));
            forrent.setUsers(usersRepository.findById(forrentDTO.getUsers()).get());
            for(ImgHouse i: forrent.getImg()){
                if(i.getId() == null){
                    i.setForrents(forrent);
                    imgHouseService.save(i);
                    break;
                }
            }
            forrentRepository.save(forrent);
        }else {
            forrent = new Forrent();
            forrent.setNamehouse(forrentDTO.getNamehouse());
            forrent.setAddress(forrentDTO.getAddress());
            forrent.setImg(imgHouseService.saveListImg(forrentDTO.getImg()));
            forrent.setDecription(forrentDTO.getDecription());
            forrent.setRentingprice(forrentDTO.getRentingprice());
            forrent.setBedroom(forrentDTO.getBedroom());
            forrent.setBathroom(forrentDTO.getBathroom());
            forrent.setType(typeService.findByid(forrentDTO.getType()));
            forrent.setUsers(usersRepository.findById(forrentDTO.getUsers()).get());
            forrentRepository.save(forrent);
            for(ImgHouse i: forrent.getImg()){
                i.setForrents(forrent);
                imgHouseService.save(i);
            }

        }
        return forrent;
    }

    @Override
    public void remove(Long id) {

    }
}
