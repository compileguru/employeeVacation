package com.labcorp.employeeVacation.controller;


import com.labcorp.employeeVacation.model.Employee;
import com.labcorp.employeeVacation.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private final EmployeeService service;

    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping
    public Collection<Employee> getAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getEmployee(@PathVariable int id) {
        return service.getEmployeeById(id);
    }

    @PostMapping("/{id}/work")
    public Employee recordWork(@PathVariable int id, @RequestParam int days) {
        log.info("Recording work for employee with ID: {}", id);
        return service.recordWorkingDays(id, days);
    }

    @PostMapping("/{id}/vacation")
    public Employee takeVacation(@PathVariable int id, @RequestParam float days) {
        log.info("Vacation taken for employee with ID: {}", id);
        return service.takeVacation(id, days);
    }
}

