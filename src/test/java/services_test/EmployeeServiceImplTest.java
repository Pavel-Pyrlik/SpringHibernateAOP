package services_test;

import org.example.spring_hibernate.entity.Employee;
import org.example.spring_hibernate.services.EmployeeService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/test-context.xml"})
@Transactional
public class EmployeeServiceImplTest {

    @Autowired
    EmployeeService employeeService;
    int employeeTestId;
    Employee employee;

    @Before
    public void setUp() {
        employee = new Employee("Masha", "Ivanova", "Sale", 800);
        employeeService.saveEmployee(employee);
        employeeTestId = employee.getId();

    }

    @Test
    public void getAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        Assert.assertTrue(employeeList.contains(employee));
    }

    @Test
    public void saveEmployee() {
        Employee employee1 = new Employee("Nina", "Sidorova", "Sale", 800);
        Employee employee2 = new Employee("Nina", "Sidorova", "Sale", 800);
        employeeService.saveEmployee(employee1);
        employee2.setId(employee1.getId());
        Assert.assertEquals(employee1,employee2);
    }
    @Test
    public void deleteEmployee() {
        employeeService.deleteEmployee(employeeTestId);
        Assert.assertNull(employeeService.getEmployee(employeeTestId));

    }

    @Test
    public void getEmployee() {
        Assert.assertNotNull(employeeService.getEmployee(employeeTestId));

    }
}