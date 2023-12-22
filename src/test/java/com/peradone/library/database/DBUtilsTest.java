package com.peradone.library.database;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DBUtilsTest {

	@Test
	void dbConnectionTest() {
		var actual = DBUtils.getConnection();
		assertNotNull(actual);
	}

}
