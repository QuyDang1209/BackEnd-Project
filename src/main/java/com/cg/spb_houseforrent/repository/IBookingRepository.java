package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IBookingRepository extends JpaRepository<BookingDetail, Long> {

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(b) FROM BookingDetail b")
    Page<ForrentResDTO> findAllBookingDetail(Pageable pageable);
}
