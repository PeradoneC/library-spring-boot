package com.peradone.library.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.peradone.library.persistence.dao.GenreDao;
import com.peradone.library.persistence.dto.Genre;

@Component
@RestController
public class GenreController {
	
	@Autowired
	private GenreDao genreDao;

	@GetMapping("/genres")
	ArrayList<Genre> all() {
		return genreDao.getAllGenres();
	}
	
	@GetMapping("/genres/{id}")
	Genre one(@PathVariable int id) {
		return genreDao.getGenreById(id);
	}
	
	@GetMapping("/genres/name/{name}")
	Genre one(@PathVariable String name) {
		return genreDao.getGenreByName(name);
	}
	
	@PostMapping("/genres")
	boolean newGenre(@RequestBody Genre newGenre) {
		return genreDao.saveGenre(newGenre);
	}
	
	@PutMapping("/genres/{id}")
	boolean replaceGenre(@RequestBody Genre genre , @PathVariable int id) {
		genre.setId(id);
		return genreDao.updateGenre(genre);
	}
	
	@DeleteMapping("/genres/{id}")
	boolean deleteGenre(@PathVariable int id) {
		return genreDao.deleteGenreById(id);
	}
	
}
