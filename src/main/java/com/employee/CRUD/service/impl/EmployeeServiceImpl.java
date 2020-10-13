package com.employee.CRUD.service.impl;

import com.employee.CRUD.domain.Employee;
import com.employee.CRUD.dto.common.RestResponse;
import com.employee.CRUD.dto.employee.EmployeeRequestDto;
import com.employee.CRUD.repository.EmployeeRepository;
import com.employee.CRUD.service.EmployeeService;
import com.employee.CRUD.util.transformers.EmployeeResponseTransformer;
import com.employee.CRUD.util.transformers.EmployeeRequestTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeRequestTransformer employeeRequestTransformer;

    @Autowired
    private EmployeeResponseTransformer employeeResponseTransformer;

    @Override
    public Optional<RestResponse> addEmployee(EmployeeRequestDto employeeRequestDto) {
        RestResponse restResponse = new RestResponse();
        if(employeeRequestDto != null){
            Employee employee = employeeRequestTransformer.toEntity(employeeRequestDto);
            restResponse.addData("employee",employeeRepository.save(employee));
            restResponse.setMessage("SUCCESS");
            restResponse.setSuccess(true);
        }else{
            restResponse.setSuccess(false);
            restResponse.setMessage("FAILURE");
        }
        return Optional.of(restResponse);
    }

    @Override
    public Optional<RestResponse> retrieveEmployee(String id) {
        RestResponse restResponse = new RestResponse();
        Optional<Employee> optionalEmployee = employeeRepository.findById(UUID.fromString(id));
        if(optionalEmployee.isPresent()){
            restResponse.setSuccess(true);
            restResponse.setMessage("SUCCESS");
            restResponse.addData("employee",employeeResponseTransformer.toDto(optionalEmployee.get()));
        }else{
            restResponse.setSuccess(false);
            restResponse.setMessage("FAILURE");
            restResponse.addData("employee",null);
        }
        return Optional.of(restResponse);
    }

    @Override
    public Optional<RestResponse> retrieveEmployees() {
        RestResponse restResponse = new RestResponse();
        List<Employee> employees = employeeRepository.findAll();
        if(!employees.isEmpty()){
            restResponse.setSuccess(true);
            restResponse.setMessage("SUCCESS");
            restResponse.addData("employees",employeeResponseTransformer.toDtoList(employees));
        }else{
            restResponse.setSuccess(false);
            restResponse.setMessage("FAILURE");
            restResponse.addData("employees",null);
        }
        return Optional.of(restResponse);
    }

    @Override
    public Optional<RestResponse> deleteEmployee(String id) {
        RestResponse restResponse = new RestResponse();
        employeeRepository.deleteById(UUID.fromString(id));
        if(employeeRepository.findById(UUID.fromString(id)).isPresent()){
            restResponse.setSuccess(false);
            restResponse.setMessage("FAILURE");
        }else{
            restResponse.setSuccess(true);
            restResponse.setMessage("SUCCESS");
        }
        restResponse.addData("employee",null);
        return Optional.of(restResponse);
    }

    @Override
    public Optional<RestResponse> updateEmployee(String id, EmployeeRequestDto employeeRequestDto) {
        RestResponse restResponse = new RestResponse();
        Optional<Employee> optionalEmployee = employeeRepository.findById(UUID.fromString(id));
        if(optionalEmployee.isPresent()){
            if(employeeRequestDto != null){
                Employee employee = optionalEmployee.get();

                employee.setFirstName(employeeRequestDto.getFirstName() != null && !employeeRequestDto.getFirstName().isEmpty() ?
                        employeeRequestDto.getFirstName() : employee.getFirstName());
                employee.setLastName(employeeRequestDto.getLastName() != null && !employeeRequestDto.getLastName().isEmpty() ?
                        employeeRequestDto.getLastName() : employee.getLastName());
                employee.setEmail(employeeRequestDto.getEmail() != null && !employeeRequestDto.getEmail().isEmpty() ?
                        employeeRequestDto.getEmail() : employee.getEmail());

                if(employeeRequestDto.getAddress() != null){
                    employee.setAddressLine1(employeeRequestDto.getAddress().getAddressLine1() != null && !employeeRequestDto.getAddress().getAddressLine1().isEmpty() ?
                            employeeRequestDto.getAddress().getAddressLine1() : employee.getAddressLine1());
                    employee.setAddressLine2(employeeRequestDto.getAddress().getAddressLine2() != null && !employeeRequestDto.getAddress().getAddressLine2().isEmpty() ?
                            employeeRequestDto.getAddress().getAddressLine2() : employee.getAddressLine2());
                    employee.setEmail(employeeRequestDto.getAddress().getCity() != null && !employeeRequestDto.getAddress().getCity().isEmpty() ?
                            employeeRequestDto.getAddress().getCity() : employee.getCity());
                    employee.setEmail(employeeRequestDto.getAddress().getCountry() != null && !employeeRequestDto.getAddress().getCountry().isEmpty() ?
                            employeeRequestDto.getAddress().getCountry() : employee.getCountry());
                    employee.setEmail(employeeRequestDto.getAddress().getState() != null && !employeeRequestDto.getAddress().getState().isEmpty() ?
                            employeeRequestDto.getAddress().getState() : employee.getState());
                    employee.setEmail(employeeRequestDto.getAddress().getZipCode() != null && !employeeRequestDto.getAddress().getZipCode().isEmpty() ?
                            employeeRequestDto.getAddress().getZipCode() : employee.getZipCode());
                }
                restResponse.setSuccess(true);
                restResponse.setMessage("SUCCESS");
                restResponse.addData("employee",employeeRepository.save(employee));
            }
        }else{
            restResponse.setSuccess(false);
            restResponse.setMessage("INVALID_EMPLOYEE_ID");
            restResponse.addData("employee",null);
        }
        return Optional.of(restResponse);
    }
}
