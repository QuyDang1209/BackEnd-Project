package com.cg.spb_houseforrent.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseSearchCriteriaDTO {
    private String namehouse;
    private String orderStatus;
}
