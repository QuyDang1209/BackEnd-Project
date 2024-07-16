package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookingDetailRepository extends JpaRepository<BookingDetail, Long> {
    List<BookingDetail> findAllByForrent(Forrent forrent);
}
