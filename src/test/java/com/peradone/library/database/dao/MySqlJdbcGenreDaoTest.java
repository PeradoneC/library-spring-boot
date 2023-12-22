package com.peradone.library.database.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.peradone.library.database.dao.GenreDao;
import com.peradone.library.database.dao.impl.MySqlJdbcGenreDao;
import com.peradone.library.database.dto.Genre;

class MySqlJdbcGenreDaoTest {
	
	private GenreDao genreDao;
	
	@BeforeEach
	void setUp() {
		this.genreDao = new MySqlJdbcGenreDao();
	}

	@Test
	void getAllGenresTest() {
		var genres = genreDao.getAllGenres();
		assertNotNull(genres);
	}
	
	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4})
	void getGenreByIdTest(int id) {
		var genre = genreDao.getGenreById(id);
		assertNotNull(genre);
	}
	
	@ParameterizedTest
	@ValueSource(strings = {"Adventure" , "Fantasy" , "Horror"})
	void getGenreByNameTest(String name) {
		var genre = genreDao.getGenreByName(name);
		assertNotNull(genre);
	}
	
//	@ParameterizedTest
//	@CsvSource({
//		"Isekai",
//		"Romcom",
//		"Learning"
//	})
//	void saveGenreTest(String name) {
//		assertTrue(genreDao.saveGenre(new Genre(name)));
//	}
	
	

}
