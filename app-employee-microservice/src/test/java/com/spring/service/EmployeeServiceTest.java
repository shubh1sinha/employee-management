package com.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.spring.dto.EmployeeDTO;
import com.spring.entity.Employee;
import com.spring.repository.EmployeeRepository;

@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class EmployeeServiceTest {
	
	@MockBean
	private EmployeeRepository empRepository;
	
	@InjectMocks
	private EmployeeService empService;
	
	@DisplayName("JUnit test for saveEmployee method")
	@Test
	public void registerEmployeeIfConditionTest() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empRepository.findByMobile("8709409648")).thenReturn(Optional.empty());
		Mockito.when(empRepository.save(any(Employee.class))).thenReturn(newEmp);
		
		String response = empService.registerEmployee(admin);
		assertEquals("Employee Added Successfully with username ="+newEmp.getUsername(), response);
	}
	
	@DisplayName("JUnit test for saveEmployee method else")
	@Test
	public void registerEmployeeElseConditionTest() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empRepository.findByMobile("8709409648")).thenReturn(Optional.of(newEmp));
		Mockito.when(empRepository.save(any(Employee.class))).thenReturn(newEmp);
		
		String response = empService.registerEmployee(admin);
		assertEquals("Already Present!", response);
	}
	
	@DisplayName("Junit test for employee login")
	@Test
	public void EmployeeLoginTestIfCondition() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setUsername(newEmp.getUsername());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empRepository.findByUsername("shusinha")).thenReturn(Optional.of(newEmp));
		
		String response = empService.loginEmployee(admin.getUsername(), admin.getPassword());
		assertEquals("Employee", response);
		
	}
	
	@DisplayName("Junit test for employee register push")
	@Test
	public void EmployeeLoginTestElseCondition() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setUsername(newEmp.getUsername());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empRepository.findByUsername("shusinha1")).thenReturn(Optional.empty());
		
		String response = empService.loginEmployee(admin.getUsername(), admin.getPassword());
		assertEquals("Register Before Login!", response);
		
	}
	
	@DisplayName("Junit test for employee Login Failed")
	@Test
	public void EmployeeLoginTestIfElseCondition() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setUsername(newEmp.getUsername());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empRepository.findByUsername("shusinha")).thenReturn(Optional.of(newEmp));
		
		String response = empService.loginEmployee(admin.getUsername(), "test");
		assertEquals("Wrong username and password", response);
		
	}
	
	@DisplayName("Junit test for list employee")
	@Test
	public void listEmployeeTest() {
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(newEmp);
		Mockito.when(empRepository.findAll()).thenReturn(empList);
		
		List<Employee> response = empService.listEmployee();
		assertEquals(empList, response);
	}
	
	@DisplayName("Junit test for updated employee")
	@Test
	public void updateEmployeeTestIfCondition() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empRepository.existsById("123")).thenReturn(true);
		Mockito.when(empRepository.save(any(Employee.class))).thenReturn(newEmp);
		
		String response = empService.updateEmployee(admin);
		assertEquals("Employee with "+newEmp.getEmployeeId()+" Updated!", response);
		
	}
	
	@DisplayName("Junit test for update employee")
	@Test
	public void updateEmployeeTestElseCondition() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empRepository.existsById("123")).thenReturn(false);
		Mockito.when(empRepository.save(any(Employee.class))).thenReturn(newEmp);
		
		String response = empService.updateEmployee(admin);
		assertEquals("Employee with "+newEmp.getEmployeeId()+" not found", response);
		
	}
	
	@DisplayName("Junit test for delete employee")
	@Test
	public void deleteEmployeeTest() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		
		String response = empService.deleteEmployee(newEmp.getEmployeeId());
		assertEquals("Employee Deleted Successfully!", response);
		
	}
	
	@DisplayName("Junit test for get employee")
	@Test
	public void getEmployeeTestI() {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123","Shubh Sinha","shusinha","8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Optional<Employee> response = empService.getEmployeeById("123");
		assertEquals(response, response);
		
	}
	
}
