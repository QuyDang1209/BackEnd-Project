package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    List<Notification> findByOrderByDateDesc();
    List<Notification> findByLandlordIdOrderByTimestampDesc(Long landlordId);
}
