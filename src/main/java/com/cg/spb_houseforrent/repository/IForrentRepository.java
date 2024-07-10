package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Forrent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IForrentRepository extends JpaRepository<Forrent, Long> {
}
