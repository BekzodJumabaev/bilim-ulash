package com.example.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Getter
@Setter
@Builder
@Entity
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Users student;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Users teacher;

    private Integer amount;

    private LocalDateTime createdAt;
}
