package com.cg.spb_houseforrent.model;

import com.cg.spb_houseforrent.model.dto.BookingDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
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
    private ForrentResDTO forrent;
    private BookingDTO booking;

}

