package pdp.uz.springlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.springlesson1task1.payload.ApiResponse;
import pdp.uz.springlesson1task1.payload.WorkerDto;
import pdp.uz.springlesson1task1.service.WorkerService;

/**
 * @author Hulkar,
 * @time пн 11:50.
 * @project 14.03.2022
 */

@RestController
@RequestMapping("/api/worker")
public class WorkerController {

    @Autowired
    WorkerService workerService;


    @GetMapping("/all")
    public HttpEntity<?> getAllWorkers() {
        return ResponseEntity.ok(workerService.getAll());
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getByIdWorker(@PathVariable Long id) {
        ApiResponse apiResponse = workerService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete")
    public HttpEntity<?> deleteWorker(@PathVariable Long id){
        ApiResponse apiResponse = workerService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }


    @PostMapping("/add")
    public HttpEntity<?>addCompany(@RequestBody WorkerDto workerDto){
        ApiResponse newWorker = workerService.add(workerDto);
        return ResponseEntity.status(201).body(newWorker);

    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?>editCompany(@PathVariable Long id,@RequestBody  WorkerDto workerDto){
        ApiResponse editingWorker = workerService.edit(id, workerDto);
        return ResponseEntity.status(editingWorker!=null?202:409).body(editingWorker);
    }

}
