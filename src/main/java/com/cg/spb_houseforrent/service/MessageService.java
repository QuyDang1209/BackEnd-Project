package com.cg.spb_houseforrent.service;

import com.cg.spb_houseforrent.model.Message;
import com.cg.spb_houseforrent.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(Message message) {
        message.setTimestamp(LocalDateTime.now());
        messageRepository.save(message);
    }

    public int getUnreadMessageCount(Long userId) {
        return (int) messageRepository.countByReceiverIdAndIsRead(userId, false);
    }

    public List<Message> getMessages(Long userId) {
        return messageRepository.findByReceiverIdOrderByTimestampDesc(userId);
    }

    public void markMessagesAsRead(Long userId) {
        messageRepository.markMessagesAsIsRead(userId);
    }
}
