package com.employee.CRUD.controller;

import com.employee.CRUD.dto.common.RestResponse;
import com.employee.CRUD.dto.employee.EmployeeRequestDto;
import com.employee.CRUD.service.EmployeeService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/employees")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @ApiOperation(value = "Add an Employee")
    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<RestResponse> addEmployee(@Valid @RequestBody EmployeeRequestDto employeeRequestDto){
        RestResponse restResponse = new RestResponse();
        HttpStatus status = HttpStatus.CONFLICT;
        if (employeeRequestDto != null) {
            Optional<RestResponse> response = employeeService.addEmployee(employeeRequestDto);
            if (response.isPresent()) {
                restResponse = response.get();
                if (restResponse.isSuccess()) {
                    status = HttpStatus.CREATED;
                    restResponse.setMessage("Employee added");
                } else {
                    restResponse.setMessage("Unable to Create employee");
                }
            } else {
                restResponse.setSuccess(false);
                restResponse.setMessage("Unable to create employee");
                restResponse.addData("employee", null);
            }
        } else {
            restResponse.setSuccess(false);
            restResponse.setMessage("Invalid input");
            restResponse.addData("employee", null);
        }
        return new ResponseEntity<>(restResponse, status);
    }

    @ApiOperation(value = "Retrieve an employee")
    @GetMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<RestResponse> retrieveEmployee(@RequestParam String id){
        RestResponse restResponse = new RestResponse();
        HttpStatus status = HttpStatus.CONFLICT;
        if (id != null) {
            Optional<RestResponse> optionalRestResponse = employeeService.retrieveEmployee(id);
            if(optionalRestResponse.isPresent()){
                restResponse = optionalRestResponse.get();
                if(restResponse.isSuccess()) {
                    status = HttpStatus.OK;
                    restResponse = optionalRestResponse.get();
                }else{
                    status = HttpStatus.NOT_FOUND;
                    restResponse = optionalRestResponse.get();
                }
            }else{
                status = HttpStatus.NOT_FOUND;
                restResponse = optionalRestResponse.get();
            }
        } else {
            restResponse.setSuccess(false);
            restResponse.setMessage("Invalid ID");
            restResponse.addData("employee", null);
        }
        return new ResponseEntity<>(restResponse, status);
    }

    @ApiOperation(value = "Retrieve all employees")
    @GetMapping()
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<RestResponse> retrieveAllEmployee(){
        RestResponse restResponse = new RestResponse();
        HttpStatus status = HttpStatus.CONFLICT;
        Optional<RestResponse> optionalRestResponse = employeeService.retrieveEmployees();
        if(optionalRestResponse.isPresent()){
            restResponse = optionalRestResponse.get();
            if(restResponse.isSuccess()){
                status = HttpStatus.OK;
            }else{
                status = HttpStatus.NO_CONTENT;
            }
        }else{
            status = HttpStatus.NOT_FOUND;
            restResponse.setSuccess(false);
            restResponse.setMessage("NO_DATA_FOUND");
            restResponse.addData("employee", null);
        }
        return new ResponseEntity<>(restResponse, status);
    }

    @ApiOperation(value = "Delete an Employee")
    @DeleteMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<RestResponse> deleteEmployee(@RequestParam String id){
        RestResponse restResponse = new RestResponse();
        HttpStatus status = HttpStatus.CONFLICT;
        Optional<RestResponse> optionalRestResponse = employeeService.deleteEmployee(id);
        if (id != null) {
            if(optionalRestResponse.isPresent()){
                restResponse = optionalRestResponse.get();
                if(restResponse.isSuccess()){
                    status = HttpStatus.OK;
                }else{
                    status = HttpStatus.NOT_FOUND;
                }
            }
        } else {
            restResponse.setSuccess(false);
            restResponse.setMessage("INVALID_INPUT");
            restResponse.addData("employee", null);
        }
        return new ResponseEntity<>(restResponse, status);
    }

    @ApiOperation(value = "Update an Employee")
    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = ""),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
    public ResponseEntity<RestResponse> updateEmployee(@RequestParam String id,@RequestBody EmployeeRequestDto employeeRequestDto){
        RestResponse restResponse = new RestResponse();
        HttpStatus status = HttpStatus.CONFLICT;
        if (id != null && employeeRequestDto != null) {
            Optional<RestResponse> response = employeeService.updateEmployee(id,employeeRequestDto);
            if (response.isPresent()) {
                restResponse = response.get();
                if (restResponse.isSuccess()) {
                    status = HttpStatus.OK;
                } else {
                    status = HttpStatus.CONFLICT;
                }
            } else {
                restResponse.setSuccess(false);
                restResponse.setMessage("Unable to update employee");
                restResponse.addData("employee", null);
            }
        } else {
            restResponse.setSuccess(false);
            restResponse.setMessage("INVALID_INPUT");
            restResponse.addData("employee", null);
        }
        return new ResponseEntity<>(restResponse, status);
    }
}
