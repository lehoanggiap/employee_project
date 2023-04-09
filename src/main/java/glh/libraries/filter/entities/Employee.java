package glh.libraries.filter.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 30)
    private String name;

    private boolean male;

    private LocalDate birthdate;

    @Column(length = 30)
    private String address;

    @Column(length = 10)
    private String phone;

    @Column(length = 30)
    private String department;

    @Column(length = 3)
    private String title;

    private long wageRate;
}
