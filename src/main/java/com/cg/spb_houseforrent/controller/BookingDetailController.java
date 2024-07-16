package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.dto.BookingDTO;
import com.cg.spb_houseforrent.service.IBookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/booking")
public class BookingDetailController {
    @Autowired
    private IBookingDetailService bookingDetailService;

    @GetMapping
    private ResponseEntity<?> getAllBooking(){
        return new ResponseEntity<>(bookingDetailService.findAll(), HttpStatus.OK);
    }
    @PostMapping
    private ResponseEntity<?> save(@RequestBody BookingDTO bookingDTO){
        bookingDetailService.saveBookingDTO(bookingDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PatchMapping("/change-status")
    private ResponseEntity<?> changeStatusHouse(@RequestBody List<BookingDTO>  bookingDTO){
        bookingDetailService.changeStatus(bookingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
