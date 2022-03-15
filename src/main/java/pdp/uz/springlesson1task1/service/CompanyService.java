package pdp.uz.springlesson1task1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pdp.uz.springlesson1task1.entity.Address;
import pdp.uz.springlesson1task1.entity.Company;
import pdp.uz.springlesson1task1.payload.ApiResponse;
import pdp.uz.springlesson1task1.payload.CompanyDto;
import pdp.uz.springlesson1task1.repository.AddressRepo;
import pdp.uz.springlesson1task1.repository.CompanyRepo;

import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Hulkar,
 * @time вс 12:40.
 * @project 13.03.2022
 */
@Service
public class CompanyService {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    AddressRepo addressRepo;


    public ApiResponse getAll() {
        return new ApiResponse(true, "All", companyRepo.findAll());
    }

    public ApiResponse getById(Long id) {
        Optional<Company> optional = companyRepo.findById(id);
        return optional.map(company -> new ApiResponse(true, "id=", company))
                .orElseGet(() -> new ApiResponse(false, "Company not found"));
    }

    public ApiResponse delete(Long id) {
        try {
            companyRepo.deleteById(id);
        } catch (Exception e) {
            return new ApiResponse(false, "Company not deleted");
        }
        return new ApiResponse(true, "Company  deleted");
    }

    public ApiResponse add(CompanyDto companyDto) {
        boolean exists = companyRepo.existsById(companyDto.getAddressId());
        if (exists)
            return new ApiResponse(false, "Company already exist");

        Optional<Address> optional = addressRepo.findById(companyDto.getAddressId());
        if (optional.isPresent()) {
            Company company = new Company(null, companyDto.getCorpName(), companyDto.getDirectorName(), optional.get());
            companyRepo.save(company);
            return new ApiResponse(true, "Company added");

        }
        return new ApiResponse(false, "Address not found");
    }

    public ApiResponse edit(Long id, CompanyDto companyDto) {
        Optional<Company> optional = companyRepo.findById(id);
        if (optional.isPresent()) {
            Optional<Address> optionalAddress = addressRepo.findById(companyDto.getAddressId());
            if (optionalAddress.isPresent()) {
                try {
                    Company editedCompany = companyRepo.save(
                            new Company(null, companyDto.getCorpName(),
                                    companyDto.getDirectorName(),
                                    optionalAddress.get()));
                    return new ApiResponse(true, "Company edited");
                } catch (Exception e) {
                    return new ApiResponse(false, "Company already exist!!");
                }
            }
            return new ApiResponse(false, "Address not found");
        }
        return new ApiResponse(false, "Company not found");
    }
}
