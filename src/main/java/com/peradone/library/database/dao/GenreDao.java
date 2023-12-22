package com.peradone.library.database.dao;

import java.util.ArrayList;

import com.peradone.library.database.dto.Genre;

public interface GenreDao {
	ArrayList<Genre> getAllGenres();
	Genre getGenreById(int id);
	Genre getGenreByName(String name);
	boolean saveGenre(Genre genre);
	boolean deleteGenreById(int id);
	boolean updateGenre(Genre genre);
}
