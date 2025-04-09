package com.labcorp.employeeVacation.model;

import com.labcorp.employeeVacation.exception.EmployeeVacationException;
import lombok.Data;
import lombok.Getter;

@Data
public abstract class Employee {

    private final int empId;
    private final EmployeeType empType;
    private int daysWorked = 0;
    private float vacationDays = 0f;
    private static final int MAX_WORK_DAYS = 260;


    public Employee(int empId, EmployeeType empType) {
        this.empId = empId;
        this.empType = empType;
    }

    public abstract float getAccumulatedVacationDaysPerYear();

    public final void recordWorkingDays(int days) {
        if (days < 0 || (daysWorked + days) > MAX_WORK_DAYS) {
            throw new EmployeeVacationException("Work Days should be between 0 & 260.");
        }
        daysWorked = daysWorked + days;
        vacationDays = vacationDays + ((float) days / MAX_WORK_DAYS) * getAccumulatedVacationDaysPerYear();
    }

    public final void takeVacation(float days) {
        if (days < 0 || days > vacationDays) {
            throw new EmployeeVacationException("Not enough vacation days.");
        }
        vacationDays = vacationDays - days;
    }

}
