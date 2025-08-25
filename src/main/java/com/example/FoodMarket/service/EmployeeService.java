package com.example.FoodMarket.service;

import com.example.FoodMarket.dto.EmployeeCreateDto;
import com.example.FoodMarket.dto.EmployeeDefaultDto;
import com.example.FoodMarket.dto.UserDefaultDto;
import com.example.FoodMarket.model.Employee;
import com.example.FoodMarket.model.User;
import com.example.FoodMarket.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public EmployeeDefaultDto convertToDefaultDto(Employee employee) {
        return new EmployeeDefaultDto(
                employee.getId(),
                employee.getUser(),
                employee.getSeller()
        );
    }

    public Employee addEmployeeFromDto(EmployeeCreateDto employeeCreateDto) {
        Employee employee = new Employee(
                employeeCreateDto.getUser(),
                employeeCreateDto.getSeller()
        );
        return employeeRepository.save(employee);
    }

    public List<EmployeeDefaultDto> getAllEmployeesAsDefaultDto() {
        return ((List<Employee>)employeeRepository.findAll())
                .stream()
                .map(this::convertToDefaultDto)
                .collect(Collectors.toList());
    }


}
