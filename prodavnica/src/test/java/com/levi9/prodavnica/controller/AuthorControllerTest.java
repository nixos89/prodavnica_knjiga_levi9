package com.levi9.prodavnica.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.levi9.prodavnica.config.AuthorConstants;
import com.levi9.prodavnica.config.UrlPrefix;
import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.AuthorListDTO;
import com.levi9.prodavnica.service.AuthorService;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AuthorService authorService;

	@Test
	public void testGetAllAuthors() throws Exception {
		when(authorService.findAllAuthors()).thenReturn(new AuthorListDTO(Lists.newArrayList(
				new AuthorDTO(AuthorConstants.PERA_ID, AuthorConstants.FIRST_NAME_PERA, AuthorConstants.LAST_NAME_PERA),
				new AuthorDTO(AuthorConstants.DESA_ID, AuthorConstants.FIRST_NAME_DESA, AuthorConstants.LAST_NAME_DESA),
				new AuthorDTO(AuthorConstants.JOVA_ID, AuthorConstants.FIRST_NAME_JOVA,
						AuthorConstants.LAST_NAME_JOVA))));

		mockMvc.perform(get(UrlPrefix.GET_AUTHORS).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(jsonPath("$.authors.[0].authorId").value(AuthorConstants.PERA_ID))
				.andExpect(jsonPath("$.authors.[0].firstName").value(AuthorConstants.FIRST_NAME_PERA))
				.andExpect(jsonPath("$.authors.[0].lastName").value(AuthorConstants.LAST_NAME_PERA))

				.andExpect(jsonPath("$.authors.[1].authorId").value(AuthorConstants.DESA_ID))
				.andExpect(jsonPath("$.authors.[1].firstName").value(AuthorConstants.FIRST_NAME_DESA))
				.andExpect(jsonPath("$.authors.[1].lastName").value(AuthorConstants.LAST_NAME_DESA))

				.andExpect(jsonPath("$.authors.[2].authorId").value(AuthorConstants.JOVA_ID))
				.andExpect(jsonPath("$.authors.[2].firstName").value(AuthorConstants.FIRST_NAME_JOVA))
				.andExpect(jsonPath("$.authors.[2].lastName").value(AuthorConstants.LAST_NAME_JOVA));
	}

	@Test
	public void testGetAuthor() throws Exception {
		when(authorService.getOne(AuthorConstants.DESA_ID)).thenReturn(new AuthorDTO(AuthorConstants.DESA_ID,
				AuthorConstants.FIRST_NAME_DESA, AuthorConstants.LAST_NAME_DESA));

		mockMvc.perform(get(UrlPrefix.GET_AUTHORS +"/" + AuthorConstants.DESA_ID).accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andExpect(jsonPath("$.authorId").value(AuthorConstants.DESA_ID))
				.andExpect(jsonPath("$.firstName").value(AuthorConstants.FIRST_NAME_DESA))
				.andExpect(jsonPath("$.lastName").value(AuthorConstants.LAST_NAME_DESA));
	}

}
