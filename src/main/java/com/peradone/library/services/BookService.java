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

import com.peradone.library.persistence.dao.BookDao;
import com.peradone.library.persistence.dto.Book;

@Component
@RestController
public class BookService {

	@Autowired
	private BookDao bookDao;
	
	@GetMapping("/books")
	ArrayList<Book> all() {
		return bookDao.getAllBooks();
	}
	
	@GetMapping("/books/{id}")
	Book one(@PathVariable int id) {
		return bookDao.getBookById(id);
	}
	
	@PostMapping("/books")
	boolean newBook(@RequestBody Book book) {
		return bookDao.saveBook(book);
	}
	
	@PutMapping("/books/{id}")
	boolean replaceBook(@RequestBody Book book , @PathVariable int id) {
		book.setId(id);
		return bookDao.updateBook(book);
	}
	
	@DeleteMapping("/books/{id}")
	boolean deleteBook(@PathVariable int id) {
		return bookDao.deleteBookById(id);
	}
	
}
