package pro.sky.homework_2_14.service.utils;

import pro.sky.homework_2_14.entity.Employee;

import java.util.Arrays;
import java.util.List;

public class EmployeeGenerator {

    public static final String FIRST_NAME = "Ivan";
    public static final String LAST_NAME = "Ivanov";
    public static final int PASSPORT_NUMBER = 1010;
    public static final int SALARY = 30000;
    public static final int DEPARTMENT_ID = 1;

    public static final String FIRST_NAME_2 = "Petr";
    public static final String LAST_NAME_2 = "Petrov";
    public static final int PASSPORT_NUMBER_2 = 2020;
    public static final int SALARY_2 = 60000;
    public static final int DEPARTMENT_ID_2 = 1;


    public static final String FIRST_NAME_3 = "Alexandr";
    public static final String LAST_NAME_3 = "Alexandrov";
    public static final int PASSPORT_NUMBER_3 = 3030;
    public static final int SALARY_3 = 90000;
    public static final int DEPARTMENT_ID_3 = 3;

    public static Employee getEmployee(){
        return new Employee(FIRST_NAME, LAST_NAME, PASSPORT_NUMBER, SALARY, DEPARTMENT_ID);
    }

    public static Employee getEmployee2(){
        return new Employee(FIRST_NAME_2, LAST_NAME_2, PASSPORT_NUMBER_2, SALARY_2, DEPARTMENT_ID_2);
    }

    public static Employee getEmployee3(){
        return new Employee(FIRST_NAME_3, LAST_NAME_3, PASSPORT_NUMBER_3, SALARY_3, DEPARTMENT_ID_3);
    }

    public static List<Employee> getAllEmployee() {
        return Arrays.asList(getEmployee(), getEmployee2(), getEmployee3());
    }

}
