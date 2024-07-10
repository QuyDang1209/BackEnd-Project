package com.cg.spb_houseforrent.model.dto;

import com.cg.spb_houseforrent.model.ImgHouse;
import com.cg.spb_houseforrent.model.TypeHouse;
import com.cg.spb_houseforrent.model.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForrentDTO {
    private Long id;
    private String address;
    private Set<ImgHouse> img;
    private String decription;
    private Double rentingprice;
    private Long type;
    private Long users;
}
