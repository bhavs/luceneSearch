package com.example.lucene.luceneAttempt21.controller;

import com.example.lucene.luceneAttempt21.entity.Books;
import com.example.lucene.luceneAttempt21.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookservice;

    public BookController(BookService bookService){
        this.bookservice=bookService;
    }

    //create book entry (post)
    //display all books(get)

    @RequestMapping(value = "book/add", method = RequestMethod.POST)
    public void createBookEntry(Books book){
        bookservice.createBookEntry(book);

    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public void getBookEntryByAuthorName(String authorName){
        bookservice.getBooksByAuthorName(authorName);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public void getAllBookEntry(){
        bookservice.getAllBooks();
    }
}
