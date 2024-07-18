package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.FilterForrent;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import com.cg.spb_houseforrent.service.ForrentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.*;

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

    @GetMapping("/pagging")
    public ResponseEntity<?> getAllForrentDTOs(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int pageSize) {
        Page<ForrentResDTO> forrentDTOsPage = forrentService.findAllForrentDTO(page,pageSize);
        Map<String, Object> response = new HashMap<>();
        response.put("items", forrentDTOsPage.getContent());
        response.put("totalPages", forrentDTOsPage.getTotalPages());
        return new ResponseEntity<>(forrentDTOsPage, HttpStatus.OK);
    }



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

    @GetMapping("/detail/{id}")
    public ResponseEntity<?> findForrentHouseDTOById(@PathVariable Long id) {
        Optional<ForrentResDTO> forrentResDTOOptional = forrentService.findForrentHouseDTOById(id);
        if (!forrentResDTOOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(forrentResDTOOptional.get(), HttpStatus.OK);
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<?> getHouseByUserId(@PathVariable Long userId) {
        Iterable<ForrentResDTO> forrentResDTOS = forrentService.findForrentResDTOByUserId(userId);
        return new ResponseEntity<>(forrentResDTOS, HttpStatus.OK);
    }

    @GetMapping("/pagging/users/{userId}")
    public ResponseEntity<?> getHouseByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ForrentResDTO> forrentResDTOS = forrentService.findForrentResDTOByUserId(userId, pageable);
        return new ResponseEntity<>(forrentResDTOS, HttpStatus.OK);
    }

    @PostMapping("/filter")
    private ResponseEntity<?> checkDayOrderPay(@RequestBody FilterForrent filterForrent){
        Set<ForrentResDTO> forrentDTOs = forrentService.findAllForrentDTO(filterForrent);
        return new ResponseEntity<>(forrentDTOs, HttpStatus.OK);

    }


    @GetMapping("/filter-house-by-type")
    public Iterable<Forrent> getHouseByType(@RequestParam("typeId") Long typeId) {
        return forrentService.getForrentByTypeId(typeId);
    }

    // Search for houses by name and status with pagination
    @GetMapping("/search")
    public ResponseEntity<?> searchHouses(
            @RequestParam("namehouse") String namehouse,
            @RequestParam("orderStatus") String orderStatus,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<ForrentResDTO> houses = forrentService.searchHousesByNamehouseAndOrderStatus(namehouse, orderStatus, pageable);
        return new ResponseEntity<>(houses, HttpStatus.OK);
    }

    // Search for rental schedules by house namehouse , time range, and order status with pagination
    @GetMapping("/search-schedules")
    public ResponseEntity<?> searchSchedules(
            @RequestParam("namehouse") String namehouse,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam("orderStatus") String orderStatus,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<ForrentResDTO> schedules = forrentService.searchSchedules(namehouse, startDate, endDate, orderStatus, pageable);
        return new ResponseEntity<>(schedules, HttpStatus.OK);
    }
}