package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.TypeHouse;
import com.cg.spb_houseforrent.repository.ITypeHouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeService implements ITypeService {

    @Autowired
    private ITypeHouseRepository typeHouseRepository;

    @Override
    public TypeHouse findByid(Long id) {
        return typeHouseRepository.findById(id).get();
    }

    @Override
    public Iterable<TypeHouse> findAll() {
        return typeHouseRepository.findAll();
    }
}
