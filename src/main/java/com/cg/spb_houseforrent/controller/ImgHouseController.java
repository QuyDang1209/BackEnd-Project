package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.ImgHouse;
import com.cg.spb_houseforrent.service.IImgHouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/img")
public class ImgHouseController {
    @Autowired
    private IImgHouseService imgHouseService;

    @PostMapping
    private ResponseEntity<?> save (@RequestBody Set<ImgHouse> imgHouseList) {
        imgHouseService.saveListImg(imgHouseList);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    private ResponseEntity<?> getAllImgByid(@PathVariable Long id){
        return new ResponseEntity<>(imgHouseService.findAllById(id), HttpStatus.OK);
    }
}
