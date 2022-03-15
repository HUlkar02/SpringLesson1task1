package pdp.uz.springlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.springlesson1task1.entity.Address;
import pdp.uz.springlesson1task1.entity.Company;
import pdp.uz.springlesson1task1.entity.Department;
import pdp.uz.springlesson1task1.payload.ApiResponse;
import pdp.uz.springlesson1task1.payload.DepartmentDto;
import pdp.uz.springlesson1task1.repository.CompanyRepo;
import pdp.uz.springlesson1task1.repository.DepartamentRepo;

import java.util.Optional;

/**
 * @author Hulkar,
 * @time вс 12:41.
 * @project 13.03.2022
 */
@Service
public class DepartmentService {

    @Autowired
    DepartamentRepo departamentRepo;

    @Autowired
    CompanyRepo companyRepo;

    public ApiResponse allDepartment() {

        return new ApiResponse(true, "All", departamentRepo.findAll());
    }

    public ApiResponse getById(Long id) {
        Optional<Department> optional = departamentRepo.findById(id);
        return optional.map(department -> new ApiResponse(true, "id=", department))
                .orElseGet(() -> new ApiResponse(false, "Department not found"));
    }

    public ApiResponse delete(Long id) {
        try {
            departamentRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse(false, "Department not deleted");
        }
        return new ApiResponse(true, "Department  deleted");
    }

    public ApiResponse add(DepartmentDto departmentDto) {
        boolean exists = companyRepo.existsById(departmentDto.getCompanyId());
        if (exists)
            return new ApiResponse(false, "Department already exist");

        Optional<Company> optional = companyRepo.findById(departmentDto.getCompanyId());
        if (optional.isPresent()) {
            Department department = new Department(null, departmentDto.getName(), optional.get());
            departamentRepo.save(department);
            return new ApiResponse(true, "Department added");

        }
        return new ApiResponse(false, "Company not found");
    }


    public ApiResponse edit(Long id, DepartmentDto departmentDto) {
        Optional<Department> optional = departamentRepo.findById(id);
        if (optional.isPresent()) {
            Optional<Company> optionalCompany = companyRepo.findById(departmentDto.getCompanyId());
            if (optional.isPresent()) {
                try {
                    departamentRepo.save(new Department(null, departmentDto.getName(), optionalCompany.get()));
                    return new ApiResponse(true, "Department edited");
                } catch (Exception e) {
                    return new ApiResponse(false, "Department already exist!!");
                }
            }
            return new ApiResponse(false, "Compnay not found");
        }
        return new ApiResponse(false, "Department not found");
    }
}
