package com.esun_bank.aply.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.esun_bank.aply.Entity.Employee;
import com.esun_bank.aply.Repository.EmployeeRepository;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployeeById(Long empId) {
        return employeeRepository.findById(empId).orElse(null);
    }
    @Transactional
    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
    @Transactional
    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }
}