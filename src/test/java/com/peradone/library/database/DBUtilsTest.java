package com.peradone.library.database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.peradone.library.persistence.DBUtils;

class DBUtilsTest {

	@Test
	void dbConnectionTest() {
		var actual = DBUtils.getConnection();
		assertNotNull(actual);
	}

}
