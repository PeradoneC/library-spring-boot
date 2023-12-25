package com.peradone.library.persistence.dao;

import java.util.ArrayList;

import com.peradone.library.persistence.dto.Author;

public interface AuthorDao {
	
	ArrayList<Author> getAllAuthors();

	Author getAuthorById(int id);

	boolean saveAuthor(Author author);

	boolean updateAuthor(Author author);

	boolean deleteAuthorById(int id);
	
}
