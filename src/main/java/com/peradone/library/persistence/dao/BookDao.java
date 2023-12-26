package com.peradone.library.persistence.dao;

import java.util.ArrayList;

import com.peradone.library.persistence.dto.Book;

public interface BookDao {

	ArrayList<Book> getAllBooks();

	Book getBookById(int id);

	boolean saveBook(Book book);

	boolean updateBook(Book book);

	boolean deleteBookById(int id);

}
