package com.cg.spb_houseforrent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingSearchCriterDTO {
    private String namehouse;
    private LocalDate startDate;
    private LocalDate endDate;
    private String orderStatus;
}
