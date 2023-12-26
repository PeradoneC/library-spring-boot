package com.peradone.library.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;

import com.peradone.library.persistence.DBUtils;
import com.peradone.library.persistence.dao.AuthorDao;
import com.peradone.library.persistence.dao.BookDao;
import com.peradone.library.persistence.dao.PublisherDao;
import com.peradone.library.persistence.dto.Book;

public class MySqlJdbcBookDao implements BookDao {

	@Autowired
	private AuthorDao authorDao;
	@Autowired
	private PublisherDao publisherDao;

	@Override
	public ArrayList<Book> getAllBooks() {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM book ORDER BY id");
			try (var rs = ps.executeQuery()) {
				ArrayList<Book> books = new ArrayList<>();
				while (rs.next()) {
					books.add(getBookData(rs));
				}
				return books;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book getBookById(int id) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM book WHERE id = ?");
			ps.setInt(1, id);
			try (var rs = ps.executeQuery()){
				if(rs.next()) {
					Book book = getBookData(rs);
					conn.close();
					return book;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Book getBookData(ResultSet rs) throws SQLException {
		Book book = new Book();
		book.setId(rs.getInt("id"));
		book.setIsbn(rs.getString("isbn"));
		book.setTitle(rs.getString("title"));
		book.setPublicationDate(rs.getDate("publication_date"));
		book.setEdition(rs.getInt("edition"));
		book.setPrice(rs.getDouble("price"));
		book.setAuthor(authorDao.getAuthorById(rs.getInt("fk_author")));
		book.setPublisher(publisherDao.getPublisherById(rs.getInt("fk_publisher")));
		return book;
	}

	@Override
	public boolean saveBook(Book book) {
		try (var conn = DBUtils.getConnection()) {
			var resetAutoIncrement = conn.prepareStatement("ALTER TABLE book AUTO_INCREMENT = 1;");
			resetAutoIncrement.executeUpdate();
			var ps = conn.prepareStatement(
					"INSERT INTO book (isbn , title , publication_date , edition , price , fk_author , fk_publisher) VALUES(? , ? , ? , ? , ? , ? , ?);");
			setPrepareStatementData(ps, book);
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateBook(Book book) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement(
					"UPDATE book SET isbn = ? , title = ? , publication_date = ? , edition = ? , price = ? , fk_author = ? , fk_publisher = ? WHERE id = ?");
			setPrepareStatementData(ps, book);
			ps.setInt(8, book.getId());
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void setPrepareStatementData(PreparedStatement ps , Book book) throws SQLException {
		ps.setString(1, book.getIsbn());
		ps.setString(2 , book.getTitle());
		ps.setDate(3, book.getPublicationDate());
		ps.setInt(4, book.getEdition());
		ps.setDouble(5, book.getPrice());
		ps.setInt(6, book.getAuthor().getId());
		ps.setInt(7, book.getPublisher().getId());
	}

	@Override
	public boolean deleteBookById(int id) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("DELETE FROM book WHERE id = ?;");
			ps.setInt(1, id);
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
