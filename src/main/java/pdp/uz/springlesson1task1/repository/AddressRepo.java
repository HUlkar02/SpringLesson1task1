package pdp.uz.springlesson1task1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pdp.uz.springlesson1task1.entity.Address;

/**
 * @author Hulkar,
 * @time вс 12:27.
 * @project 13.03.2022
 */
public interface AddressRepo extends JpaRepository<Address,Long> {

}
