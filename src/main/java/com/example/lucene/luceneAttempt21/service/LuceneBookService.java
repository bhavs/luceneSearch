package com.example.lucene.luceneAttempt21.service;


import com.example.lucene.luceneAttempt21.entity.Books;
import com.example.lucene.luceneAttempt21.lucene.BookIndexer;
import com.example.lucene.luceneAttempt21.lucene.BookSearcher;
import com.example.lucene.luceneAttempt21.repository.BookRepository;
import org.apache.lucene.search.ScoreDoc;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Service
@Slf4j
public class LuceneBookService {

    private BookIndexer bookIndexer;
    private BookRepository bookRepository;
    private BookSearcher bookSearcher;

    public LuceneBookService( BookIndexer bookIndexer, BookRepository bookRepository, BookSearcher bookSearcher){
        this.bookIndexer =bookIndexer;
        this.bookRepository = bookRepository;
        this.bookSearcher = bookSearcher;

    }

    public void createBookIndexByBookId(Books book) {
        try {
            bookIndexer.createAllBookIndexByBookId(book);
        } catch (Exception e) {
            System.out.println("error in lucene book indexing : {}");
        }
    }

    public void displayAuthorSearchResults(Books book){
        List<Books> returnValue = null;
        System.out.println("Searching and printing the search results");
        try {
            returnValue = bookSearcher.searchByAuthorName(book.getBookID(), book.getAuthorName());
        } catch ( Exception e){
            e.printStackTrace();
        }
        for (Books booktemp : returnValue){
            booktemp.toString();
        }

    }
}
