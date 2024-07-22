package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.service.LandlordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/landlord")
public class LandlordController {
    @Autowired
    private LandlordService landlordService;
    @PatchMapping("/{id}/approve")
    public ResponseEntity<Void> approveLandlord(@PathVariable Long id, @RequestParam String reason) {
        landlordService.approveLandlord(id, reason);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}/reject")
    public ResponseEntity<Void> rejectLandlord(@PathVariable Long id, @RequestParam String reason) {
        landlordService.rejectLandlord(id, reason);
        return ResponseEntity.ok().build();
    }
}
