package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.Message;
import com.cg.spb_houseforrent.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody Message message) {
        messageService.sendMessage(message);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/unread-count/{userId}")
    public ResponseEntity<Integer> getUnreadMessageCount(@PathVariable Long userId) {
        int count = messageService.getUnreadMessageCount(userId);
        return ResponseEntity.ok(count);
    }

    @GetMapping("/messages/{userId}")
    public ResponseEntity<List<Message>> getMessages(@PathVariable Long userId) {
        List<Message> messages = messageService.getMessages(userId);
        return ResponseEntity.ok(messages);
    }
}
