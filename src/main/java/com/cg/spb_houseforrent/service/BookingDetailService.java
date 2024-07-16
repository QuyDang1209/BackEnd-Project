package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.exception.DateForrentException;
import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.StatusHouse;
import com.cg.spb_houseforrent.model.dto.BookingDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import com.cg.spb_houseforrent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
public class BookingDetailService implements IBookingDetailService {
    @Autowired
    private IBookingDetailRepository bookingDetailRepository;
    @Autowired
    private IForrentRepository forrentRepository;
    @Autowired
    private IUsersRepository usersRepository;
    @Autowired
    private IPaymentRepository paymentRepository;
    @Autowired
    private IStatusHouseRepository statusHouseRepository;

    @Override
    public Iterable<BookingDetail> findAll() {
        return bookingDetailRepository.findAll();
    }

    @Override
    public Optional<BookingDetail> findById(Long id) {
        return bookingDetailRepository.findById(id);
    }

    @Override
    public Optional<BookingDetail> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public BookingDetail save(BookingDetail bookingDetail) {
        return bookingDetailRepository.save(bookingDetail);
    }

    @Override
    public BookingDetail saveBookingDTO(BookingDTO bookingDTO) {
        BookingDetail bookingDetail = null;
        if (bookingDTO.getId() != null) {
            bookingDetail.setForrent(forrentRepository.findById(bookingDTO.getForrent()).get());
            bookingDetail.setUsers(usersRepository.findById(bookingDTO.getUsers()).get());
            bookingDetail.setOrderday(bookingDTO.getOrderday());
            bookingDetail.setPayday(bookingDTO.getPayday());
            bookingDetail.setRent((ChronoUnit.DAYS.between(bookingDTO.getOrderday(), bookingDTO.getPayday()))
                    * (forrentRepository.findById(bookingDTO.getForrent()).get().getRentingprice()));
            bookingDetail.setDeposite(bookingDTO.getDeposite());
            bookingDetail.setPayment(paymentRepository.findById(bookingDTO.getPayment()).get());
            bookingDetail.setStatus(statusHouseRepository.findById(bookingDTO.getStatus()).get());
            save(bookingDetail);
        } else {

            Boolean checkday = true;
            for (BookingDetail bookingDetails : bookingDetailRepository.findAllByForrent(forrentRepository.findById(bookingDTO.getForrent()).get() )) {
                if (!
                        ((bookingDTO.getPayday().isBefore(bookingDetails.getOrderday()) || bookingDTO.getPayday().equals(bookingDetails.getOrderday())) ||
                                (bookingDTO.getOrderday().isAfter(bookingDetails.getPayday()) || bookingDTO.getOrderday().equals(bookingDetails.getPayday())))
                ) {
                    checkday = false;
                    break;
                }
            }
            if (checkday) {
                bookingDetail = new BookingDetail();
                bookingDetail.setForrent(forrentRepository.findById(bookingDTO.getForrent()).get());
                bookingDetail.setUsers(usersRepository.findById(bookingDTO.getUsers()).get());
                bookingDetail.setOrderday(bookingDTO.getOrderday());
                bookingDetail.setPayday(bookingDTO.getPayday());
                bookingDetail.setRent((ChronoUnit.DAYS.between(bookingDTO.getOrderday(), bookingDTO.getPayday()))
                        * (forrentRepository.findById(bookingDTO.getForrent()).get().getRentingprice()));
                bookingDetail.setDeposite(bookingDTO.getDeposite());
                bookingDetail.setPayment(paymentRepository.findById(bookingDTO.getPayment()).get());
                bookingDetail.setStatus(statusHouseRepository.findById(bookingDTO.getStatus()).get());
                save(bookingDetail);
            } else {
                throw new DateForrentException("Please chouse another day");
            }


        }
        return bookingDetail;
    }

    @Override
    public void changeStatus(List<BookingDTO> bookingDTOList) {
        for (BookingDTO bo : bookingDTOList) {
            BookingDetail bookingDetail = bookingDetailRepository.findById(bo.getId()).orElse(null);
            if (bookingDetail != null) {
                StatusHouse statusHouse = statusHouseRepository.findById(bo.getStatus()).get();
                bookingDetail.setStatus(statusHouse);
                save(bookingDetail);
            }
        }
    }

    @Override
    public List<BookingDetail> findAllBookingByForrentId(Long id) {
        return null;
    }

    @Override
    public void remove(Long id) {
        bookingDetailRepository.deleteById(id);
    }
}
