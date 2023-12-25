package com.peradone.library.database.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import com.peradone.library.persistence.dao.PublisherDao;
import com.peradone.library.persistence.dao.impl.MySqlJdbcPublisherDao;

class MySqlJdbcPublisherDaoTest {
	
	private PublisherDao publisherDao;

	@BeforeEach
	void setUp() {
		this.publisherDao = new MySqlJdbcPublisherDao();
	}

	@Test
	void getAllAuthorsTest() {
		var publishers = publisherDao.getAllPublishers();
		assertNotNull(publishers);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	void getGenreByIdTest(int id) {
		var publisher = publisherDao.getPublisherById(id);
		assertNotNull(publisher);
	}

}
