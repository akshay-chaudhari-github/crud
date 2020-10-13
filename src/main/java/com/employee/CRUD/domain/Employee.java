package com.employee.CRUD.domain;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "employee", schema = "EMPLOYEE_CRUD")
@Data
public class Employee extends BaseEntity {

    private String email;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="employee_id")
    private String employeeId;

    @Column(name="address_line1")
    private String addressLine1;

    @Column(name="address_line2")
    private String addressLine2;

    private String city;

    private String state;

    private String country;

    @Column(name="zip_code")
    private String zipCode;

    @Column(name="is_active")
    private boolean isActive;

    @PrePersist
    private void setEmployeeId(){
        this.employeeId = "EMP" + UUID.randomUUID().toString().substring(0,4);
    }

}
