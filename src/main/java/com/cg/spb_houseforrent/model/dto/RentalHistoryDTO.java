package com.cg.spb_houseforrent.model.dto;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.Rental;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.userdetails.User;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalHistoryDTO {
    private Long id;
    private LocalDateTime rentalTime;
    private String namehouse;
    private Double totalOrder;
    private String orderStatus;
    private Long daysUntilRental;
    private Long forrents;
    private Long users;
    private Long evaluateStatus;

    public RentalHistoryDTO(Rental rental) {
        this.id = rental.getId();
        this.rentalTime = rental.getRentalTime();
        this.namehouse = rental.getNamehouse();
        this.totalOrder = rental.getTotalOrder();
        this.orderStatus = rental.getOrderStatus();
        this.daysUntilRental = rental.getDaysUntilRental();
        this.forrents = rental.getForrents().getId();
        this.users = rental.getUsers().getId();
        this.evaluateStatus = rental.getEvaluateStatus().getId();
    }
}
