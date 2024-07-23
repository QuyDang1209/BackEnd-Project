package com.cg.spb_houseforrent.controller;

import com.cg.spb_houseforrent.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/test")
@CrossOrigin("*")
public class WebSocketController {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @PostMapping("")
    public ResponseEntity<?> showTest(@RequestBody User user){
        messagingTemplate.convertAndSend("/topic/messages", "Người dùng có địa chỉ email: " +user.getEmail() +" muốn trở thành người cho thuê nhà");
        return new ResponseEntity<>("AAA", HttpStatus.OK);
    }
}
