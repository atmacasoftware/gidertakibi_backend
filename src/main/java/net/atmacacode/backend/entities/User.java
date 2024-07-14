package net.atmacacode.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = {"email"}))
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 255, nullable = false)
    private String email;

    @Column(length = 100)
    private String first_name;

    @Column(length = 100)
    private String last_name;

    @Column(length = 11, nullable = true)
    private String mobile;

    @Column
    private String password;

    @Column
    private boolean activated = true;

    @Column
    private String activationToken;

    @Column
    private int userType;

    @Column
    private String image;

}
