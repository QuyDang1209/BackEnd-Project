package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.StatusHouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IStatusHouseRepository extends JpaRepository<StatusHouse, Long> {
}
