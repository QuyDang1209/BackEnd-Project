package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.dto.FilterForrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import com.cg.spb_houseforrent.service.ForrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/forrent-house")
public class ForrentHouseController {
    @Autowired
    private ForrentService forrentService;

    //    @GetMapping("")
//    public ResponseEntity<Iterable<Forrent>> findAll() {
//        return new ResponseEntity<>(forrentService.findAll(), HttpStatus.OK);
//    }
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
//    @GetMapping("/filter")
//    public ResponseEntity<?> filter(@PageableDefault(page = 0, size = 10 ) Pageable pageable, FilterForrent filterForrent) {
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }


    @GetMapping("/{id}")
    private ResponseEntity<?> findById(@PathVariable Long id){
        Optional<Forrent> forrentOptional = forrentService.findById(id);
        if(!forrentOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else {
            return new ResponseEntity<>(forrentOptional.get(),HttpStatus.OK);
        }
    }

    @PatchMapping("/edit/{id}")
    private ResponseEntity<?> edit(@PathVariable Long id, @RequestBody ForrentDTO forrentDTO){
        Optional<Forrent> forrentOptional = Optional.of(forrentService.findById(id).get());
        forrentDTO.setId(id);
        forrentService.saveForrentDto(forrentDTO);
        return new ResponseEntity<>(forrentOptional.get(),HttpStatus.OK);
    }
    @PostMapping("/filter")
    private ResponseEntity<?> checkDayOrderPay(@RequestBody FilterForrent filterForrent){
        Set<ForrentResDTO> forrentDTOs = forrentService.findAllForrentDTO(filterForrent);
        return new ResponseEntity<>(forrentDTOs, HttpStatus.OK);
    }
    @GetMapping("/filter-home")
    private ResponseEntity<?> homepageFilter(@PageableDefault(size = 10, page = 0) Pageable pageable, LocalDate checkIn, LocalDate checkOut) {
        return new ResponseEntity<>(forrentService.filterHomePage(pageable, checkIn, checkOut), HttpStatus.OK);

    }
    @GetMapping("/filter-house-by-type")
    public Iterable<Forrent> getHouseByType(@RequestParam("typeId") Long typeId) {
        return forrentService.getForrentByTypeId(typeId);
    }
}
