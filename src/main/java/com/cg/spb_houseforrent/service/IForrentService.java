package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;

public interface IForrentService extends IGenericService<Forrent> {
    Forrent saveForrentDto(ForrentDTO forrentDTO);
}
