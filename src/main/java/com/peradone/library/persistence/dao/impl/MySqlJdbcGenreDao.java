package com.peradone.library.persistence.dao.impl;

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
					Genre genre = new Genre();
					genre.setId(rs.getInt("id"));
					genre.setName(rs.getString("name"));
					genres.add(genre);
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
					Genre genre = new Genre();
					genre.setId(rs.getInt("id"));
					genre.setName(rs.getString("name"));
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
					Genre genre = new Genre();
					genre.setId(rs.getInt("id"));
					genre.setName(rs.getString("name"));
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
	public boolean saveGenre(Genre genre) {
		try (var conn = DBUtils.getConnection()) {
			var resetAutoIncrement = conn.prepareStatement("ALTER TABLE genre AUTO_INCREMENT = 1;");
			resetAutoIncrement.executeUpdate();
			var ps = conn.prepareStatement("INSERT INTO genre (name) VALUES(?);");
			ps.setString(1, genre.getName());
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
			 ps.setString(1, genre.getName());
			 ps.setInt(2, genre.getId());
			 ps.executeUpdate();
			 conn.close();
			 return true;
		 } catch(SQLException e) {
			 e.printStackTrace();
		 }
		return false;
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
