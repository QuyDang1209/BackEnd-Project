package com.cg.spb_houseforrent.model.dto;

import com.cg.spb_houseforrent.model.ActiveStatus;
import com.cg.spb_houseforrent.model.Role;
import com.cg.spb_houseforrent.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String name;
    private String address;
    private LocalDate dob;
    private String email;
    private String phone;
    private String password;
    private Long role;
    private Long active;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.address = user.getAddress();
        this.dob = user.getDob();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.password = user.getPassword();

        Optional<Role> optional =  user.getRole().stream().findFirst();
        if (optional.isPresent()) {
            this.role = optional.get().getId();
        }
        this.active = user.getActive().getId();
    }
}
