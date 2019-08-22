package com.levi9.prodavnica.controller;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.restdocs.payload.ResponseFieldsSnippet;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.levi9.prodavnica.config.AuthorConstants;
import com.levi9.prodavnica.config.UrlPrefix;
import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.dto.AuthorListDTO;
import com.levi9.prodavnica.service.AuthorService;

@RunWith(SpringRunner.class)
@WebMvcTest(AuthorController.class)
@AutoConfigureRestDocs(outputDir = "target/generated-sources/snippets")
public class AuthorControllerTest {

	@Autowired
	MockMvc mockMvc;

	@MockBean
	AuthorService authorService;
	
	@Autowired
	ObjectMapper objectMapper;

	@Test
	public void testGetAllAuthors() throws Exception {
		when(authorService.findAllAuthors()).thenReturn(new AuthorListDTO(AuthorConstants.authorDTOs()));

		mockMvc.perform(get(UrlPrefix.GET_AUTHORS)
					.accept(MediaType.APPLICATION_JSON_VALUE)
					.contentType(MediaType.APPLICATION_JSON)
					.content(objectMapper.writeValueAsString(AuthorConstants.authorDTOs())))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.authors.[0].authorId").value(AuthorConstants.PERA_ID))
				.andExpect(jsonPath("$.authors.[0].firstName").value(AuthorConstants.FIRST_NAME_PERA))
				.andExpect(jsonPath("$.authors.[0].lastName").value(AuthorConstants.LAST_NAME_PERA))
				.andExpect(jsonPath("$.authors.[1].authorId").value(AuthorConstants.DESA_ID))
				.andExpect(jsonPath("$.authors.[1].firstName").value(AuthorConstants.FIRST_NAME_DESA))
				.andExpect(jsonPath("$.authors.[1].lastName").value(AuthorConstants.LAST_NAME_DESA))
				.andExpect(jsonPath("$.authors.[2].authorId").value(AuthorConstants.JOVA_ID))
				.andExpect(jsonPath("$.authors.[2].firstName").value(AuthorConstants.FIRST_NAME_JOVA))
				.andExpect(jsonPath("$.authors.[2].lastName").value(AuthorConstants.LAST_NAME_JOVA))
				.andDo(document("{class-name}/{method-name}", 
									preprocessResponse(prettyPrint()),
									responseFields(authorListResponseFields())
				));				
	}

	@Test
	public void testGetAuthor() throws Exception {
		when(authorService.getOne(AuthorConstants.DESA_ID)).thenReturn(
				new AuthorDTO(
						AuthorConstants.DESA_ID,
						AuthorConstants.FIRST_NAME_DESA, 
						AuthorConstants.LAST_NAME_DESA));

		mockMvc.perform(get(UrlPrefix.GET_AUTHORS + "/{id}", AuthorConstants.DESA_ID)
				.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.authorId").value(AuthorConstants.DESA_ID))
				.andExpect(jsonPath("$.firstName").value(AuthorConstants.FIRST_NAME_DESA))
				.andExpect(jsonPath("$.lastName").value(AuthorConstants.LAST_NAME_DESA))
				.andDo(document("{class-name}/{method-name}", pathParameters(
						parameterWithName("id").description("The unique identifier of the author")),
						author()));
	}
	
	private ResponseFieldsSnippet author() {
        return responseFields(
            fieldWithPath("authorId").description("The unique identifier of the author"),
            fieldWithPath("firstName").description("The first name of the author"),
            fieldWithPath("lastName").description("The first name of the author"));
    }
	
	 private FieldDescriptor[] authorListResponseFields() {
		    return new FieldDescriptor[] {
		    		fieldWithPath("authors.[].authorId").description("The unique identifier of the author"),
		            fieldWithPath("authors.[].firstName").description("The first name of the author"),
		            fieldWithPath("authors.[].lastName").description("The first name of the author")

		    };
		  }


}
