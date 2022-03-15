package pdp.uz.springlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.springlesson1task1.entity.Address;
import pdp.uz.springlesson1task1.entity.Company;
import pdp.uz.springlesson1task1.entity.Department;
import pdp.uz.springlesson1task1.entity.Worker;
import pdp.uz.springlesson1task1.payload.ApiResponse;
import pdp.uz.springlesson1task1.payload.DepartmentDto;
import pdp.uz.springlesson1task1.payload.WorkerDto;
import pdp.uz.springlesson1task1.repository.AddressRepo;
import pdp.uz.springlesson1task1.repository.DepartamentRepo;
import pdp.uz.springlesson1task1.repository.WorkerRepo;

import java.util.Optional;

/**
 * @author Hulkar,
 * @time вс 12:41.
 * @project 13.03.2022
 */
@Service
public class WorkerService {

    @Autowired
    DepartamentRepo departamentRepo;


    @Autowired
    AddressRepo addressRepo;

    @Autowired
    WorkerRepo workerRepo;




    public ApiResponse getAll() {
        return new ApiResponse(true, "All", workerRepo.findAll());

    }

    public ApiResponse getById(Long id) {
        Optional<Worker> workerOptional = workerRepo.findById(id);
        return workerOptional.map(worker -> new ApiResponse(true, "id=", worker))
                .orElseGet(() -> new ApiResponse(false, "Worker not found"));
    }

    public ApiResponse delete(Long id) {
        try {

            workerRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse(false, "Worker not found");
        }
        return new ApiResponse(true, "Worker deleted");
    }

    public ApiResponse add(WorkerDto workerDto) {
        boolean exists = workerRepo.existsByDepartmentIdAndAddressId(workerDto.getDepartmentId(), workerDto.getAddressId());
        if (exists)
            return new ApiResponse(false,"Worker already exist");

        Optional<Address> optionalAddress = addressRepo.findById(workerDto.getAddressId());
        Optional<Department> optionalDepartment = departamentRepo.findById(workerDto.getDepartmentId());
        if (optionalAddress.isPresent() && optionalDepartment.isPresent()){
            Worker worker= new Worker(null, workerDto.getName(), workerDto.getPhoneNumber(), optionalAddress.get(), optionalDepartment.get());
        workerRepo.save(worker);
        return new ApiResponse(true,"Worker added");
        }
        return new ApiResponse(false,"Address or Department not found");
    }

    public  ApiResponse edit(Long id, WorkerDto workerDto) {
        Optional<Worker> optional = workerRepo.findById(id);
        if (optional.isPresent()) {
            Optional<Address> optionalAddress = addressRepo.findById(workerDto.getAddressId());
            Optional<Department> optionalDepartment = departamentRepo.findById(workerDto.getDepartmentId());
            if (optionalAddress.isPresent() && optionalDepartment.isPresent()) {
                try {
                    workerRepo.save(new Worker(workerDto.getName(),workerDto.getPhoneNumber(),optionalAddress.get(),optionalDepartment.get()));
                    return new ApiResponse(true, "Worker edited");
                } catch (Exception e) {
                    return new ApiResponse(false, "Worker already exist!!");
                }
            }
            return new ApiResponse(false, "Worker not found");
        }
        return new ApiResponse(false, "Department OR Address not found");
    }

}
