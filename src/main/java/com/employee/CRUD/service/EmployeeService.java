package com.employee.CRUD.service;

import com.employee.CRUD.dto.common.RestResponse;
import com.employee.CRUD.dto.employee.EmployeeRequestDto;

import java.util.Optional;

public interface EmployeeService {

    Optional<RestResponse> addEmployee(EmployeeRequestDto employeeRequestDto);

    Optional<RestResponse> retrieveEmployee(String id);

    Optional<RestResponse> retrieveEmployees();

    Optional<RestResponse> deleteEmployee(String id);

    Optional<RestResponse> updateEmployee(String id,EmployeeRequestDto employeeRequestDto);
}
