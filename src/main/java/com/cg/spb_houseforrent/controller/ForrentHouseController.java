package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.dto.FilterForrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import com.cg.spb_houseforrent.service.ForrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<?> getAllForrentDTOs() {
        List<ForrentResDTO> forrentDTOs = forrentService.findAllForrentDTO();
        return new ResponseEntity<>(forrentDTOs, HttpStatus.OK);
    }
    // :8080/api/forrent-house/filter?kw=&person=2&bedroom=2&bathroom=2&checkin=2022-11-11&checkout=2022-11-12&page=1&size=10
    @GetMapping("/filter")
    public ResponseEntity<?> filter(@PageableDefault(page = 0, size = 10 ) Pageable pageable, FilterForrent filterForrent) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
