package apptive.attendence.entity;

import jakarta.persistence.*;

@Entity
public class InProject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users user;

    private String teamName;
}
