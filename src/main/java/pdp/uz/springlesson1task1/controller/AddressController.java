package pdp.uz.springlesson1task1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pdp.uz.springlesson1task1.entity.Address;
import pdp.uz.springlesson1task1.payload.ApiResponse;
import pdp.uz.springlesson1task1.service.AddressService;

/**
 * @author Hulkar,
 * @time вс 12:31.
 * @project 13.03.2022
 */
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping("/all")
    public HttpEntity<?> getAllAddress(){
        return ResponseEntity.ok(addressService.allAddress());
    }

    @PostMapping("/addOrEdit")
    public HttpEntity<?> addAddress(@RequestBody Address address){
        ApiResponse apiResponse = addressService.addOrEdit(address);
        return ResponseEntity.status(apiResponse.getMessage().equals("Saved")?
                201 : apiResponse.getMessage().equals("Edited")?
                202:409).body(apiResponse);
    }

    @DeleteMapping("/delete")
    public HttpEntity<?> delete(@PathVariable Long id){
        ApiResponse apiResponse = addressService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess()?202:409).body(apiResponse);
    }

    @GetMapping("/getById/{id}")
    public HttpEntity<?>getOneAddress(@PathVariable Long id){
        ApiResponse apiResponse = addressService.getById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }



}
