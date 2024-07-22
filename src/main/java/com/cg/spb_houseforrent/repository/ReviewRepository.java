package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    @Query("SELECT r FROM Review r WHERE r.forrent.id = :forrentId")
    List<Review> findByForrentId(Long forrentId);
    @Query("SELECT r FROM Review r WHERE r.forrent.id = :forrentId AND r.user.id = :userId")
    List<Review> findByForrentIdAndUserId(
            @Param("forrentId") Long forrentId,
            @Param("userId") Long userId
    );
}
