package pdp.uz.springlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.springlesson1task1.payload.ApiResponse;
import pdp.uz.springlesson1task1.payload.CompanyDto;
import pdp.uz.springlesson1task1.payload.DepartmentDto;
import pdp.uz.springlesson1task1.repository.CompanyRepo;
import pdp.uz.springlesson1task1.service.DepartmentService;

/**
 * @author Hulkar,
 * @time вс 22:59.
 * @project 13.03.2022
 */
@RestController
@RequestMapping("/api/department")
public class DepartmentController {

    @Autowired
    DepartmentService departmentService;

   @GetMapping("/all")
    public HttpEntity<?>getAllDepartment(){
       return ResponseEntity.ok(departmentService.allDepartment());
   }
    @GetMapping("/{id}")
    public HttpEntity<?> getByIdDepartment(@PathVariable Long id) {
        ApiResponse apiResponse = departmentService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete")
    public HttpEntity<?> deleteDepartment(@PathVariable Long id){
        ApiResponse apiResponse = departmentService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }


    @PostMapping("/add")
    public HttpEntity<?>addCompany(@RequestBody DepartmentDto departmentDto){
        ApiResponse newDepartment = departmentService.add(departmentDto);
        return ResponseEntity.status(201).body(newDepartment);

    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?>editCompany(@PathVariable Long id,@RequestBody DepartmentDto departmentDto){
        ApiResponse editingDepartment = departmentService.edit(id, departmentDto);
        return ResponseEntity.status(editingDepartment!=null?202:409).body(editingDepartment);
    }

}
