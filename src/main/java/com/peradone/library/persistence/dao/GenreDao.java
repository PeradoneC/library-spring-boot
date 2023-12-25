package com.peradone.library.persistence.dao;

import java.util.ArrayList;

import com.peradone.library.persistence.dto.Genre;

public interface GenreDao {
	
	ArrayList<Genre> getAllGenres();

	Genre getGenreById(int id);

	Genre getGenreByName(String name);

	boolean saveGenre(Genre genre);

	boolean updateGenre(Genre genre);

	boolean deleteGenreById(int id);
	
}
