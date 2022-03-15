package pdp.uz.springlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.springlesson1task1.payload.ApiResponse;
import pdp.uz.springlesson1task1.payload.CompanyDto;
import pdp.uz.springlesson1task1.service.CompanyService;

/**
 * @author Hulkar,
 * @time вс 13:54.
 * @project 13.03.2022
 */
@RestController
@RequestMapping("/api/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @GetMapping("/all")
    public HttpEntity<?> getAllCompanies() {
        return ResponseEntity.ok(companyService.getAll());
    }

    @GetMapping("/{id}")
            public HttpEntity<?> getByIdCompany(@PathVariable Long id) {
        ApiResponse apiResponse = companyService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete")
    public HttpEntity<?> deleteCompany(@PathVariable Long id){
        ApiResponse apiResponse = companyService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }


    @PostMapping("/add")
    public HttpEntity<?>addCompany(@RequestBody CompanyDto companyDto){
        ApiResponse newCompany = companyService.add(companyDto);
        return ResponseEntity.status(201).body(newCompany);

    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?>editCompany(@PathVariable Long id,@RequestBody CompanyDto companyDto){
        ApiResponse editingCompany = companyService.edit(id, companyDto);
        return ResponseEntity.status(editingCompany!=null?202:409).body(editingCompany);
    }
}
