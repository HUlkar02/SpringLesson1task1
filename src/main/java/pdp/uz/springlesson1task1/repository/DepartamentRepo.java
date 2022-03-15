package pdp.uz.springlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.springlesson1task1.entity.Department;

/**
 * @author Hulkar,
 * @time вс 12:29.
 * @project 13.03.2022
 */
public interface DepartamentRepo extends JpaRepository<Department,Long> {
}
