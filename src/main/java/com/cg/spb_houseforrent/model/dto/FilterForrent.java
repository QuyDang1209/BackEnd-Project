package com.cg.spb_houseforrent.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FilterForrent {
    private String kw;
    private Long bedroom;
    private Long bathroom;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkin;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate checkout;
}
