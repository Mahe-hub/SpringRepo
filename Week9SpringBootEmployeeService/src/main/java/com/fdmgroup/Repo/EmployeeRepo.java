package com.fdmgroup.Repo;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fdmgroup.Model.EmployeeClass;

public interface EmployeeRepo extends JpaRepository<EmployeeClass, Long> {
	
	public EmployeeClass findByFirstnameAndLastname(String firstname,String lastname);
	

}
