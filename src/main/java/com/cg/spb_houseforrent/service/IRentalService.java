package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.res.RentalHistoryDTORes;

import java.util.List;

public interface IRentalService {
    List<RentalHistoryDTORes> getUserRentalHistory(User users);
    void cancelRental(Long rentalId);
}
