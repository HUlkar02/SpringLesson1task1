package pdp.uz.springlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.springlesson1task1.entity.Company;

/**
 * @author Hulkar,
 * @time вс 12:27.
 * @project 13.03.2022
 */
public interface CompanyRepo extends JpaRepository<Company,Long> {
}
