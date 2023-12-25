package com.peradone.library.database.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.peradone.library.persistence.dao.AuthorDao;
import com.peradone.library.persistence.dao.impl.MySqlJdbcAuthorDao;

class MySqlJdbcAuthorDaoTest {

	private AuthorDao authorDao;
	
	@BeforeEach
	void setUp() {
		this.authorDao = new MySqlJdbcAuthorDao();
	}

	@Test
	void getAllAuthors() {
		var authors = authorDao.getAllAuthors();
		assertNotNull(authors);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	void getAuthorByIdTest(int id) {
		var author = authorDao.getAuthorById(id);
		assertNotNull(author);
	}
	
}
