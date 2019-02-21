package com.example.lucene.luceneAttempt21.lucene;

import com.amazonaws.util.StringUtils;
import com.example.lucene.luceneAttempt21.config.LuceneConfig;
import com.example.lucene.luceneAttempt21.entity.Books;
import com.example.lucene.luceneAttempt21.repository.BookRepository;
import com.google.common.annotations.VisibleForTesting;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;
import com.amazonaws.util.CollectionUtils;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookIndexer {
    private LuceneConfig luceneConfig;
    private BookRepository bookRepository;
//    private Map<Long, IndexWriter> bookIndexWriterMap = new HashMap<>();


    public BookIndexer(LuceneConfig luceneConfig, BookRepository bookRepository){
        this.luceneConfig=luceneConfig;
        this.bookRepository=bookRepository;
    }



    private IndexWriter createWriter(Long bookID) throws IOException {
//        if (bookIndexWriterMap.containsKey(bookId)) {
//            IndexWriter cached = bookIndexWriterMap.get(bookId);
//            if (cached.isOpen()) {
//                return cached;
//            }
//            // cached one is not open
//            bookIndexWriterMap.remove(bookId);
//        }
        FSDirectory dir = FSDirectory.open(Paths.get(luceneConfig.getDirectory() + bookID + "/books"));
        IndexWriterConfig config = new IndexWriterConfig(new StandardAnalyzer());
        IndexWriter writer = new IndexWriter(dir, config);
//        bookIndexWriterMap.put(bookId, writer);
        return writer;
    }

//    void closeWriter(Long bookId) {
//        try {
////            IndexWriter cached = bookIndexWriterMap.get(bookId);
//            cached.close();
////            bookIndexWriterMap.remove(bookId);
//        } catch (IOException e) {
//            throw new RuntimeException("Error while closing indexes for books: " + bookId, e);
//        }
//
//    }


    public void createAllBookIndexByBookId(Books book) throws IOException {
        IndexWriter writer = createWriter(book.getBookID());
        try {
            Document bookDocuments = createDocument(book);
//            if (CollectionUtils.isNullOrEmpty(bookDocuments)) {
//                System.out.println("Books not found for ID: {}" + bookId);
//                return;
//            }
            writer.deleteAll();
            writer.addDocument(bookDocuments);
            writer.commit();
        } catch (Exception e) {
            throw new RuntimeException("Error while adding book indexes : " + book.getBookID(), e);
        } finally {
            writer.close();
        }
    }

//    private List<Document> createBookDocuments(Long bookId) {
//        List<Books> books = bookRepository.findByBookID(bookId);
//        List<Document> allBooks = null;
//        if (!CollectionUtils.isNullOrEmpty(books)) {
//            allBooks = new ArrayList<>();
//            for (Books book : books) {
//                allBooks.add(createDocument(book));
//            }
//        }
//        return allBooks;
//    }

    private Document createDocument(Books book) {
        Document document = new Document();
        document.add(new TextField("ID", String.valueOf(book.getBookID()), Field.Store.YES));
        document.add(new TextField("booksTitle", book.getTitle(), Field.Store.YES));
        document.add(new TextField("authorName", book.getAuthorName(), Field.Store.YES));
        document.add(new TextField("ISBN", String.valueOf(book.getISBN()), Field.Store.YES));
        return document;
    }

}
