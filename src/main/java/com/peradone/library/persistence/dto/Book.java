package com.peradone.library.persistence.dto;

import java.sql.Date;

public class Book {

	private int id;
	private String isbn;
	private String title;
	private Date publicationDate;
	private int edition;
	private double price;
	private Author author;
	private Publisher publisher;

	public int getId() {
		return id;
	}

	public String getIsbn() {
		return isbn;
	}

	public String getTitle() {
		return title;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public int getEdition() {
		return edition;
	}

	public double getPrice() {
		return price;
	}

	public Author getAuthor() {
		return author;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Author [id=" + id + ", isbn=" + isbn + ", title=" + title + ", publicationDate=" + publicationDate
				+ ", edition=" + edition + "price=" + price + ", author=" + author + ", publisher=" + publisher + "]";
	}
}
