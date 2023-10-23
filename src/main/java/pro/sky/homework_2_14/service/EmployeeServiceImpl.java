package pro.sky.homework_2_14.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.homework_2_14.entity.Employee;
import pro.sky.homework_2_14.exception.EmployeeAlreadyAddedException;
import pro.sky.homework_2_14.exception.EmployeeNotFoundException;
import pro.sky.homework_2_14.exception.EmployeeStorageIsFullException;
import pro.sky.homework_2_14.exception.EmployeeValidateException;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    final Map<Integer, Employee> employees;
    private static final int MAX_SIZE = 10; // максимум сотрудников

    public EmployeeServiceImpl()
            throws EmployeeAlreadyAddedException, EmployeeStorageIsFullException {

        this.employees = new HashMap<>();

        addNewEmployee("Maksim", "Tarasov", 5017, 90000, 1);
        addNewEmployee("Yulia", "Tarasova", 4280, 80000, 2);
        addNewEmployee("Alex", "Belyanin", 2362, 25000, 3);
        addNewEmployee("Pavel", "Varnavskii", 2886, 15000, 1);
        addNewEmployee("Andrey", "Astapov", 5021, 35000, 2);
        addNewEmployee("Fedor", "Barahoev", 2308, 45000, 3);
    }

    public String employees(){
        return "Список сотрудников:\n" + employees;
    }

    @Override
    // метод для добавления сотрудника
    public Employee addNewEmployee(String firstName, String lastName, int passportNumber, int salary, int departmentId)
            throws EmployeeStorageIsFullException, EmployeeAlreadyAddedException {

        // Замена заглавной буквы
        firstName = StringUtils.capitalize(firstName);
        lastName = StringUtils.capitalize(lastName);

        // проверка на отсутствие запрещенных символов (любых кроме букв)
        validateNames(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, passportNumber, salary, departmentId);

        // проверка на наличие сотрудника перед добавлением
        if (employees.containsKey(employee.getPassportNumber())){
            throw new EmployeeAlreadyAddedException("Ошибка! Сотрудник уже добавлен в список!");
        }

        // проверка на заполненность списка
        if (employees.size() == MAX_SIZE){
            throw new EmployeeStorageIsFullException("Ошибка! Список заполнен!");
        }

        // добавляем сотрудника, увеличиваем счетчик сотрудников
        employees.put(employee.getPassportNumber(), employee);

        return employee;
    }

    @Override
    // метод для удаления сотрудника
    public Employee removeEmployee(String firstName, String lastName, int passportNumber, int salary, int departmentId)
            throws EmployeeNotFoundException {

        // проверка на отсутствие запрещенных символов (любых кроме букв)
        validateNames(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, passportNumber, salary, departmentId);

        // проверка на наличие сотрудника перед удалением
        if (!employees.containsKey(employee.getPassportNumber())){
            throw new EmployeeNotFoundException("Ошибка! Сотрудник не найден!");
        }

        // удаляем сотрудника, уменьшаем счетчик
        employees.remove(employee.getPassportNumber(), employee);

        return employee;
    }

    @Override
    // метод для поиска сотрудника
    public Employee findEmployee(String firstName, String lastName, int passportNumber, int salary, int departmentId)
            throws EmployeeNotFoundException {

        // проверка на отсутствие запрещенных символов (любых кроме букв)
        validateNames(firstName, lastName);

        Employee employee = new Employee(firstName, lastName, passportNumber, salary, departmentId);

        if (employees.containsKey(employee.getPassportNumber())){
            return employees.get(employee.getPassportNumber());
        }
        throw new EmployeeNotFoundException("Ошибка! Сотрудник не найден!");
    }

    @Override
    public Collection<Employee> findAll(){
        return Collections.unmodifiableCollection(employees.values());
    }

    // метод для проверки имени и фамилии на наличие запрещенных символов
    private void validateNames(String firstName, String lastName){

        if (!StringUtils.isAlpha(firstName)){
            throw new EmployeeValidateException("Ошибка! Имя содержит запрещенные символы");
        }
        if (!StringUtils.isAlpha(lastName)){
            throw new EmployeeValidateException("Ошибка! Фамилия содержит запрещенные символы");
        }
    }
}
