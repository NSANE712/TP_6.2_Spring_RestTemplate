package ma.formations.spring.resttemplatefront.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import java.util.Date;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EmpVo implements Serializable {
    private Long id;
    private String firstName;
    private Double salaire;
    private String fonction;
    private Date dateAnniversaire;
}
