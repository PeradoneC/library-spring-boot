package com.peradone.library.persistence.dao;

import java.util.ArrayList;

import com.peradone.library.persistence.dto.Book;

public interface BookDao {

	ArrayList<Book> getAllBooks();

	Book getBookById(int id);

	Book getBookByName(String name);

	boolean saveBook(Book book);

	boolean updateBook(Book book);

	boolean deleteBookById(int id);

}
