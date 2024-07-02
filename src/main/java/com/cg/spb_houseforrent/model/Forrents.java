package com.cg.spb_houseforrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "forrents")
public class Forrents {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String address;
    private String img;
    private String decription;
    @OneToOne
    @JoinColumn(name = "typehouses_id")
    private TypeHouses type;
    @OneToOne
    @JoinColumn(name = "users_id")
    private Users users;

}
