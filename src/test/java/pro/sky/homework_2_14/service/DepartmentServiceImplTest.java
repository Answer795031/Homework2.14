package pro.sky.homework_2_14.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.homework_2_14.entity.Employee;
import pro.sky.homework_2_14.exception.EmployeeAlreadyAddedException;
import pro.sky.homework_2_14.exception.EmployeeNotFoundException;
import pro.sky.homework_2_14.service.utils.EmployeeGenerator;

import java.util.*;

import static org.mockito.Mockito.when;
import static pro.sky.homework_2_14.service.utils.EmployeeGenerator.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeServiceImpl employeeService;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void findMaxSalary_Success() { // поиск сотрудника с максимальной з/п в отделе

        // указываем необходимый departmentId
        Integer departmentId = DEPARTMENT_ID;

        // когда вызываем findAll, возвращается метод getAllEmployee из EmployeeGenerator'а
        when(employeeService.findAll()).thenReturn(getAllEmployee());

        // ожидаемый результат
        Employee expectedEmployee = getEmployee2();

        //фактический результат
        Employee actualEmployee = departmentService.findMaxSalary(departmentId);

        // сравнение результатов
        assertEquals(expectedEmployee, actualEmployee);

    }

    @Test
    void findMaxSalary_EmployeeNotFoundException() { // поиск сотрудника с максимальной з/п в отделе - сотрудник не найден

        // указываем необходимый departmentId
        Integer departmentId = DEPARTMENT_ID_3;

        // когда вызываем findAll, возвращается пустой лист
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        // ожидаемый результат
        String expectedMessage = "Ошибка! Сотрудник не найден!";

        // фактический результат
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.findMaxSalary(departmentId)
        );
    }

    @Test
    void findMinSalary_Success() { // поиск сотрудника с минимальной з/п в отделе

        // указываем необходимый departmentId
        Integer departmentId = DEPARTMENT_ID;

        // когда вызываем findAll, возвращается метод getAllEmployee из EmployeeGenerator'а
        when(employeeService.findAll()).thenReturn(getAllEmployee());

        // ожидаемый результат
        Employee expectedEmployee = getEmployee();

        //фактический результат
        Employee actualEmployee = departmentService.findMinSalary(departmentId);

        // сравнение результатов
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void findMinSalary_EmployeeNotFoundException() { // поиск сотрудника с максимальной з/п в отделе - сотрудник не найден

        // указываем необходимый departmentId
        Integer departmentId = DEPARTMENT_ID_3;

        // когда вызываем findAll, возвращается пустой лист
        when(employeeService.findAll()).thenReturn(Collections.emptyList());

        // ожидаемый результат
        String expectedMessage = "Ошибка! Сотрудник не найден!";

        // фактический результат
        Exception exception = assertThrows(
                EmployeeNotFoundException.class,
                () -> departmentService.findMinSalary(departmentId)
        );
    }

    @Test
    void findAllByDepartment_Success() { // поиск всех сотрудников по отделу

        // указываем необходимый departmentId
        Integer departmentId = DEPARTMENT_ID;

        // когда вызываем findAll, возвращается метод getAllEmployee из EmployeeGenerator'а
        when(employeeService.findAll()).thenReturn(getAllEmployee());

        // ожидаемый результат
        Collection<Employee> expectedCollection = new ArrayList<>();
        expectedCollection.add(getEmployee());
        expectedCollection.add(getEmployee2());

        // фактический результат
        Collection<Employee> actualCollection = departmentService.findAllByDepartment(departmentId);
        assertEquals(expectedCollection, actualCollection);
        assertEquals(getEmployee().getDepartmentId(), getEmployee2().getDepartmentId());
        assertNotEquals(getEmployee().getDepartmentId(), getEmployee3().getDepartmentId());
    }

    @Test
    void findAllByDepartment_Fault_DepartmentIdIsNull() { // поиск всех сотрудников по отделу если departmentId == null

        // указываем необходимый departmentId
        Integer departmentId = null;

        // когда вызываем findAll, возвращается метод getAllEmployee из EmployeeGenerator'а
        when(employeeService.findAll()).thenReturn(getAllEmployee());

        // ожидаемый результат
        Collection<Employee> expectedCollection = new ArrayList<>();
        expectedCollection.add(getEmployee());
        expectedCollection.add(getEmployee2());
        expectedCollection.add(getEmployee3());

        // фактический результат
        Collection<Employee> actualCollection = departmentService.findAllByDepartment(departmentId);
        assertEquals(expectedCollection, actualCollection);
        assertEquals(getEmployee().getDepartmentId(), getEmployee2().getDepartmentId());
        assertNotEquals(getEmployee().getDepartmentId(), getEmployee3().getDepartmentId());
    }
}