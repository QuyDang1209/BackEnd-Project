package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IForrentRepository extends JpaRepository<Forrent, Long> {
    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) FROM Forrent f")
    List<ForrentResDTO> findAllForrentDTO();


    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) FROM Forrent f WHERE f.id = :id")
    Optional<ForrentResDTO> findForrentHouseDTOById(@Param("id") Long id);

    @Query("SELECT f FROM Forrent f JOIN f.type t WHERE t.id = :typeId")
    Iterable<Forrent> findTypeById(@Param("typeId") Long typeId);

    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f)" +
            "FROM Forrent f WHERE f.users.id = :userId")
    Iterable<ForrentResDTO> findForrentResDTOsByUsers_Id(Long userId);

    Page<Forrent> findByNamehouseContainingAndOrderStatus(String namehouse, String orderStatus, Pageable pageable);

    @Query("SELECT f FROM Forrent f WHERE f.namehouse LIKE %:namehouse% AND f.orderStatus = :orderStatus AND f.startDate >= :startDate AND f.endDate <= :endDate")
    Page<Forrent> findSchedules(@Param("namehouse") String namehouse,
                                @Param("startDate") LocalDate startDate,
                                @Param("endDate") LocalDate endDate,
                                @Param("orderStatus") String orderStatus,
                                Pageable pageable);

    @Query("SELECT f FROM Forrent f WHERE f.users.id = :userId")
    Page<ForrentResDTO> findByUserId(Long userId, Pageable pageable);
}