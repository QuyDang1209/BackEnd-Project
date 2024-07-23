package com.cg.spb_houseforrent.model.dto;

import com.cg.spb_houseforrent.model.User;
import com.cg.spb_houseforrent.model.Forrent;
import com.cg.spb_houseforrent.model.TypeHouse;
import com.cg.spb_houseforrent.model.Notification;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class NotificationRequestDTO {
    private Long id;
    private User user;
    private Forrent forrent;
    private LocalDate date;
    private TypeHouse type;
    public NotificationRequestDTO(Notification notification) {
        this.id = notification.getId();
        this.user = new User(notification.getUser().getId());
        this.forrent = new Forrent(notification.getForrent().getId());
        this.date = notification.getDate();
        this.type = new TypeHouse(notification.getType().getId());
    }
}
