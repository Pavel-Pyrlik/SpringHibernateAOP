package controllers_test;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.spring_hibernate.entity.Employee;
import org.example.spring_hibernate.exception_handling.NoSuchEmployeeException;
import org.example.spring_hibernate.services.EmployeeService;
import org.junit.Before;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/test-context.xml"})
@WebAppConfiguration
@Transactional
public class MyRESTControllerTest {

    @Autowired
    EmployeeService employeeService;
    private ObjectMapper objectMapper;
    int employeeTestId;
    Employee  employee;

    @Autowired
    WebApplicationContext wac;
    MockMvc mockMvc;

    @Before
    public void setUp() {
        employee = new Employee("Masha", "Ivanova", "Sale", 800);
        employeeService.saveEmployee(employee);
        employeeTestId = employee.getId();
        objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();

    }
    @Test
    public void getEmployeeById() throws Exception {
        mockMvc.perform(
                        get("/api/employees/{employeeTestId}", employeeTestId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(employeeTestId))
                .andExpect(jsonPath("$.name").value("Masha"));

    }

    @Test
    public void addEmployee() throws Exception {
        Employee employee1 = new Employee("Nina", "Sidorova", "Sale", 800);

        mockMvc.perform(
                        post("/api/employees")
                                .content(objectMapper.writeValueAsString(employee1))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Nina"));
    }

    @Test
    public void DeleteEmployee_thenStatus200() throws Exception {
        mockMvc.perform(
                        delete("/api/employees/{employeeTestId}", employeeTestId))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(employee)));
    }

    @Test
    public void getAllEmployees() throws Exception {
        mockMvc.perform(
                        get("/api/employees"))
                .andExpect(status().isOk());
    }

    @Test
    public void exceptionsTrowTest() throws Exception {
        mockMvc.perform(
                        get("/api/employees/1000"))
                .andExpect(status().isNotFound())
                .andExpect(mvcResult -> Objects.requireNonNull(mvcResult.getResolvedException())
                        .getClass().equals(NoSuchEmployeeException.class));
    }
    }

