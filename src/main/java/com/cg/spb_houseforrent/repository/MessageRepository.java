package com.cg.spb_houseforrent.repository;

import com.cg.spb_houseforrent.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Long> {
    List<Message> findByReceiverIdOrderByTimestampDesc(Long receiverId);

    long countByReceiverIdAndIsRead(Long receiverId, boolean isRead);

    @Modifying
    @Query("UPDATE Message m SET m.isRead = true WHERE m.receiverId = :receiverId AND m.isRead = false")
    void markMessagesAsIsRead(@Param("receiverId") Long receiverId);
}
