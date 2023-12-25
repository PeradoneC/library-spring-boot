package com.peradone.library.services;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.peradone.library.persistence.dao.PublisherDao;
import com.peradone.library.persistence.dto.Publisher;

@Component
@RestController
public class PublisherService {

	@Autowired
	PublisherDao publisherDao;
	
	@GetMapping("/publishers")
	ArrayList<Publisher> all() {
		return publisherDao.getAllPublishers();
	}
	
	@GetMapping("/publishers/{id}")
	Publisher one(@PathVariable int id) {
		return publisherDao.getPublisherById(id);
	}
	
	@PostMapping("/publishers")
	boolean newGenre(@RequestBody Publisher publisher) {
		return publisherDao.savePublisher(publisher);
	}
	
	@PutMapping("/publishers/{id}")
	boolean replacePublisher(@RequestBody Publisher publisher , @PathVariable int id) {
		publisher.setId(id);
		return publisherDao.updatePublisher(publisher);
	}
	
	@DeleteMapping("/publishers/{id}")
	boolean deletePublisher(@PathVariable int id) {
		return publisherDao.deletePublisherById(id);
	}
	
}
