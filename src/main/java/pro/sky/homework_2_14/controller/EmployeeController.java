package pro.sky.homework_2_14.controller;

import org.springframework.web.bind.annotation.*;
import pro.sky.homework_2_14.entity.Employee;
import pro.sky.homework_2_14.exception.EmployeeAlreadyAddedException;
import pro.sky.homework_2_14.exception.EmployeeNotFoundException;
import pro.sky.homework_2_14.exception.EmployeeStorageIsFullException;
import pro.sky.homework_2_14.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @ExceptionHandler
    public String handleException(RuntimeException e){
        return e.getMessage();
    }

    private final EmployeeService employeeService;

    // инжектим EmployeeService в EmployeeController
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public String employees(){
        return employeeService.employees();
    }

    // добавление сотрудника
    @GetMapping(path = "/add")   // /employee/add?firstName=Ivan&lastName=Ivanov&passportNumber=3498&salary=10000&departmentId=4
    public Employee addNewEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("passportNumber") int passportNumber,
                                   @RequestParam("salary") int salary,
                                   @RequestParam("departmentId") int departmentId)
            throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {
        return employeeService.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId);
    }

    // удаление сотрудника
    @GetMapping(path = "/remove")    // /employee/add?firstName=Ivan&lastName=Ivanov&passportNumber=3498&salary=10000&departmentId=4
    public Employee removeEmployee(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("passportNumber") int passportNumber,
                                   @RequestParam("salary") int salary,
                                   @RequestParam("departmentId") int departmentId)
            throws EmployeeNotFoundException {
        return employeeService.removeEmployee(firstName, lastName, passportNumber, salary, departmentId);
    }

    // поиск сотрудника
    @GetMapping(path = "/find")  // /employee/add?firstName=Ivan&lastName=Ivanov&passportNumber=3498&salary=10000&departmentId=4
    public Employee findEmployee(@RequestParam("firstName") String firstName,
                                 @RequestParam("lastName") String lastName,
                                 @RequestParam("passportNumber") int passportNumber,
                                 @RequestParam("salary") int salary,
                                 @RequestParam("departmentId") int departmentId)
            throws EmployeeNotFoundException {
        return employeeService.findEmployee(firstName, lastName, passportNumber, salary, departmentId);
    }
}