package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.Review;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.ReviewDTO;
import com.cg.spb_houseforrent.repository.IForrentRepository;
import com.cg.spb_houseforrent.repository.IUsersRepository;
import com.cg.spb_houseforrent.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService implements IReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private IForrentRepository forrentRepository;

    @Autowired
    private IUsersRepository userRepository;

    @Override
    public List<ReviewDTO> getReviewsByForrentId(Long forrentId) {
        List<Review> reviews = reviewRepository.findByForrentId(forrentId);
        return reviews.stream().map(ReviewDTO::new).collect(Collectors.toList());
    }

    public void addReview(Long forrentId, Long userId, String content) {
        // Ensure the house status is "Checked-out" and user has rented the house
        Forrent forrent = forrentRepository.findById(forrentId).orElseThrow(() -> new RuntimeException("House not found"));
        if (!"Checked-out".equals(forrent.getOrderStatus())) {
            throw new RuntimeException("House status is not eligible for review");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Review review = new Review();
        review.setForrent(forrent);
        review.setUser(user);
        review.setContent(content);
        review.setReviewDate(LocalDate.now());

        reviewRepository.save(review);
    }
}
