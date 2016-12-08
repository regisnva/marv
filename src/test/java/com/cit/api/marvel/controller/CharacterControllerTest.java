package com.cit.api.marvel.controller;

import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cit.api.marvel.conf.AppWebConf;
import com.cit.api.marvel.gateway.MarvelConstants;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppWebConf.class	 })
@WebAppConfiguration
public class CharacterControllerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mockMvc;

	@Before
	public void setUp() throws Exception {
		DefaultMockMvcBuilder builer = MockMvcBuilders.webAppContextSetup(context);
		this.mockMvc = builer.build();
	}


	@Test
	public void shouldBeMarvelCharactesSucessRequestTest() {
		try {
			MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.get("/".concat(MarvelConstants.LIST_CHARACTERS_RESOURCE).concat("?limit=2"));
			
			this.mockMvc.perform(builder)
			.andExpect(MockMvcResultMatchers
					.status()
					.isOk())
			.andDo(MockMvcResultHandlers.print());
			
		} catch (Exception e){
			fail(e.getMessage());
		}
	}
	
	@Test
	public void shouldBeMarvelCharactesCacheRequestTest() {
		try {
			MockHttpServletRequestBuilder builder = MockMvcRequestBuilders
					.get("/".concat(MarvelConstants.LIST_CHARACTERS_RESOURCE).concat("?limit=1"))
					.header(MarvelConstants.IF_NONE_MATCH_HEADER, "e1cef8a6e120071d86387f876a7eb1c011a1b0ac");
			
			this.mockMvc.perform(builder)
			.andExpect(MockMvcResultMatchers
					.status()	
					.isNotModified())
			.andDo(MockMvcResultHandlers.print());
			
		} catch (Exception e){
			fail(e.getMessage());
		}
	}

}
