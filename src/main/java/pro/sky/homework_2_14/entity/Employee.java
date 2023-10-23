package pro.sky.homework_2_14.entity;

import java.util.Objects;

public class Employee {

    private final String firstName;
    private final String lastName;
    private final int passportNumber;
    private final int salary;
    private final int departmentId;

    public Employee(String firstName, String lastName, int passportNumber, int salary, int departmentId) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.passportNumber = passportNumber;
        this.salary = salary;
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", passportNumber=" + passportNumber +
                ", salary=" + salary +
                ", departmentId=" + departmentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return passportNumber == employee.passportNumber
                && salary == employee.salary
                && departmentId == employee.departmentId
                && Objects.equals(firstName, employee.firstName)
                && Objects.equals(lastName, employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, passportNumber, salary, departmentId);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getPassportNumber() {
        return passportNumber;
    }

    public int getSalary() {
        return salary;
    }

    public int getDepartmentId() {
        return departmentId;
    }
}