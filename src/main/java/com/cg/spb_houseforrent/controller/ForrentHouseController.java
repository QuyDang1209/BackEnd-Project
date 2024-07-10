package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.service.ForrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/forrent-house")
public class ForrentHouseController {
    @Autowired
    private ForrentService forrentService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody ForrentDTO forrentDTO){
        forrentService.saveForrentDto(forrentDTO);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
