package pdp.uz.springlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.springlesson1task1.entity.Address;
import pdp.uz.springlesson1task1.payload.ApiResponse;
import pdp.uz.springlesson1task1.repository.AddressRepo;

import java.util.Optional;

/**
 * @author Hulkar,
 * @time вс 12:40.
 * @project 13.03.2022
 */
@Service
public class AddressService {

    @Autowired
    AddressRepo addressRepo;

    public ApiResponse allAddress() {
        return new ApiResponse(true, "All address", addressRepo.findAll());

    }

    public ApiResponse addOrEdit(Address address) {
        try {
            Address repo = new Address();
            if (address.getId() != null) {
                repo = addressRepo.getById(address.getId());
            }
            repo.setHomeNumber(address.getHomeNumber());
            repo.setStreet(address.getStreet());
            addressRepo.save(repo);
            return new ApiResponse(true, address.getId() != null ? "Edited" : "Saved");
        } catch (Exception e) {
            e.printStackTrace();
            return new ApiResponse(false, "Error");
        }

    }

    public ApiResponse delete(Long id) {
        try {
            addressRepo.deleteById(id);
        }catch (Exception e){
            return new ApiResponse(false,"Address not deleted");
        }
            return new ApiResponse(true,"Address  deleted");
    }

    public ApiResponse getById(Long id) {
        Optional<Address> optional = addressRepo.findById(id);
        return optional.map(address -> new ApiResponse(true, "id=", address))
                .orElseGet(() -> new ApiResponse(false, "Address not found"));
    }
}
