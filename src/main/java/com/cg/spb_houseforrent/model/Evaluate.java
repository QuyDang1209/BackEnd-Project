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
@Table(name = "evaluate")
public class Evaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;
    private String img;
    private Long rate;
    private String comment;
    @ManyToOne
    @JoinColumn(name = "forrent_id")
    private Forrents forrents;
    @ManyToOne
    @JoinColumn(name = "evaluatestatus")
    private EvaluateStatus evaluateStatus;

}
