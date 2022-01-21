package org.example.spring_hibernate.rest.controllers;

import org.example.spring_hibernate.entity.Employee;
import org.example.spring_hibernate.exception_handling.NoSuchEmployeeException;
import org.example.spring_hibernate.exception_handling.ValidDataException;
import org.example.spring_hibernate.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Employee>> showAllEmployees() {
        return new ResponseEntity<>(service.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity<Employee> getEmployee(@PathVariable int id) {
        Employee employee = service.getEmployee(id);
        if(employee == null)
            throw new NoSuchEmployeeException("There is no employee with ID = " +
                    id + " in Database");

        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PostMapping("/employees")
    public ResponseEntity<Employee> addNewEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) {
        if(bindingResult.hasErrors())
            throw new ValidDataException("Invalid data");
        service.saveEmployee(employee);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }

    @PutMapping("/employees")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        service.saveEmployee(employee);
        return new ResponseEntity<>(employee,HttpStatus.OK);
    }
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable int id) {
        Employee employee = service.getEmployee(id);
        if(employee == null)
            throw new NoSuchEmployeeException("There is no employee with ID = " +
                    id + " in Database");
       service.deleteEmployee(id);
        return new ResponseEntity<>("Employee with ID = " + id + " was delete", HttpStatus.OK);
    }

}
