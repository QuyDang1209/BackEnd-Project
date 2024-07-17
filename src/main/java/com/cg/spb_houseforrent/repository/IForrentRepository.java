package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.ForrentDTO;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IForrentRepository extends JpaRepository<Forrent, Long> {
    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) FROM Forrent f")
    List<ForrentResDTO> findAllForrentDTO();

    @Query("select new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) \n" +
            "from Forrent f \n" +
            "where not exists ( \n" +
            "select b \n" +
            "from BookingDetail b \n" +
            "where f.id = b.forrent.id \n" +
            "and b.status.id = 1 \n" +
            "and (:checkIn < b.payday and :checkOut > b.orderday) \n" +
            ")")
    Page<ForrentResDTO> filterHomePage(Pageable pageable, @Param("checkIn")LocalDate checkIn, @Param("checkOut")LocalDate checkOut);
}
