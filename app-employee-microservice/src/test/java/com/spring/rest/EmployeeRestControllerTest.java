package com.spring.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.dto.EmployeeDTO;
import com.spring.entity.Employee;
import com.spring.service.EmployeeService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class EmployeeRestControllerTest {

	@Autowired
	MockMvc mvc;

	@MockBean
	private EmployeeService empService;

	@InjectMocks
	private EmployeeRestController empRest;

	@DisplayName("Junit Test for register employee")
	@Test
	public void registerEmployeeTest() throws Exception {
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123", "Shubh Sinha", "shusinha", "8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empService.registerEmployee(admin)).thenReturn("Admin with " + admin.getUsername() + " created.");
		ObjectMapper om = new ObjectMapper();
		String userJson = om.writeValueAsString(admin);
		mvc.perform(post("/employee/register").contentType(MediaType.APPLICATION_JSON_VALUE).content(userJson))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@DisplayName("Junit Test for login employee")
	@Test
	public void emloyeeLoginTes() throws Exception{
		EmployeeDTO admin = new EmployeeDTO();
		Employee newEmp = new Employee("123", "Shubh Sinha", "shusinha", "8709409648", "shubh123");
		admin.setEmployeeId(newEmp.getEmployeeId());
		admin.setMobile(newEmp.getMobile());
		admin.setName(newEmp.getName());
		admin.setPassword(newEmp.getPassword());
		Mockito.when(empService.loginEmployee(admin.getUsername(), admin.getPassword())).thenReturn("Employee");
		ObjectMapper om = new ObjectMapper();
		String userJson = om.writeValueAsString(admin);
		mvc.perform(get("/employee/login/shusinha/shubh123").contentType(MediaType.APPLICATION_JSON_VALUE).content(userJson))
		.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}

}
