package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.TypeHouse;
import com.cg.spb_houseforrent.repository.ITypeRepository;

public interface ITypeService {
    TypeHouse findByid(Long id);
}
