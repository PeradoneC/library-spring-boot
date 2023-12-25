package com.peradone.library.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.peradone.library.persistence.DBUtils;
import com.peradone.library.persistence.dao.AuthorDao;
import com.peradone.library.persistence.dto.Author;

public class MySqlJdbcAuthorDao implements AuthorDao {

	@Override
	public ArrayList<Author> getAllAuthors() {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM author ORDER BY id");
			try (var rs = ps.executeQuery()) {
				ArrayList<Author> authors = new ArrayList<>();
				while (rs.next()) {
					authors.add(getAuthorData(rs));
				}
				conn.close();
				return authors;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Author getAuthorById(int id) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM author WHERE id = ?;");
			ps.setInt(1, id);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					Author author = getAuthorData(rs);
					conn.close();
					return author;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Author getAuthorData(ResultSet rs) throws SQLException {
		Author author = new Author();
		author.setId(rs.getInt("id"));
		author.setFirstName(rs.getString("first_name"));
		author.setSecondName(rs.getString("second_name"));
		author.setCompanyName(rs.getString("company_name"));
		return author;
	}

	@Override
	public boolean saveAuthor(Author author) {
		try (var conn = DBUtils.getConnection()) {
			var resetAutoIncrement = conn.prepareStatement("ALTER TABLE author AUTO_INCREMENT = 1;");
			resetAutoIncrement.executeUpdate();
			var ps = conn.prepareStatement(
					"INSERT INTO author (first_name , second_name , company_name) VALUES(? , ? , ?);");
			setPrepareStatementData(ps, author);
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateAuthor(Author author) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement(
					"UPDATE author SET first_name = ? , second_name = ? , company_name = ? WHERE id = ?");
			setPrepareStatementData(ps, author);
			ps.setInt(4, author.getId());
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void setPrepareStatementData(PreparedStatement ps, Author author) throws SQLException {
		ps.setString(1, author.getFirstName());
		ps.setString(2, author.getSecondName());
		ps.setString(3, author.getCompanyName());
	}

	@Override
	public boolean deleteAuthorById(int id) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("DELETE FROM author WHERE id = ?;");
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
