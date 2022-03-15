package pdp.uz.springlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Hulkar,
 * @time пн 9:52.
 * @project 14.03.2022
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private String name;
    private Long companyId;


}
