package com.example.lucene.luceneAttempt21.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Books {

    @GeneratedValue()
    @Id
    private long bookID;
    private String title;
    private String authorName;
    private long ISBN;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public long getISBN() {
        return ISBN;
    }

    public void setISBN(long ISBN) {
        this.ISBN = ISBN;
    }

    public long getBookID() {
        return bookID;
    }

    public void setBookID(long bookID) {
        this.bookID = bookID;
    }

    @Override
    public String toString() {
        return "Books{" +
                "ID=" + bookID +
                ", title='" + title + '\'' +
                ", authorName='" + authorName + '\'' +
                ", ISBN=" + ISBN +
                '}';
    }
}
