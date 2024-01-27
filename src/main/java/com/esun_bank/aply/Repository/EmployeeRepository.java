package com.esun_bank.aply.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.esun_bank.aply.Entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	List<Employee> findAll();
	Optional<Employee> findByEmpId(Long empId);
}
