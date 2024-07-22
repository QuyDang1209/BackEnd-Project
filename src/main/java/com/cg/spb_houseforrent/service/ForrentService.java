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
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
//                    break;
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
    public Page<ForrentResDTO> findAllForrentDTO(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Forrent> forrentPage = forrentRepository.findAll(pageable);
        return forrentPage.map(this::convertToDto);
    }

    @Override
    public Page<ForrentResDTO> findForrentResDTOByUserId(Long userId, Pageable pageable) {
        return forrentRepository.findByUserId(userId, pageable);
    }

    @Override
    public void remove(Long id) {

    }
    @Override
    public Optional<ForrentResDTO> findForrentHouseDTOById(Long id) {
        return forrentRepository.findForrentHouseDTOById(id);
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
    public Iterable<Forrent> getForrentByTypeId(Long typeId) {
        return forrentRepository.findTypeById(typeId);
    }

    @Override
    public Page<ForrentResDTO> searchHousesByNamehouseAndOrderStatus(String namehouse, String orderStatus, Pageable pageable) {
        return forrentRepository.findByNamehouseContainingAndOrderStatus(namehouse, orderStatus, pageable)
                .map(this::convertToDto);
    }

    @Override
    public Page<ForrentResDTO> searchSchedules(String namehouse, LocalDate startDate, LocalDate endDate, String orderStatus, Pageable pageable) {
        return forrentRepository.findSchedules(namehouse, startDate, endDate, orderStatus, pageable)
                .map(this::convertToDto);
    }

    public Iterable<ForrentResDTO> findForrentResDTOByUserId(Long userId) {
        return forrentRepository.findForrentResDTOsByUsers_Id(userId);
    }

    public Map<String, Object> calculateIncomeStatistics(LocalDate startDate, LocalDate endDate) {
        Map<String, Object> statistics = new HashMap<>();
        BigDecimal totalIncome = calculateTotalIncome(startDate, endDate);
        statistics.put("startDate", startDate);
        statistics.put("endDate", endDate);
        statistics.put("totalIncome", totalIncome);
        return statistics;
    }

    private BigDecimal calculateTotalIncome(LocalDate startDate, LocalDate endDate) {
        BigDecimal totalIncome = BigDecimal.valueOf(5000.00); // Example total income
        return totalIncome;
    }

    public List<ForrentResDTO> findTop5MostRented(){
        return forrentRepository.findTop5ByOrderByRentCountDesc();
    }

    private ForrentResDTO convertToDto(Forrent forrent) {
        // Conversion logic here...
        ForrentResDTO forrentResDTO = new ForrentResDTO();
        forrentResDTO.setId(forrent.getId());
        forrentResDTO.setNamehouse(forrent.getNamehouse());
        forrentResDTO.setImgDTOs(forrent.getImg().stream().map(imgHouse -> imgHouse.imgHouseDTO()).toList());
        forrentResDTO.setAddress(forrent.getAddress());
        forrentResDTO.setRentingprice(forrent.getRentingprice());
        forrentResDTO.setBedroom(forrent.getBedroom());
        forrentResDTO.setBathroom(forrent.getBathroom());
        forrentResDTO.setType(forrent.getType().getId());
        forrentResDTO.setUsers(forrent.getUsers().getId());
        forrentResDTO.setStartDate(forrent.getStartDate());
        forrentResDTO.setEndDate(forrent.getEndDate());
        forrentResDTO.setOrderStatus(forrent.getOrderStatus());
        return forrentResDTO;
    }

    // Assuming you have a constructor or method to convert Forrent to ForrentResDTO
    public ForrentResDTO updateForrentHouseStatus(Long houseId , String orderStatus){
        Forrent forrent = forrentRepository.findById(houseId).orElseThrow(() -> new IllegalArgumentException("House not found"));
        if ("Rented".equals(forrent.getOrderStatus())){
            throw new IllegalStateException(" Can't change status of a rented house");
        }
        forrent.setOrderStatus(orderStatus);
        Forrent updatedForrent = forrentRepository.save(forrent);
        return new ForrentResDTO(updatedForrent);
    }
}