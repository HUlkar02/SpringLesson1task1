package pdp.uz.springlesson1task1.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @author Hulkar,
 * @time вс 22:29.
 * @project 13.03.2022
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompanyDto {
    private String  corpName;
    private String  directorName;
    private Long addressId;
}
