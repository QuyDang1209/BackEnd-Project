package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.dto.BookingDTO;

import java.util.List;

public interface IBookingDetailService extends IGenericService<BookingDetail>{
    BookingDetail saveBookingDTO(BookingDTO bookingDTO);
    void changeStatus(List<BookingDTO> bookingDTOList);
    List<BookingDetail> findAllBookingByForrentId(Long id);
}
}
