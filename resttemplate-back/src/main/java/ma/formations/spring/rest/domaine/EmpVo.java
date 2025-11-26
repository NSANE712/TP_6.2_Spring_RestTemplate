package ma.formations.spring.rest.domaine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
//Value Object (VO) <==> DTO : Data Transfer Object
//POJO : Plain Old Java Object
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class EmpVo implements Serializable {
    private Long id;
    private String firstName;
    private Double salaire;
    private String fonction;
    private Date dateAnniversaire;
}
