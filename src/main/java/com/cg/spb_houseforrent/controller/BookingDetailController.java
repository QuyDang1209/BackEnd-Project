package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.dto.BookingDTO;
import com.cg.spb_houseforrent.model.dto.res.BookingResDTO;
import com.cg.spb_houseforrent.service.IBookingDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/booking")
public class BookingDetailController {
    @Autowired
    private IBookingDetailService bookingDetailService;

    @GetMapping
    private ResponseEntity<?> getAllBooking() {
        return new ResponseEntity<>(bookingDetailService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public BookingDetail createBookingDetail(
            @RequestParam Long forrentId,
            @RequestParam Long userId,
            @RequestParam("orderpay") String orderpay,
            @RequestParam("payday") String payday) {
        LocalDate start = LocalDate.parse(orderpay);
        LocalDate end = LocalDate.parse(payday);
        return bookingDetailService.createBookingDetail(forrentId, userId, start, end);
    }

    @PostMapping
    private ResponseEntity<?> save(@RequestBody BookingDTO bookingDTO) {
        bookingDetailService.saveBookingDTO(bookingDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/change-status")
    private ResponseEntity<?> changeStatusHouse(@RequestBody List<BookingDTO> bookingDTO) {
        bookingDetailService.changeStatus(bookingDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<BookingResDTO>> getAllBookingsByHostUserId(@PathVariable Long hostId) {
        return new ResponseEntity<>(bookingDetailService.findAllBookingsByUserId(hostId), HttpStatus.OK);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<BookingResDTO>> getAllBookingsByUserId(@PathVariable Long userId) {
        return new ResponseEntity<>(bookingDetailService.findAllBookingsByUserId(userId), HttpStatus.OK);
    }


    /**
     * Nếu muốn checkIn thì truyền statusHouseId = 2
     */
    @GetMapping("/checkin/{bookingId}/{statusHouseId}")
    public ResponseEntity<?> checkinCheckIn(@PathVariable Long bookingId, @PathVariable Long statusHouseId) {
        try {
            bookingDetailService.checkin(bookingId, statusHouseId);
            return ResponseEntity.ok("Check-in thành công");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
    /**
     * Nếu muốn checkOut thì truyền statusHouseId = 3
     */
    @GetMapping("/checkout/{bookingId}/{statusHouseId}")
    public ResponseEntity<?> checkinCheckout(@PathVariable Long bookingId, @PathVariable Long statusHouseId) {
        try {
            bookingDetailService.checkout(bookingId, statusHouseId);
            return ResponseEntity.ok("Check-out thành công");
        } catch (Exception e) {
            return ResponseEntity.status(500).body(e.getMessage());
        }
    }
}