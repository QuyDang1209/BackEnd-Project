package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.FilterForrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public interface IForrentService extends IGenericService<Forrent> {
    Forrent saveForrentDto(ForrentDTO forrentDTO);

    List<ForrentResDTO> findAllForrentDTO();
    Set<ForrentResDTO> findAllForrentDTO(FilterForrent filterForrent);
    Page<ForrentResDTO> filterHomePage(Pageable pageable, LocalDate checkIn, LocalDate checkOut);

    Iterable<Forrent> getForrentByTypeId(Long typeId);
}
