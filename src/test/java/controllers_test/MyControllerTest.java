package controllers_test;

import org.example.spring_hibernate.controllers.MyController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/test-context.xml"})
@WebAppConfiguration
public class MyControllerTest {

    @Autowired
    WebApplicationContext wac;

    private MockMvc mockMvc;

    @Autowired
    MyController myController;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testHomePage() throws Exception {

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/view/all-employees.jsp"))
                .andExpect(model().attributeExists("allEmps"));

    }

    @Test
    public void testAddNewEmployee() throws Exception {

        mockMvc.perform(get("/addNewEmployee"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/view/employee-info.jsp"))
                .andExpect(model().attributeExists("newEmployee"));

    }

    @Test
    public void testUpdateEmployee() throws Exception {

        mockMvc.perform(get("/updateInfo?empId=1"))
                .andExpect(status().isOk())
                .andExpect(forwardedUrl("/WEB-INF/view/employee-info.jsp"));

    }

    @Test
    public void testDeleteEmployee() throws Exception {

        mockMvc.perform(get("/deleteEmp").param("empId", "4"))
                .andExpect(status().is3xxRedirection());

    }




}
