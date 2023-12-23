package com.peradone.library.persistence;

import java.util.ArrayList;

import com.peradone.library.persistence.dao.GenreDao;
import com.peradone.library.persistence.dao.impl.MySqlJdbcGenreDao;
import com.peradone.library.persistence.dto.Genre;

public class DaoTest {

	public static void main(String[] args) {
		GenreDao genreDao = new MySqlJdbcGenreDao();
		ArrayList<Genre> genres = genreDao.getAllGenres();
		for (Genre genre : genres) {
			System.out.println(genre);
		}
		
		System.out.println("\n-------------------------------------------------\n");
		
		Genre genre = genreDao.getGenreById(2);
		System.out.println(genre);
		
		System.out.println("\n-------------------------------------------------\n");
		
		genre = genreDao.getGenreByName("Horror");
		System.out.println(genre);
		
		System.out.println("\n-------------------------------------------------\n");
		
		Genre newGenre = new Genre("Isekai");
//		System.out.println(genreDao.saveGenre(newGenre));
		newGenre.setId(9);
		newGenre.setName("Isekai");
		System.out.println(genreDao.updateGenre(newGenre));
		
		System.out.println("\n-------------------------------------------------\n");
		
//		System.out.println(genreDao.deleteGenreById(9));
		
	}

}
