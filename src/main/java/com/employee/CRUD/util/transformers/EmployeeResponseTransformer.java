package com.employee.CRUD.util.transformers;

import com.employee.CRUD.domain.Employee;
import com.employee.CRUD.dto.employee.EmployeeResponseDto;
import com.employee.CRUD.util.transformers.AddressTransformer;
import com.employee.CRUD.util.transformers.DtoTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class EmployeeResponseTransformer implements DtoTransformer<Employee, EmployeeResponseDto> {

    @Autowired
    private AddressTransformer addressTransformer;

    @Override
    public EmployeeResponseDto toDto(Employee entity) {
        EmployeeResponseDto employeeResponseDto = new EmployeeResponseDto();
        if(entity != null){
            employeeResponseDto.setAddress(addressTransformer.toDto(entity));
            employeeResponseDto.setId(entity.getId().toString());
            employeeResponseDto.setEmail(entity.getEmail());
            employeeResponseDto.setEmployeeId(entity.getEmployeeId());
            employeeResponseDto.setFirstName(entity.getFirstName());
            employeeResponseDto.setLastName(entity.getLastName());
            employeeResponseDto.setActive(entity.isActive());
        }
        return employeeResponseDto;
    }

    @Override
    public Employee toEntity(EmployeeResponseDto dto) {
        return null;
    }

    @Override
    public Set<EmployeeResponseDto> toDtoSet(Set<Employee> entityList) {
        return entityList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> toEntitySet(Set<EmployeeResponseDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public List<EmployeeResponseDto> toDtoList(List<Employee> entityList) {
        return entityList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> toEntityList(List<EmployeeResponseDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
