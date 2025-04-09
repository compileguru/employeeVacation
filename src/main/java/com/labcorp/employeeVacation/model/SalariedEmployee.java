package com.labcorp.employeeVacation.model;

public class SalariedEmployee extends Employee {
    public SalariedEmployee(int id, EmployeeType employeeType) {
        super(id, employeeType);
    }

    @Override
    public float getAccumulatedVacationDaysPerYear() {
        return 15f;
    }
}

