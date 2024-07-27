package com.cg.spb_houseforrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String address;
    private LocalDate dob;
    private String email;
    private String phone;
    private String password;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> role;
    @ManyToOne
    @JoinColumn(name = "actives_id")
    private ActiveStatus active;

    public User(Long userId) {
        this.id = userId;
    }
}
