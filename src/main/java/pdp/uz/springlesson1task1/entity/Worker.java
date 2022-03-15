package pdp.uz.springlesson1task1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @author Hulkar,
 * @time вс 11:53.
 * @project 13.03.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String phoneNumber;

    @OneToOne
    private Address address;

    @ManyToOne
    private Department department;

    public Worker(String name, String phoneNumber, Address address, Department department) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.department = department;
    }
}
