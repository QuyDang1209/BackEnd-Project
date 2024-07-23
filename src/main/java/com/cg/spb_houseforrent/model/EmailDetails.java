package com.cg.spb_houseforrent.model;

import com.cg.spb_houseforrent.model.dto.BookingDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class EmailDetails {
    private String recipient;
    private String subject;
    private User user;
    private Forrent forrent;
    private BookingDTO booking;

}

