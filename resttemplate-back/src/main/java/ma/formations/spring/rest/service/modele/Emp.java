package ma.formations.spring.rest.service.modele;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;

@Entity @NoArgsConstructor @Data
public class Emp implements Serializable {
    @GeneratedValue @Id
    private Long id;
    @Column(name = "NAME", unique = true, length = 30)
    private String firstName;
    private Double salaire;
    private String fonction;
    @Transient
    private Date dateAnniversaire;
    public Emp(String name, Double salary, String fonction) {
        this.firstName = name;
        this.salaire = salary;
        this.fonction = fonction;
    }
}