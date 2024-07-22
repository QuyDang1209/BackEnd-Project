package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.ImgHouse;
import com.cg.spb_houseforrent.model.dto.FilterForrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import com.cg.spb_houseforrent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

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
    @Autowired
    private IBookingDetailRepository iBookingDetailRepository;

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
        if (forrentDTO.getId() != null) {
            forrent = forrentRepository.findById(forrentDTO.getId()).get();
            forrent.setNamehouse(forrentDTO.getNamehouse());
            forrent.setAddress(forrentDTO.getAddress());
            forrent.setImg(forrentDTO.getImg());
            forrent.setDecription(forrentDTO.getDecription());
            forrent.setRentingprice(forrentDTO.getRentingprice());
            forrent.setBedroom(forrentDTO.getBedroom());
            forrent.setBathroom(forrentDTO.getBathroom());
            forrent.setType(iTypeHouseRepository.findById(forrentDTO.getType()).get());
            forrent.setUsers(usersRepository.findById(forrentDTO.getUsers()).get());
            for (ImgHouse i : forrent.getImg()) {
                if (i.getId() == null) {
                    i.setForrents(forrent);
                    imgHouseService.save(i);
                }
            }
            forrentRepository.save(forrent);
        } else {
            forrent = new Forrent();
            forrent.setNamehouse(forrentDTO.getNamehouse());
            forrent.setAddress(forrentDTO.getAddress());
            forrent.setImg(imgHouseService.saveListImg(forrentDTO.getImg()));
            forrent.setDecription(forrentDTO.getDecription());
            forrent.setRentingprice(forrentDTO.getRentingprice());
            forrent.setBedroom(forrentDTO.getBedroom());
            forrent.setBathroom(forrentDTO.getBathroom());
            forrent.setType(iTypeHouseRepository.findById(forrentDTO.getType()).get());
            forrent.setUsers(usersRepository.findById(forrentDTO.getUsers()).get());
            forrent.setNamehouse(forrentDTO.getNamehouse());
            forrent.setBedroom(forrentDTO.getBedroom());
            forrent.setBathroom(forrentDTO.getBathroom());
            forrentRepository.save(forrent);
            for (ImgHouse i : forrent.getImg()) {
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

    @Override
    public Set<ForrentResDTO> findAllForrentDTO(FilterForrent filterForrent) {

        Set<ForrentResDTO> listForrentCheck = new HashSet<>();
        List<ForrentResDTO> list1 = findAllForrentDTO();
        for (ForrentResDTO forrentResDTO : list1) {
            if (filterForrent.getBedroom() <= forrentResDTO.getBedroom() && filterForrent.getBathroom() <= forrentResDTO.getBathroom()) {
                Boolean checkday = true;
                for (BookingDetail bookingDetail : iBookingDetailRepository.findAllByForrent(forrentRepository.findById(forrentResDTO.getId()).get())) {
                    if (!
                            ((filterForrent.getCheckout().isBefore(bookingDetail.getOrderday()) || filterForrent.getCheckout().equals(bookingDetail.getOrderday())) ||
                                    (filterForrent.getCheckin().isAfter(bookingDetail.getPayday()) || filterForrent.getCheckin().equals(bookingDetail.getPayday())))
                    ) {
                        checkday = false;
                        break;
                    }
                }
                if (checkday) {
                    listForrentCheck.add(forrentResDTO);
                }
            }
        }
        return listForrentCheck;
    }

    @Override
    public Page<ForrentResDTO> filterHomePage(Pageable pageable, LocalDate checkIn, LocalDate checkOut ) {
        return forrentRepository.filterHomePage(pageable, checkIn, checkOut);
    }
    @Override
    public Iterable<Forrent> getForrentByTypeId(Long typeId) {
        return forrentRepository.findTypeById(typeId);
    }
}

