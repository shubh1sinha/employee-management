package com.spring.rest;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dto.EmployeeDTO;
import com.spring.entity.Employee;
import com.spring.service.EmployeeServiceDAO;
/****
 * 
 * @author shusinha5
 * @project Employee-Management
 * @Description Full Stack Application
 * 		with devops.
 *
 */


@RestController
@RequestMapping(value = "/employee")
public class EmployeeRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeRestController.class);
	
	@Autowired
	private EmployeeServiceDAO empService;
	
	@PostMapping(value="/register", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> registerEmployee(@RequestBody EmployeeDTO employee){
		String result = empService.registerEmployee(employee);
		return ResponseEntity.ok("{\"status\":\""+result+"\"}");
	}
	
	@GetMapping(value="/login/{username}/{password}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> loginEmployee(@PathVariable("username") String username, @PathVariable("password") String password){
		String result = empService.loginEmployee(username, password);
		return ResponseEntity.ok("{\"status\":\""+result+"\"}");
	}
	
	@GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Employee> employeeList(){
		return empService.listEmployee();
	}
	
	@PostMapping(value="/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmployee(@RequestBody EmployeeDTO employee){
		String result =  empService.updateEmployee(employee);
		return ResponseEntity.ok("{\"status\":\""+result+"\"}");
	}
	
	@GetMapping(value="/delete/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> updateEmployee(@PathVariable("id") String id){
		String result =  empService.deleteEmployee(id);
		return ResponseEntity.ok("{\"status\":\""+result+"\"}");
	}
	
	@GetMapping(value="/info/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Optional<Employee>> getEmployee(@PathVariable("id") String id){
		return ResponseEntity.ok(empService.getEmployeeById(id));
	}
 

}
