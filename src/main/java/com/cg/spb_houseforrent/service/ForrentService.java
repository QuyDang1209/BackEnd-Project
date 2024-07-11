package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.ImgHouse;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import com.cg.spb_houseforrent.repository.IForrentRepository;
import com.cg.spb_houseforrent.repository.ITypeHouseRepository;
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
    private IUsersRepository usersRepository;
    @Autowired
    private IImgHouseService imgHouseService;
    @Autowired
    private ITypeHouseRepository iTypeHouseRepository;

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
            forrent.setAddress(forrentDTO.getAddress());
            forrent.setImgs(forrentDTO.getImg());
            forrent.setDecription(forrentDTO.getDescription());
            forrent.setRentingprice(forrentDTO.getRentingprice());
            forrent.setType(iTypeHouseRepository.findById(forrentDTO.getType()).get());
            forrent.setUsers(usersRepository.findById(forrentDTO.getUsers()).get());
            forrent.setNamehouse(forrentDTO.getNamehouse());
            forrent.setBedroom(forrentDTO.getBedroom());
            forrent.setBathroom(forrentDTO.getBathroom());
            forrentRepository.save(forrent);


        }else {
            forrent = new Forrent();
            forrent.setAddress(forrentDTO.getAddress());
            forrent.setImgs(imgHouseService.saveListImg(forrentDTO.getImg()));
            forrent.setDecription(forrentDTO.getDescription());
            forrent.setRentingprice(forrentDTO.getRentingprice());
            forrent.setType(iTypeHouseRepository.findById(forrentDTO.getType()).get());
            forrent.setUsers(usersRepository.findById(forrentDTO.getUsers()).get());
            forrent.setNamehouse(forrentDTO.getNamehouse());
            forrent.setBedroom(forrentDTO.getBedroom());
            forrent.setBathroom(forrentDTO.getBathroom());
            forrentRepository.save(forrent);
            for(ImgHouse i: forrent.getImgs()){
                i.setForrents(forrent);
                imgHouseService.save(i);
            }

        }
        return forrent;
    }

    @Override
    public List<ForrentResDTO> findAllForrentDTO() {
        return forrentRepository.findAllForrentDTO();
    }

    @Override
    public void remove(Long id) {

    }

}
