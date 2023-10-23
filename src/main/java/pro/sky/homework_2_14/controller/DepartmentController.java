package pro.sky.homework_2_14.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.homework_2_14.entity.Employee;
import pro.sky.homework_2_14.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ExceptionHandler
    public String handleException(RuntimeException e) {
        return e.getMessage();
    }

    // поиск сотрудника с максимальной з/п в отделе
    @GetMapping(path = "{departmentId}/salary/max")  // /departments/1/salary/max
    public Employee findMaxSalary(@PathVariable int departmentId) {
        return departmentService.findMaxSalary(departmentId);
    }

    // поиск сотрудника с минимальной з/п в отделе
    @GetMapping(path = "{departmentId}/salary/min")  // /departments/1/salary/min
    public Employee findMinSalary(@PathVariable int departmentId) {
        return departmentService.findMinSalary(departmentId);
    }

    // поиск всех сотрудников в отделе
    @GetMapping(path = "{departmentId}/employees")  // /departments/1/employees
    public Collection<Employee> findAll(@PathVariable int departmentId) {
        return departmentService.findAllByDepartment(departmentId);
    }

    // поиск всех сотрудников с группировкой по отделам
    @GetMapping(path = "/employees")  // /departments/employees
    public Map<Integer, List<Employee>> findAll() {
        return departmentService.findAllByDepartment();
    }
}
