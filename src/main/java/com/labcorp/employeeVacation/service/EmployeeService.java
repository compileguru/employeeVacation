package com.labcorp.employeeVacation.service;

import com.labcorp.employeeVacation.exception.EmployeeVacationException;
import com.labcorp.employeeVacation.model.*;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class EmployeeService {
    private final Map<Integer, Employee> employeeMap = new HashMap<>();
    private int nextId = 1;

    @PostConstruct
    public void init() {
        for (int i = 0; i < 10; i++) {
            employeeMap.put(nextId, new HourlyEmployee(nextId++, EmployeeType.HOURLY));
            employeeMap.put(nextId, new SalariedEmployee(nextId++, EmployeeType.SALARIED));
            employeeMap.put(nextId, new Manager(nextId++, EmployeeType.MANAGER));
        }
    }

    public Collection<Employee> getAllEmployees() {
        return employeeMap.values();
    }

    public Employee getEmployeeById(int id) {
        return Optional.ofNullable(employeeMap.get(id))
                .orElseThrow(() -> new EmployeeVacationException("Employee not found."));
    }

    public Employee recordWorkingDays(int id, int days) {
        getEmployeeById(id).recordWorkingDays(days);
        return getEmployeeById(id);
    }

    public Employee takeVacation(int id, float days) {
        getEmployeeById(id).takeVacation(days);
        return getEmployeeById(id);
    }
}

