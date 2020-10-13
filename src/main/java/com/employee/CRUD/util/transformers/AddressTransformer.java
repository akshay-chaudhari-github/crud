package com.employee.CRUD.util.transformers;

import com.employee.CRUD.domain.Employee;
import com.employee.CRUD.dto.common.AddressDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AddressTransformer implements DtoTransformer<Employee, AddressDto> {


    @Override
    public AddressDto toDto(Employee entity) {
        AddressDto addressDto = new AddressDto();
        if(entity != null){
            addressDto.setAddressLine1(entity.getAddressLine1());
            addressDto.setAddressLine2(entity.getAddressLine2());
            addressDto.setCity(entity.getCity());
            addressDto.setCountry(entity.getCountry());
            addressDto.setState(entity.getState());
            addressDto.setZipCode(entity.getZipCode());
        }
        return addressDto;
    }

    @Override
    public Employee toEntity(AddressDto dto) {
        Employee employee = new Employee();
        if(dto != null){
            employee.setAddressLine1(dto.getAddressLine1());
            employee.setAddressLine2(dto.getAddressLine2());
            employee.setCity(dto.getCity());
            employee.setCountry(dto.getCountry());
            employee.setState(dto.getState());
            employee.setZipCode(dto.getZipCode());
        }
        return employee;
    }

    @Override
    public Set<AddressDto> toDtoSet(Set<Employee> entityList) {
        return entityList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<Employee> toEntitySet(Set<AddressDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toSet());
    }

    @Override
    public List<AddressDto> toDtoList(List<Employee> entityList) {
        return entityList
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> toEntityList(List<AddressDto> dtoList) {
        return dtoList
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}
