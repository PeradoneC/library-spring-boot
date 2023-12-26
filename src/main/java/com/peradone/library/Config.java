package com.peradone.library;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.peradone.library.persistence.dao.AuthorDao;
import com.peradone.library.persistence.dao.BookDao;
import com.peradone.library.persistence.dao.GenreDao;
import com.peradone.library.persistence.dao.PublisherDao;
import com.peradone.library.persistence.dao.impl.MySqlJdbcAuthorDao;
import com.peradone.library.persistence.dao.impl.MySqlJdbcBookDao;
import com.peradone.library.persistence.dao.impl.MySqlJdbcGenreDao;
import com.peradone.library.persistence.dao.impl.MySqlJdbcPublisherDao;

@Configuration
public class Config {

    @Bean
    GenreDao getGenreDao() {
		return new MySqlJdbcGenreDao();
	}
    
    @Bean
    AuthorDao getAuthorDao() {
    	return new MySqlJdbcAuthorDao();
    }
    
    @Bean
    PublisherDao getPublisherDao() {
    	return new MySqlJdbcPublisherDao();
    }
    
    @Bean
    BookDao getBookDao() {
    	return new MySqlJdbcBookDao();
    }
	
}
