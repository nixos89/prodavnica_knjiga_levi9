package com.levi9.prodavnica.config;

import com.levi9.prodavnica.dto.AuthorDTO;
import com.levi9.prodavnica.model.Author;

import java.util.LinkedList;

public class AuthorConstants {

	public static final Long PERA_ID = 1L;
	public static final String FIRST_NAME_PERA = "Petar";
	public static final String LAST_NAME_PERA = "Peric";

	public static final Long JOVA_ID = 2L;
	public static final String FIRST_NAME_JOVA = "Jovan";
	public static final String LAST_NAME_JOVA = "Jovanovic";

	public static final Long DESA_ID = 3L;
	public static final String FIRST_NAME_DESA = "Desanka";
	public static final String LAST_NAME_DESA = "Maksimovic";

	public static LinkedList<Author> authors() {
		Author authPera = new Author(FIRST_NAME_PERA, LAST_NAME_PERA);
		authPera.setAuthorId(PERA_ID);

		Author authDesa = new Author(FIRST_NAME_DESA, LAST_NAME_DESA);
		authDesa.setAuthorId(AuthorConstants.DESA_ID);

		Author authJova = new Author(FIRST_NAME_JOVA, LAST_NAME_JOVA);
		authJova.setAuthorId(JOVA_ID);

		LinkedList<Author> authors = new LinkedList<Author>();
		authors.add(authPera);
		authors.add(authDesa);
		authors.add(authJova);
		return authors;
	}

	public static LinkedList<AuthorDTO> authorDTOs() {
		AuthorDTO authDTOPera = new AuthorDTO(AuthorConstants.PERA_ID, AuthorConstants.FIRST_NAME_PERA,
				AuthorConstants.LAST_NAME_PERA);

		AuthorDTO authDTODesa = new AuthorDTO(AuthorConstants.DESA_ID, AuthorConstants.FIRST_NAME_DESA,
				AuthorConstants.LAST_NAME_DESA);

		AuthorDTO authDTOJova = new AuthorDTO(AuthorConstants.JOVA_ID, AuthorConstants.FIRST_NAME_JOVA,
				AuthorConstants.LAST_NAME_JOVA);

		LinkedList<AuthorDTO> authorDTOList = new LinkedList<AuthorDTO>();
		authorDTOList.add(authDTOPera);
		authorDTOList.add(authDTODesa);
		authorDTOList.add(authDTOJova);
		return authorDTOList;
	}

}
