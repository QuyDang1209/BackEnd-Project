package com.cg.spb_houseforrent.model.dto;

import com.cg.spb_houseforrent.model.Comment;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.Review;
import com.cg.spb_houseforrent.model.User;

import java.time.LocalDate;

public class ReviewDTO {
    private Long id;
    private Forrent forrentId;
    private User userId;
    private Comment comment_id;
    private Integer rating;
    private LocalDate reviewDate;
    public ReviewDTO(Review review){
        this.id = review.getId();
        this.forrentId = new Forrent(review.getId());
        this.comment_id = new Comment(review.getId());
        this.rating = review.getRating();
        this.reviewDate = review.getReviewDate();
    }
}
