package com.peradone.library.persistence.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.peradone.library.persistence.DBUtils;
import com.peradone.library.persistence.dao.PublisherDao;
import com.peradone.library.persistence.dto.Publisher;

public class MySqlJdbcPublisherDao implements PublisherDao {

	@Override
	public ArrayList<Publisher> getAllPublishers() {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM publisher ORDER BY id;");
			try (var rs = ps.executeQuery()) {
				ArrayList<Publisher> publishers = new ArrayList<>();
				while (rs.next()) {
					publishers.add(getPublisherData(rs));
				}
				conn.close();
				return publishers;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Publisher getPublisherById(int id) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("SELECT * FROM publisher WHERE id = ?;");
			ps.setInt(1, id);
			try (var rs = ps.executeQuery()) {
				if (rs.next()) {
					Publisher publisher = getPublisherData(rs);
					conn.close();
					return publisher;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Publisher getPublisherData(ResultSet rs) throws SQLException {
		Publisher publisher = new Publisher();
		publisher.setId(rs.getInt("id"));
		publisher.setName(rs.getString("name"));
		return publisher;
	}

	@Override
	public boolean savePublisher(Publisher publisher) {
		try (var conn = DBUtils.getConnection()) {
			var resetAutoIncrement = conn.prepareStatement("ALTER TABLE publisher AUTO_INCREMENT = 1;");
			resetAutoIncrement.executeUpdate();
			var ps = conn.prepareStatement("INSERT INTO publisher (name) VALUES(?);");
			setPrepareStatementData(ps, publisher);
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePublisher(Publisher publisher) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("UPDATE publisher SET name = ? WHERE id = ?");
			setPrepareStatementData(ps, publisher);
			ps.setInt(2, publisher.getId());
			ps.executeUpdate();
			conn.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	private void setPrepareStatementData(PreparedStatement ps, Publisher publisher) throws SQLException {
		ps.setString(1, publisher.getName());
	}

	@Override
	public boolean deletePublisherById(int id) {
		try (var conn = DBUtils.getConnection()) {
			var ps = conn.prepareStatement("DELETE FROM publisher WHERE id = ?;");
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
