package org.springframework.data.demo.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.domain.Author;
import org.springframework.data.demo.domain.Book;
import org.springframework.stereotype.Repository;

import com.google.code.morphia.Datastore;

@Repository
public class MongoBookShelf implements BookShelf {
	
	@Autowired
	Datastore datastore;

	@Override
	public void add(Book book) {
		lookUpAuthor(book);
		datastore.save(book);
	}

	@Override
	public void save(Book book) {
		lookUpAuthor(book);
		datastore.save(book);
	}

	@Override
	public Book find(String isbn) {
		return datastore.find(Book.class).filter("isbn", isbn).get();
	}

	@Override
	public void remove(String isbn) {
		datastore.delete(Book.class, isbn);
	}

	@Override
	public List<Book> findAll() {
		List<Book> books = datastore.find(Book.class).asList(); 
		return Collections.unmodifiableList(books);
	}

	private void lookUpAuthor(Book book) {
		if (book.getAuthor() != null) {
			Author existing = datastore.find(Author.class).filter("name", book.getAuthor().getName()).get();
			if (existing != null) {
				book.setAuthor(existing);
			}
			else {
				datastore.save(book.getAuthor());
			}
		}
	}
}
