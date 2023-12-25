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

import com.peradone.library.persistence.dao.AuthorDao;
import com.peradone.library.persistence.dto.Author;

@Component
@RestController
public class AuthorService {

	@Autowired
	private AuthorDao authorDao;
	
	@GetMapping("/authors")
	ArrayList<Author> all() {
		return authorDao.getAllAuthors();
	}
	
	@GetMapping("/authors/{id}")
	Author one(@PathVariable int id) {
		return authorDao.getAuthorById(id);
	}
	
	@PostMapping("/authors")
	boolean newAuthor(@RequestBody Author author) {
		return authorDao.saveAuthor(author);
	}
	
	@PutMapping("/authors/{id}")
	boolean replaceAuthor(@RequestBody Author author , @PathVariable int id) {
		author.setId(id);
		return authorDao.updateAuthor(author);
	}
	
	@DeleteMapping("/authors/{id}")
	boolean deleteAuthor(@PathVariable int id) {
		return authorDao.deleteAuthorById(id);
	}
	
}
