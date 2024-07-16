package com.cg.spb_houseforrent.model.dto.res;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.dto.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingResDTO {
    private Long id;
    private TypeHouseResDTO typeHouse;

    private ForrentResDTO forrent;

    private LocalDate orderDay;
    private LocalDate payDay;

    private Double deposite;
    private PaymentResDTO payment;

    private StatusHouseResDTO statusHouse;
    private UserDTO user;

    public BookingResDTO(BookingDetail bookingDetail) {
        this.id = bookingDetail.getId();
        this.typeHouse = new TypeHouseResDTO(bookingDetail.getForrent().getType().getId(), bookingDetail.getForrent().getType().getTypename());

        this.forrent = new ForrentResDTO(bookingDetail.getForrent());
        this.orderDay = bookingDetail.getOrderday();
        this.payDay = bookingDetail.getPayday();
        this.deposite = bookingDetail.getDeposite();

        this.payment = new PaymentResDTO(bookingDetail.getPayment().getId(), bookingDetail.getPayment().getPayname());
        this.statusHouse = new StatusHouseResDTO(bookingDetail.getStatus().getId(), bookingDetail.getStatus().getStatus());
        this.user = new UserDTO(bookingDetail.getUsers());
    }


}