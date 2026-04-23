package com.example.model;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    private Integer timeBalans=60;
}



/*
        {
        "fullName": "Ali Ustoz",
        "phoneNumber": "+998901112233",
        "password": "password123"
        }
*/
/*

        {
        "id": 1,
        "skillType": "TEACH"
        }
*/


//     GET http://localhost:8080/skills/find-teachers/2
