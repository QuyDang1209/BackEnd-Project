package com.cg.spb_houseforrent.model.dto.res;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.ImgHouse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ForrentResDTO {
    private Long id;
    private String address;
    private List<ImgHouseDTO> imgDTOs;
    private String description;
    private Double rentingprice;
    private Long type;
    private String namehouse;
    private Long bedroom;
    private Long bathroom;
    private Long users;

    public ForrentResDTO(Forrent forrent) {
        this.id = forrent.getId();
        this.address = forrent.getAddress();
        this.imgDTOs = forrent.getImgs().stream().map(imgHouse -> imgHouse.imgHouseDTO()).toList();
        this.description = forrent.getDecription();
        this.rentingprice = forrent.getRentingprice();
        this.type = forrent.getType().getId();
        this.users = forrent.getUsers().getId();
        this.namehouse = forrent.getNamehouse();
        this.bedroom = forrent.getBedroom();
        this.bathroom = forrent.getBathroom();
    }
    public ForrentResDTO(BookingDetail bookingDetail) {

    }
}