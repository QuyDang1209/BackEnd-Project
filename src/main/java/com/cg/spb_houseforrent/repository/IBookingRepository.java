package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.dto.res.BookingResDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IBookingRepository extends JpaRepository<BookingDetail, Long> {

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(b) FROM BookingDetail b")
    Page<ForrentResDTO> findAllBookingDetail(Pageable pageable);

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.BookingResDTO(b) \n" +
            "FROM Forrent f JOIN BookingDetail b on f.id = b.forrent.id \n" +
            "WHERE f.users.id = :id")
    List<BookingResDTO> findAllBookingByUserId(@Param("id") Long id);

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.BookingResDTO(b) " +
            "FROM BookingDetail b WHERE b.users.id = :userId")
    List<BookingResDTO> findByUserId(@Param("userId") Long userId);

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.BookingResDTO(b) " +
            "FROM BookingDetail b WHERE b.forrent.id = :forrentId")
    List<BookingResDTO> findByForrentId(@Param("forrentId") Long forrentId);

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.BookingResDTO(b) " +
            "FROM BookingDetail b WHERE b.orderday BETWEEN :orderday AND :payday")
    List<BookingResDTO> findByOrderdayBetween(@Param("orderday") LocalDate orderday, @Param("payday") LocalDate payday);
}