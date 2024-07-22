package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.config.service.UserService;
import com.cg.spb_houseforrent.exception.DateForrentException;
import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.StatusHouse;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.dto.BookingDTO;
import com.cg.spb_houseforrent.model.dto.res.BookingResDTO;
import com.cg.spb_houseforrent.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    private IBookingRepository bookingRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

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

    /*
    @Override
    public BookingDetail createBookingDetail(Long forrentId, Long userId, LocalDate orderpay, LocalDate payday) {
        Forrent forrent = forrentRepository.findById(forrentId).orElseThrow(() -> new IllegalArgumentException("House not found"));
        if ("Rented".equals(forrent.getOrderStatus())) {
            throw new IllegalStateException("Cannot book a rented house");
        }
        long days = ChronoUnit.DAYS.between(orderpay, payday);
        if (days <= 0) {
            throw new IllegalArgumentException("Invalid rental period");
        }

        double totalPrice = days * forrent.getRentingprice();

        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setForrent(forrent);
        bookingDetail.setUsers(new User(userId));
        bookingDetail.setTotalPrice(totalPrice);
        bookingDetail.setStatus(new StatusHouse(1L)); // Assuming 1 is the initial booking status
        return bookingDetailRepository.save(bookingDetail);
    }

     */

    @Override
    public BookingDetail createBookingDetail(Long forrentId, Long userId, LocalDate orderpay, LocalDate payday) {
        Forrent forrent = forrentRepository.findById(forrentId).orElseThrow(() -> new IllegalArgumentException("House not found"));
        if ("Rented".equals(forrent.getOrderStatus())) {
            throw new IllegalStateException("Cannot book a rented house");
        }
        long days = ChronoUnit.DAYS.between(orderpay, payday);
        if (days <= 0) {
            throw new IllegalArgumentException("Invalid rental period");
        }
        double totalPrice = days * forrent.getRentingprice();
        BookingDetail bookingDetail = new BookingDetail();
        bookingDetail.setForrent(forrent);
        bookingDetail.setUsers(new User(userId));
        bookingDetail.setTotalPrice(totalPrice);
        bookingDetail.setStatus(new StatusHouse(1L)); // Assuming 1 is the initial booking status
        BookingDetail savedBooking = bookingRepository.save(bookingDetail);
        // Send email notification
        String subject = "Booking Confirmation";
        String message = "Dear user, your booking for house " + forrent.getNamehouse() +
                " from " + orderpay + " to " + payday +
                " has been confirmed. Total price: " + totalPrice;
        emailService.sendEmail("user-email@example.com", subject, message);
        return savedBooking;
    }

    private void updateBookingDetailFromDTO(BookingDetail bookingDetail, BookingDTO bookingDTO) {
        Forrent forrent = forrentRepository.findById(bookingDTO.getForrent())
                .orElseThrow(() -> new IllegalArgumentException("House not found"));
        User user = usersRepository.findById(bookingDTO.getUsers())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        double rent = ChronoUnit.DAYS.between(bookingDTO.getOrderday(), bookingDTO.getPayday()) * forrent.getRentingprice();

        bookingDetail.setForrent(forrent);
        bookingDetail.setUsers(user);
        bookingDetail.setOrderday(bookingDTO.getOrderday());
        bookingDetail.setPayday(bookingDTO.getPayday());
        bookingDetail.setRent(rent);
        bookingDetail.setDeposite(bookingDTO.getDeposite());
        bookingDetail.setPayment(paymentRepository.findById(bookingDTO.getPayment())
                .orElseThrow(() -> new IllegalArgumentException("Payment method not found")));
        bookingDetail.setStatus(statusHouseRepository.findById(bookingDTO.getStatus())
                .orElseThrow(() -> new IllegalArgumentException("Status not found")));
    }

    private boolean isBookingPeriodAvailable(BookingDTO bookingDTO) {
        Forrent forrent = forrentRepository.findById(bookingDTO.getForrent())
                .orElseThrow(() -> new IllegalArgumentException("House not found"));
        return bookingDetailRepository.findAllByForrent(forrent).stream().noneMatch(bookingDetail ->
                !((bookingDTO.getPayday().isBefore(bookingDetail.getOrderday()) || bookingDTO.getPayday().isEqual(bookingDetail.getOrderday())) ||
                        (bookingDTO.getOrderday().isAfter(bookingDetail.getPayday()) || bookingDTO.getOrderday().isEqual(bookingDetail.getPayday())))
        );
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
        return bookingDetailRepository.findAllByForrent(forrentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("House not found")));
    }

    @Override
    public List<BookingResDTO> findAllBookingsByUserId(Long userId) {
        return bookingRepository.findAllBookingByUserId(userId);
    }

    @Override
    public void checkin(Long bookingId, Long statusHouseId) throws Exception {
        Optional<BookingDetail> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            BookingDetail bookingDetail = optionalBooking.get();
            if (statusHouseId == 2) { // Check-in
                bookingDetail.setStatus(new StatusHouse(2L)); // Assuming 2 is the status for check-in
                bookingRepository.save(bookingDetail);
            } else {
                throw new Exception("Invalid statusHouseId for check-in");
            }
        } else {
            throw new Exception("Booking not found");
        }
    }

    @Override
    public void checkout(Long bookingId, Long statusHouseId) throws Exception {
        Optional<BookingDetail> optionalBooking = bookingRepository.findById(bookingId);
        if (optionalBooking.isPresent()) {
            BookingDetail bookingDetail = optionalBooking.get();
            if (statusHouseId == 3) { // Check-out
                bookingDetail.setStatus(new StatusHouse(3L)); // Assuming 3 is the status for check-out
                bookingRepository.save(bookingDetail);
            } else {
                throw new Exception("Invalid statusHouseId for check-out");
            }
        } else {
            throw new Exception("Booking not found");
        }
    }

    @Override
    public void remove(Long id) {
        bookingDetailRepository.deleteById(id);
    }
}