package com.cg.spb_houseforrent.model.dto;

import com.cg.spb_houseforrent.model.ActiveStatus;
import com.cg.spb_houseforrent.model.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
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
}
