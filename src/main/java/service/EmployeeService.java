package service;

import Model.Employee;

import java.util.List;

public interface EmployeeService {
    public void saveEmployee(Employee s);
    public void deleteEmployee(Integer id);
    public Employee findById(Integer id);
    public List<Employee> findAll();
    public List<Employee> findAllByNameContainsIgnoreCase(String name);
}
