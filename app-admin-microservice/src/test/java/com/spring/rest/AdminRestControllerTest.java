package com.spring.rest;

import static org.mockito.ArgumentMatchers.any;
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
import com.spring.dto.AdminDTO;
import com.spring.service.AdminService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class AdminRestControllerTest {

	@Autowired
	MockMvc mvc;
	
	@MockBean
	private AdminService adminService;
	
	@InjectMocks
	private AdminRestController adminRestController;
	
	@DisplayName("Junit Test for register admin")
	@Test
	public void registerAdmin() throws Exception {
		AdminDTO adminDto = new AdminDTO();
		adminDto.setUsername("admin");
		adminDto.setPassword("admin");
//		Admin admin = new Admin("admin", "admin");
		Mockito.when(adminService.saveAdmin(any(AdminDTO.class))).thenReturn("Admin with "+adminDto.getUsername()+" created.");
		ObjectMapper om = new ObjectMapper();
		String userJson = om.writeValueAsString(adminDto);
		mvc.perform(post("/admin/add").contentType(MediaType.APPLICATION_JSON_VALUE).content(userJson))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
	
	@DisplayName("Junit Test for register admin")
	@Test
	public void loginAdmin() throws Exception {
		AdminDTO adminDto = new AdminDTO();
		adminDto.setUsername("admin");
		adminDto.setPassword("admin");
		Mockito.when(adminService.adminLogin(adminDto.getUsername(), adminDto.getPassword())).thenReturn("admin");
		ObjectMapper om = new ObjectMapper();
		String userJson = om.writeValueAsString(adminDto);
		mvc.perform(get("/admin/login/admin/admin").contentType(MediaType.APPLICATION_JSON_VALUE).content(userJson))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON));
	}
}
