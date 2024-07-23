package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.Notification;
import com.cg.spb_houseforrent.model.dto.NotificationRequestDTO;
import com.cg.spb_houseforrent.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @GetMapping
    public List<Notification> getAllNotifications() {
        return notificationService.getAllNotifications();
    }
    @PostMapping
    public void createNotification(@RequestBody NotificationRequestDTO notificationRequestDTO) {
        notificationService.createNotification(
                notificationRequestDTO.getUser().getId(),
                notificationRequestDTO.getForrent().getId(),
                notificationRequestDTO.getDate(),
                notificationRequestDTO.getType().getId()
        );
    }
    @GetMapping("/{landlordId}")
    public ResponseEntity<List<Notification>> getNotifications(@PathVariable Long landlordId) {
        return ResponseEntity.ok(notificationService.getNotifications(landlordId));
    }
}
