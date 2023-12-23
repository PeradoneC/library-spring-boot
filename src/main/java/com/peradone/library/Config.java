package com.peradone.library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.peradone.library.persistence.dao.GenreDao;
import com.peradone.library.persistence.dao.impl.MySqlJdbcGenreDao;

@Configuration
public class Config {

    @Bean
    GenreDao getGenreDao() {
		return new MySqlJdbcGenreDao();
	}
	
}
