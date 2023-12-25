package com.peradone.library.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.peradone.library.persistence.DBUtils;
import com.peradone.library.persistence.dao.GenreDao;
import com.peradone.library.persistence.dto.Genre;

public class MySqlJdbcGenreDao implements GenreDao {

	@Override
	public ArrayList<Genre> getAllGenres() {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM genre ORDER BY id;");
			try (var rs = ps.executeQuery()) {
				ArrayList<Genre> genres = new ArrayList<>();
				while (rs.next()) {
					genres.add(getGenreData(rs));
				}
				conn.close();
				return genres;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Genre getGenreById(int id) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM genre WHERE id = ?;");
			ps.setInt(1, id);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					Genre genre = getGenreData(rs);
					conn.close();
					return genre;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Genre getGenreByName(String name) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM genre WHERE name = ?;");
			ps.setString(1, name);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					Genre genre = getGenreData(rs);
					conn.close();
					return genre;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Genre getGenreData(ResultSet rs) throws SQLException {
		Genre genre = new Genre();
		genre.setId(rs.getInt("id"));
		genre.setName(rs.getString("name"));
		return genre;
	}

	@Override
	public boolean saveGenre(Genre genre) {
		try (var conn = DBUtils.getConnection()) {
			var resetAutoIncrement = conn.prepareStatement("ALTER TABLE genre AUTO_INCREMENT = 1;");
			resetAutoIncrement.executeUpdate();
			var ps = conn.prepareStatement("INSERT INTO genre (name) VALUES(?);");
			setPrepareStatementData(ps, genre);
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateGenre(Genre genre) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("UPDATE genre SET name = ? WHERE id = ?");
			setPrepareStatementData(ps, genre);
			ps.setInt(2, genre.getId());
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	private void setPrepareStatementData(PreparedStatement ps, Genre genre) throws SQLException {
		ps.setString(1, genre.getName());
	}

	@Override
	public boolean deleteGenreById(int id) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("DELETE FROM genre WHERE id = ?;");
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
