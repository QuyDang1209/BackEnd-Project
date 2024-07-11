package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.dto.res.ForrentResDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IForrentRepository extends JpaRepository<Forrent, Long> {
    @Query("SELECT new com.cg.spb_houseforrent.model.dto.res.ForrentResDTO(f) FROM Forrent f")
    List<ForrentResDTO> findAllForrentDTO();
}
