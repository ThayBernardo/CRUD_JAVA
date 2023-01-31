package ControlEmployees.dao;

import ControlEmployees.model.Employee;

import java.util.List;

public interface IEmployeeDAO {
    Employee create(Employee employee);
    void update();
    void delete(String name);
    List<Employee> findAll();
    List<String> findByBirthday();
    String maxAge();
    List<String> findAllNames();
    Integer totalSalary();
}
