package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.TypeHouse;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import com.cg.spb_houseforrent.service.ITypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/typeHouse")
public class TypeHouseController {
    @Autowired
    private ITypeService typeService;
    @GetMapping
    public ResponseEntity<?> getAllType() {
        Iterable<TypeHouse> typeHouses = typeService.findAll();
        return new ResponseEntity<>(typeHouses, HttpStatus.OK);
    }
}
