package org.springframework.data.demo.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.demo.domain.Author;
import org.springframework.data.demo.domain.Book;
import org.springframework.stereotype.Component;

import com.google.code.morphia.Datastore;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

@Component
public class DbHelper {

	@Autowired
	Datastore datastore;

	public String getDump(Class<?> entityClass) {
		final StringBuilder mongoData = new StringBuilder();
		DBCollection coll = datastore.getCollection(entityClass);
		for (DBObject dbo : coll.find()) {
			mongoData.append(dbo.toString());
			mongoData.append(" ");
		}
		String ret = "";
		if (mongoData.length() > 0) {
			ret = entityClass.getSimpleName() + " collection: " + mongoData.toString();
		}
		return ret;
	}
	
	public void clear() {
		datastore.getCollection(Book.class).drop();
		datastore.getCollection(Author.class).drop();
	}
}
