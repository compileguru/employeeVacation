package com.labcorp.employeeVacation.model;

public class HourlyEmployee extends Employee {
    public HourlyEmployee(int id, EmployeeType employeeType) {
        super(id,employeeType);
    }

    @Override
    public float getAccumulatedVacationDaysPerYear() {
        return 10f;
    }
}

