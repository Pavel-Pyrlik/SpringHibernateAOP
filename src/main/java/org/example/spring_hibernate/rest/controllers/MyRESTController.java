package org.example.spring_hibernate.rest.controllers;

import org.example.spring_hibernate.entity.Employee;
import org.example.spring_hibernate.exception_handling.NoSuchEmployeeException;
import org.example.spring_hibernate.exception_handling.ValidDataException;
import org.example.spring_hibernate.services.EmployeeService;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRESTController {
    private final EmployeeService service;

    public MyRESTController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return service.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = service.getEmployee(id);
        if(employee == null)
            throw new NoSuchEmployeeException("There is no employee with ID = " +
                    id + " in Database");

        return employee;
    }

    @PostMapping("/employees")
    public Employee addNewEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidDataException("Invalid data");
        service.saveEmployee(employee);
        return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {
        service.saveEmployee(employee);
        return employee;
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id) {
        Employee employee = service.getEmployee(id);
        if(employee == null)
            throw new NoSuchEmployeeException("There is no employee with ID = " +
                    id + " in Database");
       service.deleteEmployee(id);
        return "Employee with ID = " + id + " was delete";
    }

}
