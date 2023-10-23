package pro.sky.homework_2_14.service;

import org.junit.jupiter.api.Test;
import pro.sky.homework_2_14.entity.Employee;
import pro.sky.homework_2_14.exception.EmployeeAlreadyAddedException;
import pro.sky.homework_2_14.exception.EmployeeNotFoundException;
import pro.sky.homework_2_14.exception.EmployeeStorageIsFullException;
import static pro.sky.homework_2_14.service.utils.EmployeeGenerator.*;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {

    private final EmployeeServiceImpl employeeServiceImpl = new EmployeeServiceImpl();

    @Test
    void addNewEmployee_Success() { // добавление нового сотрудника

        // данные сотрудника
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int passportNumber = PASSPORT_NUMBER;
        int salary = SALARY;
        int departmentId = DEPARTMENT_ID;

        // ожидаемый результат
        Employee expectedEmployee = getEmployee();

        // фактический результат
        Employee actualEmployee = employeeServiceImpl.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // сравнение результатов
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void addNewEmployee_EmployeeAlreadyAddedException() { // добавление уже существующего сотрудника

        // данные сотрудника
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int passportNumber = PASSPORT_NUMBER;
        int salary = SALARY;
        int departmentId = DEPARTMENT_ID;

        // добавляем сотрудника чтобы затем попытаться добавить его повторно
        employeeServiceImpl.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // ожидаемый результат
        String expectedMessage = "Ошибка! Сотрудник уже добавлен в список!";

        // фактический результат
        Exception exception = assertThrows(
                EmployeeAlreadyAddedException.class,
                () -> employeeServiceImpl.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId)
        );

        // сравнение результатов
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void addNewEmployee_EmployeeStorageIsFullException() { // добавление сотрудника в заполненную мапу

        // данные сотрудника
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int salary = SALARY;
        int departmentId = DEPARTMENT_ID;

        // ожидаемый результат
        String expectedMessage = "Ошибка! Список заполнен!";

        // фактический результат
        Exception exception = assertThrows(
                EmployeeStorageIsFullException.class,
                () -> {
                    // В мапе из EmployeeServiceImpl уже есть 6 сотрудников, размер мапы 10
                    // добавим еще 5 сотрудников через цикл для ускорения
                    for (int passportNumber = 1010; passportNumber < 1015; passportNumber++) {
                        employeeServiceImpl.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId);
                    }
                }
        );

        // сравнение результатов
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void removeEmployee_Success() { // удаление сотрудника

        // данные сотрудника
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int passportNumber = PASSPORT_NUMBER;
        int salary = SALARY;
        int departmentId = DEPARTMENT_ID;

        // добавляем сотрудника
        employeeServiceImpl.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // ожидаемый результат
        Employee expectedEmployee = getEmployee();

        // фактический результат
        Employee actualEmployee = employeeServiceImpl.removeEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // сравнение результатов
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void removeEmployee_EmployeeNotFoundException() { // удаление несуществующего сотрудника

        // данные сотрудника
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int passportNumber = PASSPORT_NUMBER;
        int salary = SALARY;
        int departmentId = DEPARTMENT_ID;

        // добавляем сотрудника
        employeeServiceImpl.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // удаляем сотрудника чтобы затем попытаться удалить его повторно
        employeeServiceImpl.removeEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // ожидаемый результат
        String expectedMessage = "Ошибка! Сотрудник не найден!";

        // фактический результат
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeServiceImpl.removeEmployee(firstName, lastName, passportNumber, salary, departmentId)
        );

        // сравнение результатов
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void findEmployee_Success() { // поиск сотрудника

        // данные сотрудника
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int passportNumber = PASSPORT_NUMBER;
        int salary = SALARY;
        int departmentId = DEPARTMENT_ID;

        // добавляем сотрудника чтобы затем найти его
        employeeServiceImpl.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // ожидаемый результат
        Employee expectedEmployee = getEmployee();

        // фактический результат
        Employee actualEmployee = employeeServiceImpl.findEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // сравнение результатов
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void findEmployee_EmployeeNotFoundException() { // поиск несуществующего сотрудника

        // данные сотрудника
        String firstName = FIRST_NAME;
        String lastName = LAST_NAME;
        int passportNumber = PASSPORT_NUMBER;
        int salary = SALARY;
        int departmentId = DEPARTMENT_ID;

        // добавляем сотрудника
        employeeServiceImpl.addNewEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // удаляем сотрудника чтобы затем попытаться найти его в мапе
        employeeServiceImpl.removeEmployee(firstName, lastName, passportNumber, salary, departmentId);

        // ожидаемый результат
        String expectedMessage = "Ошибка! Сотрудник не найден!";

        // фактический результат
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> employeeServiceImpl.findEmployee(firstName, lastName, passportNumber, salary, departmentId)
        );

        // сравнение результатов
        assertEquals(expectedMessage, exception.getMessage());
    }

    @Test
    void findAll_Success() {

        // ожидаемый результат
        Collection<Employee> expectedEmployees = employeeServiceImpl.employees.values();

        // фактический результат
        Collection<Employee> actualEmployees = employeeServiceImpl.findAll();

        // сравнение результатов
        assertEquals(expectedEmployees.toString(), actualEmployees.toString());
    }
}