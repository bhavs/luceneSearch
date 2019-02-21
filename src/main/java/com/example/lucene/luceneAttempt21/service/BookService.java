package com.example.lucene.luceneAttempt21.service;

import com.example.lucene.luceneAttempt21.entity.Books;
import com.example.lucene.luceneAttempt21.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;
    private LuceneBookService luceneBookService;

    public BookService(BookRepository bookRepository, LuceneBookService luceneBookService){
        this.bookRepository = bookRepository;
        this.luceneBookService= luceneBookService;
    }

    public void createBookEntry(Books book){
        bookRepository.save(book);
        luceneBookService.createBookIndexByBookId(book);
    }

    public void getBooksByAuthorName(String name){
        List<Books> books= bookRepository.findByAuthorName(name);
        System.out.println("Printing values from the db query");
        for(Books book: books){
            book.toString();
            luceneBookService.displayAuthorSearchResults(book);
        }

    }

    public void getAllBooks(){
        Iterable<Books> books= bookRepository.findAll();
        System.out.println("Printing values from the db query");
        for(Books book: books){
            book.toString();
        }
    }


}
