package com.peradone.library.persistence.dao;

import java.util.ArrayList;

import com.peradone.library.persistence.dto.Publisher;

public interface PublisherDao {

	ArrayList<Publisher> getAllPublishers();
	
	Publisher getPublisherById(int id);

	boolean savePublisher(Publisher publisher);

	boolean updatePublisher(Publisher publisher);

	boolean deletePublisherById(int id);
	
}
