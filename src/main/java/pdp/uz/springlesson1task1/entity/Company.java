package pdp.uz.springlesson1task1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Hulkar,
 * @time вс 10:30.
 * @project 13.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String  corpName;

    @Column(nullable = false)
    private String  directorName;

    @OneToOne
    private Address address;
}
