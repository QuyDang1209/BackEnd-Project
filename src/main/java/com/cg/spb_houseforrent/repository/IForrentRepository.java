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
import java.util.Optional;

@Repository
public interface IForrentRepository extends JpaRepository<Forrent, Long> {
    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) FROM Forrent f")
    List<ForrentResDTO> findAllForrentDTO();
    @Query("SELECT f FROM Forrent f JOIN f.type t WHERE t.id = :typeId")
    Iterable<Forrent> findTypeById(@Param("typeId") Long typeId);
    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) FROM Forrent f WHERE f.id = :id")
    Optional<ForrentResDTO> findForrentResDTOById(Long id);
    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) FROM Forrent f WHERE f.users.id = :userId")
    List<ForrentResDTO> findForrentResDTOsByUserId(Long userId);
    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) " +
            "FROM Forrent f " +
            "WHERE (:houseName IS NULL OR f.namehouse LIKE %:houseName%) " +
            "AND (:startDate IS NULL OR f.startDate >= :startDate) " +
            "AND (:endDate IS NULL OR f.endDate <= :endDate) " +
            "AND (:orderStatus IS NULL OR f.orderStatus = :orderStatus)")
    Page<ForrentResDTO> searchBookings(@Param("namehouse") String namehouse,
                                       @Param("startDate") LocalDate startDate,
                                       @Param("endDate") LocalDate endDate,
                                       @Param("orderStatus") String orderStatus,
                                       Pageable pageable);

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) " +
            "FROM Forrent f " +
            "WHERE (:namehouse IS NULL OR f.namehouse LIKE %:namehouse%) " +
            "AND (:orderStatus IS NULL OR f.orderStatus = :orderStatus)")
    Page<ForrentResDTO> searchHouses(@Param("namehouse") String namehouse,
                                     @Param("orderStatus") String orderStatus,
                                     Pageable pageable);
}