package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.Notification;
import com.cg.spb_houseforrent.model.TypeHouse;
import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;
    public List<Notification> getAllNotifications() {
        return notificationRepository.findByOrderByDateDesc();
    }
    public void addNotification(Long landlordId, Long userId, Long forrentId, Long typeId, LocalDate date, LocalDateTime timestamp) {
        Notification notification = new Notification();
        notification.setLandlordId(landlordId);
        User user = new User(userId); // Fetch or create User object as needed
        Forrent forrent = new Forrent(forrentId); // Fetch or create Forrent object as needed
        TypeHouse type = new TypeHouse(typeId); // Fetch or create TypeHouse object as needed
        notification.setDate(date);
        notification.setTimestamp(timestamp);
        notificationRepository.save(notification);
    }
    public void createNotification(Long userId, Long forrentId, LocalDate date, Long typeId) {
        User user = new User(userId); // Fetch or create User object as needed
        Forrent forrent = new Forrent(forrentId); // Fetch or create Forrent object as needed
        TypeHouse type = new TypeHouse(typeId); // Fetch or create TypeHouse object as needed
        Notification notification = new Notification();
        notification.setUser(user);
        notification.setForrent(forrent);
        notification.setDate(date);
        notification.setType(type);
        notificationRepository.save(notification);
    }
    public List<Notification> getNotifications(Long landlordId) {
        return notificationRepository.findByLandlordIdOrderByTimestampDesc(landlordId);
    }
}
