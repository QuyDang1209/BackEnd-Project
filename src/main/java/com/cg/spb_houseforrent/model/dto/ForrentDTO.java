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
    private String address;
    private Set<ImgHouse> img;
    private String description;
    private Double rentingprice;
    private Long type;
    private Long users;
    private String namehouse;
    private Long bedroom;
    private Long bathroom;

    public ForrentDTO(Forrent forrent) {
        this.id = forrent.getId();
        this.address = forrent.getAddress();
        this.img = forrent.getImgs();
        this.description = forrent.getDecription();
        this.rentingprice = forrent.getRentingprice();
        this.type = forrent.getType().getId();
        this.users = forrent.getUsers().getId();
        this.namehouse = forrent.getNamehouse();
        this.bedroom = forrent.getBedroom();
        this.bathroom = forrent.getBathroom();
    }
}
