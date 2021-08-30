package ru.otus.homework.mongock.changelog.test;

import com.github.cloudyrock.mongock.ChangeLog;
import com.github.cloudyrock.mongock.ChangeSet;
import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

@ChangeLog
public class DatabaseChangelog {

    @ChangeSet(order = "001", id = "dropDb", author = "musin", runAlways = true)
    public void dropDb(MongoDatabase db) {
        db.drop();
    }

    @ChangeSet(order = "002", id = "insertInitData", author = "musin")
    public void insertInitData(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("genres");
        myCollection.insertMany(Arrays.asList(
                new Document().append("_id", "1").append("name", "novel")
        ));
    }

    @ChangeSet(order = "003", id = "insertAuthors", author = "musin")
    public void insertAuthors(MongoDatabase db) {
        MongoCollection<Document> myCollection = db.getCollection("authors");
        myCollection.insertOne(
                new Document()
                        .append("_id", "1")
                        .append("fullName", "Tolstoy Lev Nikolaevich")
        );
    }

    @ChangeSet(order = "004", id = "insertBook", author = "musin")
    public void insertBook(MongoDatabase db) {
        final MongoCollection<Document> books = db.getCollection("books");

        BasicDBObject author = new BasicDBObject();
        author.put("_id", "1");
        author.put("fullName", "Tolstoy");

        BasicDBObject genre = new BasicDBObject();
        genre.put("_id", "1");
        genre.put("name", "novel");

        books.insertOne(new Document()
                .append("_id", "1")
                .append("title", "War and peace")
                .append("author", author)
                .append("genre", genre));
    }

}
