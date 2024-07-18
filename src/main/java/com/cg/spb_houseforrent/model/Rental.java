package com.cg.spb_houseforrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime rentalTime;
    private String namehouse;
    private Double totalOrder;
    private String orderStatus;
    private Long daysUntilRental;
    @ManyToOne
    @JoinColumn(name = "users_id",nullable = false)
    private User users;
    @ManyToOne
    @JoinColumn(name = "forrents_id")
    private Forrent forrents;
    @ManyToOne
    @JoinColumn(name="evaluatestatus_id")
    private EvaluateStatus evaluateStatus;
}
