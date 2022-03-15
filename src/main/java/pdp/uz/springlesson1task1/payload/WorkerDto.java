package pdp.uz.springlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Hulkar,
 * @time пн 13:38.
 * @project 14.03.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkerDto {
    private String name;
    private String  phoneNumber;
    private Long addressId;
    private Long departmentId;
}
