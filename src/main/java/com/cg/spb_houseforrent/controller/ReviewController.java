package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.dto.ReviewDTO;
import com.cg.spb_houseforrent.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    public ResponseEntity<?> addReview(@RequestParam Long forrentId, @RequestParam Long userId, @RequestBody String content) {
        try {
            reviewService.addReview(forrentId, userId, content);
            return ResponseEntity.ok().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/forrent/{forrentId}")
    public ResponseEntity<List<ReviewDTO>> getReviewsByForrentId(@PathVariable Long forrentId) {
        List<ReviewDTO> reviews = reviewService.getReviewsByForrentId(forrentId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);
    }
}
