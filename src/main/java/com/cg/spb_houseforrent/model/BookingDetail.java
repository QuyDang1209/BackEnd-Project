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
@Table(name = "bookings")
public class
BookingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "forrents_id")
    private Forrent forrent;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private User users;
    private LocalDate orderday;
    private LocalDate payday;
    private Double rent;
    private Double deposite;
    @ManyToOne
    @JoinColumn(name = "payment")
    private Payment payment;

}
