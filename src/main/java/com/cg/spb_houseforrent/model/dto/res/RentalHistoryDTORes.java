package com.cg.spb_houseforrent.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalHistoryDTORes {
    private Long id;
    private LocalDateTime rentalTime;
    private String namehouse;
    private Double totalOrder;
    private String orderStatus;
    private Long daysUntilRental;
    private boolean canCancel;

    public RentalHistoryDTORes(LocalDateTime rentalTime, String namehouse, Double totalOrder, String orderStatus, Long daysUntilRental, boolean canCancel) {
        this.rentalTime = rentalTime;
        this.namehouse = namehouse;
        this.totalOrder = totalOrder;
        this.orderStatus = orderStatus;
        this.daysUntilRental = daysUntilRental;
        this.canCancel = canCancel;
    }
}
