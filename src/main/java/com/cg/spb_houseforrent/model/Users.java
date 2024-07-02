package com.cg.spb_houseforrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private LocalDate dob;
    private String email;
    private String phone;
    private String password;
    @OneToOne
    @JoinColumn(name = "roles_id")
    private Roles role;
    @OneToOne
    @JoinColumn(name = "actives_id")
    private ActiveStatus active;

}
