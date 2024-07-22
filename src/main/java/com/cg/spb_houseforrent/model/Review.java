package com.cg.spb_houseforrent.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "forrent_id")
    private Forrent forrent;

    @Setter
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;  // Assuming User entity exists
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment_id;
    private Integer rating;
    private LocalDate reviewDate;

    public void setContent(String content) {
        if (this.comment_id != null) {
            this.comment_id.setContent(content);
        }
    }
    public String getContent() {
        return this.comment_id != null ? this.comment_id.getContent() : null;
    }
}
