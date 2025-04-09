package com.labcorp.employeeVacation.model;

public class Manager extends SalariedEmployee {
    public Manager(int id, EmployeeType employeeType) {
        super(id, employeeType);
    }

    @Override
    public float getAccumulatedVacationDaysPerYear() {
        return 30f;
    }
}
