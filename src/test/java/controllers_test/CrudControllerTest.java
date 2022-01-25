package controllers_test;

import config.TestMyConfig;
import config.TestMyWebInitializer;
import org.example.spring_hibernate.controllers.CrudController;
import org.example.spring_hibernate.entity.Employee;
import org.example.spring_hibernate.services.EmployeeService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={TestMyConfig.class, TestMyWebInitializer.class})
@WebAppConfiguration
@Transactional
public class CrudControllerTest {

    @Autowired
    WebApplicationContext wac;
    @Autowired
    EmployeeService employeeService;

    private MockMvc mockMvc;

    @Autowired
    CrudController crudController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testHomePage() throws Exception {

        mockMvc.perform(get("/crud/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/view/all-employees.jsp"))
                .andExpect(model().attributeExists("allEmps"));

    }

    @Test
    public void testAddNewEmployee() throws Exception {

        mockMvc.perform(get("/crud/addNewEmployee"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/view/employee-info.jsp"))
                .andExpect(model().attributeExists("newEmployee"));

    }

    @Test
    public void testUpdateEmployee() throws Exception {

        mockMvc.perform(get("/crud/updateInfo").param("empId", "1"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/view/employee-info.jsp"));

    }

    @Test
    public void testDeleteEmployee() throws Exception {
        Employee employee = new Employee("Masha", "Ivanova", "Sale", 800);
        employeeService.saveEmployee(employee);

        mockMvc.perform(get("/crud/deleteEmp").param("empId", String.valueOf(employee.getId())))
                .andExpect(status().is3xxRedirection());

    }




}
