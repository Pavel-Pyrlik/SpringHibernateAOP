package org.example.spring_hibernate.dao;

import org.example.spring_hibernate.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
     List<Employee> getAllEmployees();

     void saveEmployee(Employee employee);

     Employee getEmployee(int id);

     void deleteEmployee(int id);
}
