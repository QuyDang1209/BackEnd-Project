package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Rental;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.res.RentalHistoryDTORes;
import com.cg.spb_houseforrent.repository.IRentalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalService implements IRentalService {

    @Autowired
    private IRentalRepository rentalRepository;

    @Override
    public List<RentalHistoryDTORes> getUserRentalHistory(User users) {
        List<Rental> rentals = rentalRepository.findByUser(users);
        return rentals.stream().map(this::convertToDTORes).collect(Collectors.toList());
    }

    @Override
    public void cancelRental(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid rental ID"));
        rental.setOrderStatus("Cancelled");
        rentalRepository.save(rental);
    }
    private RentalHistoryDTORes convertToDTORes(Rental rental) {
        RentalHistoryDTORes dtoRes = new RentalHistoryDTORes();
        dtoRes.setId(rental.getId());
        dtoRes.setRentalTime(rental.getRentalTime());
        dtoRes.setNamehouse(rental.getNamehouse());
        dtoRes.setTotalOrder(rental.getTotalOrder());
        dtoRes.setOrderStatus(rental.getOrderStatus());
        long daysUntilRental = ChronoUnit.DAYS.between(LocalDateTime.now(), rental.getRentalTime());
        dtoRes.setDaysUntilRental(daysUntilRental);
        boolean canCancel = daysUntilRental > 1 && "Pending".equals(rental.getOrderStatus());
        dtoRes.setCanCancel(canCancel);
        return dtoRes;
    }
}
