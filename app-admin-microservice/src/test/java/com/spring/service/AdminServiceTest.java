package com.spring.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

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

import com.spring.dto.AdminDTO;
import com.spring.entity.Admin;
import com.spring.repository.AdminRepository;



@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AdminServiceTest {
	
	@MockBean 
	private AdminRepository adminRepo;
	
	@InjectMocks
	private AdminService adminService;

	@DisplayName("JUnit test for saveEmployee method")
	@Test
	public void saveAdminTestIfCondition() throws Exception {
		AdminDTO admin = new AdminDTO();
		Admin newAdmin = new Admin("admin", "admin");
		admin.setUsername(newAdmin.getUsername());
		admin.setPassword(newAdmin.getPassword());
		Mockito.when(adminRepo.findById("admin")).thenReturn(Optional.empty());
		Mockito.when(adminRepo.save(any(Admin.class))).thenReturn(newAdmin);
		
		String response = adminService.saveAdmin(admin);
		assertEquals("Admin with "+admin.getUsername()+" created.", response);
	}
	
	@DisplayName("JUnit test for Admin Present")
	@Test
	public void saveAdminTestElseCondition() throws Exception {
		AdminDTO admin = new AdminDTO();
		Admin newAdmin = new Admin("admin", "admin");
		admin.setUsername(newAdmin.getUsername());
		admin.setPassword(newAdmin.getPassword());
		Mockito.when(adminRepo.findById("admin")).thenReturn(Optional.of(newAdmin));
		Mockito.when(adminRepo.save(any(Admin.class))).thenReturn(newAdmin);
		
		String response = adminService.saveAdmin(admin);
		assertEquals("Already present!", response);
	}
	
	@DisplayName("Junit test for admin login")
	@Test
	public void adminLoginTestIfCondition() {
		AdminDTO admin = new AdminDTO();
		Admin newAdmin = new Admin("admin", "admin");
		admin.setUsername(newAdmin.getUsername());
		admin.setPassword(newAdmin.getPassword());
		Mockito.when(adminRepo.findById("admin")).thenReturn(Optional.of(newAdmin));
		
		String response = adminService.adminLogin(admin.getUsername(), admin.getPassword());
		assertEquals("admin", response);
		
	}
	
	@DisplayName("Junit test for employee not present")
	@Test
	public void adminLoginTestIfElseCondition() {
		AdminDTO admin = new AdminDTO();
		Admin newAdmin = new Admin("admin", "admin");
		admin.setUsername(newAdmin.getUsername());
		admin.setPassword(newAdmin.getPassword());
		Mockito.when(adminRepo.findById("admin")).thenReturn(Optional.empty());
		
		String response = adminService.adminLogin(admin.getUsername(), admin.getPassword());
		assertEquals("Admin Not present!", response);
		
	}
	
	@DisplayName("Junit test for employee not logged IN")
	@Test
	public void adminLoginTestElseCondition() {
		AdminDTO admin = new AdminDTO();
		Admin newAdmin = new Admin("admin", "admin");
		admin.setUsername(newAdmin.getUsername());
		admin.setPassword(newAdmin.getPassword());
		Mockito.when(adminRepo.findById("admin")).thenReturn(Optional.of(newAdmin));
		
		String response = adminService.adminLogin(admin.getUsername(), "false");
		assertEquals("Wrong username or password!", response);
		
	}
	

	


}
