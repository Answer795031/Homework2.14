package pro.sky.homework_2_14.service;

import pro.sky.homework_2_14.entity.Employee;
import pro.sky.homework_2_14.exception.EmployeeNotFoundException;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee findMaxSalary(Integer departmentId) throws EmployeeNotFoundException;

    Employee findMinSalary(Integer departmentId) throws EmployeeNotFoundException;

    Collection<Employee> findAllByDepartment(Integer departmentId);

    Map<Integer, List<Employee>> findAllByDepartment();
}