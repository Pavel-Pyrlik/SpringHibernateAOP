package org.example.spring_hibernate.controllers;

import org.example.spring_hibernate.entity.Employee;
import org.example.spring_hibernate.services.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.List;

@Controller
public class MyController {

    private final EmployeeService employeeService;

    public MyController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @RequestMapping("/")
    public String showAllEmployees(Model model) {
        List<Employee> employeeList = employeeService.getAllEmployees();
        model.addAttribute("allEmps",  employeeList);
        return "all-employees";
    }

    @RequestMapping("/addNewEmployee")
    public String addNewEmployee(Model model) {
        model.addAttribute("newEmployee", new Employee());
        return "employee-info";
    }

    @RequestMapping("/saveEmployee")
    public String saveEmployee(@Valid @ModelAttribute("newEmployee") Employee employee,
                                BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "employee-info";
        else {
            employeeService.saveEmployee(employee);
            return "redirect:/";
        }
    }

    @RequestMapping("/updateInfo")
    public String updateEmployee(@RequestParam("empId") int id, Model model) {
        model.addAttribute("newEmployee",
                employeeService.getEmployee(id));
        return "employee-info";
    }

    @RequestMapping("/deleteEmp")
    public String deleteEmployee(@RequestParam("empId") int id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

}
