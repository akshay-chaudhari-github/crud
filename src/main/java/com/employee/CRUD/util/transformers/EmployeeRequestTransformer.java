package com.employee.CRUD.util.transformers;

import com.employee.CRUD.domain.Employee;
import com.employee.CRUD.dto.employee.EmployeeRequestDto;
import com.employee.CRUD.util.transformers.AddressTransformer;
import com.employee.CRUD.util.transformers.DtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeRequestTransformer implements DtoTransformer<Employee, EmployeeRequestDto> {

    @Autowired
    private AddressTransformer addressTransformer;


    @Override
    public EmployeeRequestDto toDto(Employee entity) {
        return null;
    }

    @Override
    public Employee toEntity(EmployeeRequestDto dto) {
        Employee employee = new Employee();
        if (dto != null) {
            employee = addressTransformer.toEntity(dto.getAddress());
            employee.setEmail(dto.getEmail());
            employee.setFirstName(dto.getFirstName());
            employee.setLastName(dto.getLastName());
        }
        return employee;
    }

    @Override
    public Set<EmployeeRequestDto> toDtoSet(Set<Employee> entityList) {
        return entityList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> toEntitySet(Set<EmployeeRequestDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public List<EmployeeRequestDto> toDtoList(List<Employee> entityList) {
        return entityList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> toEntityList(List<EmployeeRequestDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
