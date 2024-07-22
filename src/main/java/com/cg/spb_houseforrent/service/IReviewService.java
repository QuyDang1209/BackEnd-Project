package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.dto.ReviewDTO;

import java.util.List;

public interface IReviewService {
    List<ReviewDTO> getReviewsByForrentId(Long forrentId);
}
