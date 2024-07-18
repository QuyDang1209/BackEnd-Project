package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Rental;
import com.cg.spb_houseforrent.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRentalRepository extends JpaRepository<Rental, Long> {
    @Query("SELECT r FROM Rental r WHERE r.users  = :users")
    List<Rental> findByUser(@Param("users") User users);
}
