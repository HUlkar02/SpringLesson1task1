package pdp.uz.springlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.springlesson1task1.entity.Worker;

/**
 * @author Hulkar,
 * @time вс 12:29.
 * @project 13.03.2022
 */
public interface WorkerRepo extends JpaRepository<Worker,Long> {
boolean existsByDepartmentIdAndAddressId(Long department_id, Long address_id);
}
