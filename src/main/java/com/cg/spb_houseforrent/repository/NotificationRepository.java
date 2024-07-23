package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByOrderByDateDesc();
//    List<Notification> findByLandlordIdOrderByTimestampDesc(Long landlordId);
    @Query("SELECT n FROM Notification n WHERE n.landlordId = :landlordId ORDER BY n.timestamp DESC")
    List<Notification> findByLandlordIdOrderByTimestampDesc(@Param("landlordId") Long landlordId);
}
