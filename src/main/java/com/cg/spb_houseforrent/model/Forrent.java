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
@Table(name = "forrents")
public class Forrent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String namehouse;
    private String address;
    @OneToMany(mappedBy = "forrents")
    private Set<ImgHouse> img;
    private Long bedroom;
    private Long bathroom;
    private Double rentingprice;
    private LocalDate startDate;
    private LocalDate endDate;
    private String orderStatus;
    private String decription;
    @ManyToOne
    @JoinColumn(name = "typehouses_id")
    private TypeHouse type;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;

}
