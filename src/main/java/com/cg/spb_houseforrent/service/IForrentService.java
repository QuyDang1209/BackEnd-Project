package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.BookingSearchCriterDTO;
import com.cg.spb_houseforrent.model.dto.FilterForrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.HouseSearchCriteriaDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IForrentService extends IGenericService<Forrent> {
    Forrent saveForrentDto(ForrentDTO forrentDTO);

    List<ForrentResDTO> findAllForrentDTO();

    Page<ForrentResDTO> findAllForrentDTO(int page, int pageSize);

    Page<ForrentResDTO> findForrentResDTOByUserId(Long userId, Pageable pageable);

    Optional<ForrentResDTO> findForrentHouseDTOById(Long id);

    Set<ForrentResDTO> findAllForrentDTO(FilterForrent filterForrent);
    Page<ForrentResDTO> filterHomePage(Pageable pageable, LocalDate checkIn, LocalDate checkOut);

    Iterable<Forrent> getForrentByTypeId(Long typeId);

    Page<ForrentResDTO> searchHousesByNamehouseAndOrderStatus(String namehouse, String orderStatus, Pageable pageable);

    Page<ForrentResDTO> searchSchedules(String namehouse, LocalDate startDate, LocalDate endDate, String orderStatus, Pageable pageable);
}