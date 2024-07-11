package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;

import java.util.List;

public interface IForrentService extends IGenericService<Forrent> {
    Forrent saveForrentDto(ForrentDTO forrentDTO);

    List<ForrentResDTO> findAllForrentDTO();
}
