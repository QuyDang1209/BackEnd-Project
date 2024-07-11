package com.cg.spb_houseforrent.model.dto.res;

import com.cg.spb_houseforrent.model.Forrent;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImgHouseDTO {
    private Long id;
    private String img;
}