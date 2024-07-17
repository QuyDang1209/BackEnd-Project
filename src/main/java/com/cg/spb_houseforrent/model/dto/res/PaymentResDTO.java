package com.cg.spb_houseforrent.model.dto.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResDTO {
    private Long id;
    private String payname;
}