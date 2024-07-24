package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.BookingDetail;
import com.cg.spb_houseforrent.model.dto.SumForrent;
import com.cg.spb_houseforrent.model.dto.res.BookingResDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.data.jpa.domain.Specification.where;

public interface IBookingRepository extends JpaRepository<BookingDetail, Long> {

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(b) FROM BookingDetail b")
    Page<ForrentResDTO> findAllBookingDetail(Pageable pageable);

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.BookingResDTO(b) \n" +
            "FROM Forrent f JOIN BookingDetail b on f.id = b.forrent.id \n" +
            "WHERE f.users.id = :id")
    List<BookingResDTO> findAllBookingByUserId(@Param("id") Long id);


    @Query("select f.namehouse , sum(b.rent) as total " +
            "from Forrent f join BookingDetail b on f.id = b.forrent.id " +
            "where ( b.orderday > :fisrtday and b.payday <= :endday) " +
            "and (f.users.id = :userId )group by b.forrent.id")
    List<SumForrent> getSumForrent(@Param("fisrtday") LocalDate fisrtday,
                                   @Param("endday") LocalDate endday,
                                   @Param("userId") Long userId);

}