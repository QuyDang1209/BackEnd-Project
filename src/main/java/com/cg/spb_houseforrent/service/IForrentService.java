package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.BookingSearchCriterDTO;
import com.cg.spb_houseforrent.model.dto.FilterForrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.HouseSearchCriteriaDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IForrentService extends IGenericService<Forrent> {
    Forrent saveForrentDto(ForrentDTO forrentDTO);

    List<ForrentResDTO> findAllForrentDTO();

    Optional<ForrentResDTO> findForrentHouseDTOById(Long id);

    Set<ForrentResDTO> findAllForrentDTO(FilterForrent filterForrent);

    Iterable<Forrent> getForrentByTypeId(Long typeId);

//    Page<ForrentResDTO> searchBookings(BookingSearchCriterDTO criterDTO, Pageable pageable);
//
//    Page<ForrentResDTO> searchHouses(HouseSearchCriteriaDTO criteria, Pageable pageable);
}