package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.ActiveStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IActiveStatusRepository extends JpaRepository<ActiveStatus, Long> {
}
