package com.cg.spb_houseforrent.model.dto;

import com.cg.spb_houseforrent.model.Forrent;
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
    private String namehouse;
    private String address;
    private Set<ImgHouse> img;
    private String decription;
    private Double rentingprice;
    private Long bedroom;
    private Long bathroom;
    private Long type;
    private Long users;

    public ForrentDTO(Forrent forrent) {
        this.id = forrent.getId();
        this.address = forrent.getAddress();
        this.img = forrent.getImg();
        this.decription = forrent.getDecription();
        this.rentingprice = forrent.getRentingprice();
        this.type = forrent.getType().getId();
        this.users = forrent.getUsers().getId();
        this.namehouse = forrent.getNamehouse();
        this.bedroom = forrent.getBedroom();
        this.bathroom = forrent.getBathroom();
    }
}
