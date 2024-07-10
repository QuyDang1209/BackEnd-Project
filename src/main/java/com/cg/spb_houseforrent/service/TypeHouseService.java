package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.TypeHouse;
import com.cg.spb_houseforrent.repository.ITypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeHouseService implements ITypeService {
    @Autowired
    private ITypeRepository typeRepository;

    @Override
    public TypeHouse findByid(Long id) {
        return typeRepository.findById(id).get();
    }
}
