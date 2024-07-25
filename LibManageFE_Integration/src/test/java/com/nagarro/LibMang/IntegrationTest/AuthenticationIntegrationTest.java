package com.nagarro.LibMang.IntegrationTest;
import com.nagarro.LibMang.config.MvcAppContext;
import com.nagarro.LibMang.entities.Book;
import com.nagarro.LibMang.service.BookService;
import com.nagarro.LibMang.service.LoginService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { MvcAppContext.class })
@WebAppConfiguration
public class AuthenticationIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private LoginService loginService;

	@Autowired
	private BookService bookService;

	private MockMvc mockMvc;

	@BeforeEach
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
	}

	@Test
	public void givenWac_whenServletContext_thenItProvidesHomeController() {
		ServletContext servletContext = webApplicationContext.getServletContext();
		assertNotNull(servletContext);
		assertTrue(servletContext instanceof org.springframework.mock.web.MockServletContext);
	}

	@Test
	public void testLoginSuccess() throws Exception {

		boolean result = loginService.checkLogin("adarshgupta", "12345");
		
		mockMvc.perform(post("/login").param("uname", "adarshgupta").param("pass", "12345"))
		.andExpect(status().isOk()).andExpect(view().name("BookListing"));
		
		assertTrue(result==true);

		

	}

//	@Test
//	public void testLoginFailure() throws Exception {
////		when(loginService.checkLogin("invalidUser", "invalidPass")).thenReturn(false);
////		boolean result = loginService.checkLogin("invalidUser", "invalidPass");
//
//		mockMvc.perform(post("/login").param("uname", "invalidUser").param("pass", "invalidPass"))
//				.andExpect(status().isOk()).andExpect(view().name("login"));
//		
////		assertTrue(result!=true);
//	}

	@Test
	public void testLogout() throws Exception {
		mockMvc.perform(post("/Logout")).andExpect(status().isOk()).andExpect(view().name("login"));
	}
}
