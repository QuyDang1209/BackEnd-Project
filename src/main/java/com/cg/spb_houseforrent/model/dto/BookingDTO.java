package com.cg.spb_houseforrent.model.dto;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.Payment;
import com.cg.spb_houseforrent.model.StatusHouse;
import com.cg.spb_houseforrent.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
    private Long id;
    private Long forrent;
    private Long users;
    private LocalDate orderday;
    private LocalDate payday;
    //    private Double rent;
    private Double deposite;
    private Long payment;
    private Long status;
    //    private Double rent;

}


