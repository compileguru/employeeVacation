package com.labcorp.employeeVacation.service;

import com.labcorp.employeeVacation.exception.EmployeeVacationException;
import com.labcorp.employeeVacation.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class EmployeeServiceTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    void testCreateEmployees_onStartup_creates30Employees() {
        Collection<Employee> employees = employeeService.getAllEmployees();
        assertEquals(30, employees.size());
    }

    @Test
    void testRecordWorkingDays_calculateCorrectVacationDays() {
        Employee emp = employeeService.getEmployeeById(2); // Assuming salaried employee
        employeeService.recordWorkingDays(2, 130); // Vacation for half max working days (260/2)
        float expectedVacation = (15f / 260f) * 130;
        assertEquals(expectedVacation, emp.getVacationDays(), 0.001);
    }

    @Test
    void testTakeVacation_deductsVacationAfterBeenTaken() {
        Employee employee = employeeService.getEmployeeById(1);
        employeeService.recordWorkingDays(1, 260); // Full year
        employeeService.takeVacation(1, 5.0f);
        assertEquals(employee.getVacationDays(), employee.getAccumulatedVacationDaysPerYear() - 5.0f, 0.01);
    }

    @Test
    void testWorkBeyond260Days_throwsException() {
        assertThrows(EmployeeVacationException.class, () -> {
            employeeService.recordWorkingDays(1, 300);
        });
    }

    @Test
    void testTakeMoreVacationThanAccumulated_throwsException() {
        assertThrows(EmployeeVacationException.class, () -> {
            employeeService.takeVacation(1, 10.0f);
        });
    }
}
