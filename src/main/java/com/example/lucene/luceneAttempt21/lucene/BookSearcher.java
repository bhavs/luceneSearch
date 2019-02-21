package com.example.lucene.luceneAttempt21.lucene;

import com.example.lucene.luceneAttempt21.config.LuceneConfig;
import com.example.lucene.luceneAttempt21.entity.Books;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class BookSearcher {

    private LuceneConfig luceneConfig;

    @Autowired
    public BookSearcher(LuceneConfig luceneConfig){
        this.luceneConfig = luceneConfig;
    }

    private IndexSearcher createSearcher(Long bookId) throws IOException {
        Directory dir = FSDirectory
                .open(Paths.get(luceneConfig.getDirectory() + bookId + "/books"));
        if (!DirectoryReader.indexExists(dir)) {
            System.out.println("no indexes found in the directory");
            return null;
        }
        IndexReader reader = DirectoryReader.open(dir);
        return new IndexSearcher(reader);
    }

    public List<Books> searchByAuthorName(Long bookID, String authorName)
            throws Exception {
         List<Books> searchResults=new ArrayList<Books>();
        IndexSearcher searcher = createSearcher(bookID);
        QueryParser qp = new QueryParser("authorName", new StandardAnalyzer());
        Query authorNameQuery = qp.parse(authorName);
        List<ScoreDoc> scoreDocs= Arrays.asList(searcher.search(authorNameQuery, 10).scoreDocs);
        for (ScoreDoc scoreDoc : scoreDocs){
            Document doc = searcher.doc(scoreDoc.doc);
            Books book = new Books();
            book.setBookID(Long.valueOf(doc.get("ID")));
            book.setAuthorName(doc.get("authorName"));
            book.setTitle(doc.get("booksTitle"));
            book.setISBN(Long.valueOf(doc.get("ISBN")));
            searchResults.add(book);
        }
        return searchResults;
    }

}
