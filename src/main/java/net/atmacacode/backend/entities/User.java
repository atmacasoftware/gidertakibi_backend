package net.atmacacode.backend.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.atmacacode.backend.core.validation.UniqueEmail;

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
    @Email
    @UniqueEmail(message = "{backend.constraint.email.notUnique}")
    private String email;

    @Column(length = 100)
    @Size(min = 1, max = 100, message = "{backend.constraint.firstname.sizeWarning}")
    private String first_name;

    @Column(length = 100)
    @Size(min = 1, max = 100, message = "{backend.constraint.lastname.sizeWarning}")
    private String last_name;

    @Column(length = 11, nullable = true)
    @Size(min = 11, max = 11, message = "{backend.constraint.mobile.sizeWarning}")
    private String mobile;

    @Column
    private String password;
}
