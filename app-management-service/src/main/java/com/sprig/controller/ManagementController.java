package com.sprig.controller;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.sprig.dto.AdminDTO;
import com.sprig.dto.EmployeeDTO;
import com.sprig.entity.Employee;

/****
 * 
 * @author shusinha5
 * @project Employee-Management
 * @Description Full Stack Application
 * 		with devops.
 *
 */
@Controller
@CrossOrigin(origins = "http://localhost:3001/")
public class ManagementController {
	
	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);

	@Autowired
	private RestTemplate restTemplate;

	@PostMapping(value = "/admin/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> adminRegistration(@RequestBody AdminDTO admin) {
		ResponseEntity<String> adminReg = restTemplate.postForEntity("http://APP-ADMIN-MICROSERVICE/admin/add", admin,
				String.class);
		return adminReg;
	}

	@GetMapping(value = "/admin/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> adminLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		ResponseEntity<String> login = restTemplate
				.getForEntity("http://APP-ADMIN-MICROSERVICE/admin/login/" + username + "/" + password, String.class);
		return login;
	}

	@PostMapping(value = "/employee/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> employeeRegistration(@RequestBody EmployeeDTO employee) {
		ResponseEntity<String> employeeReg = restTemplate.postForEntity("http://APP-EMPLOYEE-MICROSERVICE/employee/register",
				employee, String.class);
		return employeeReg;
	}

	@GetMapping(value = "/employee/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> EmployeeLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		ResponseEntity<String> login = restTemplate
				.getForEntity("http://APP-EMPLOYEE-MICROSERVICE/employee/login/" + username + "/" + password, String.class);
		return login;
	}

	@GetMapping(value = "/employee/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Employee>> listEmployee() {
		ResponseEntity<List<Employee>> employeeList = restTemplate.exchange("http://APP-EMPLOYEE-MICROSERVICE/employee/list",
				HttpMethod.GET, null, new ParameterizedTypeReference<List<Employee>>() {
				});
		return employeeList;
	}

	@PostMapping(value = "/employee/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> employeeUpdate(@RequestBody EmployeeDTO employee) {
		ResponseEntity<String> employeeUpdate = restTemplate.postForEntity("http://APP-EMPLOYEE-MICROSERVICE/employee/update",
				employee, String.class);
		return employeeUpdate;
	}

	@GetMapping(value = "/employee/delete", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<String> deleteEmployee(@RequestParam("id") String id) {
		ResponseEntity<String> deleteEmp = restTemplate.getForEntity("http://APP-EMPLOYEE-MICROSERVICE/employee/delete/" + id,
				String.class);
		return deleteEmp;
	}

	@GetMapping(value = "/employee/info", produces = MediaType.APPLICATION_JSON_VALUE)
	ResponseEntity<Employee> getEmployeeById(@RequestParam("username") String username) {
		ResponseEntity<Employee> getEmp = restTemplate.getForEntity("http://APP-EMPLOYEE-MICROSERVICE/employee/info/" + username,
				Employee.class);
		return getEmp;
	}

}
