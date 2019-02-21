package com.example.lucene.luceneAttempt21.repository;

import com.example.lucene.luceneAttempt21.entity.Books;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Books, Long> {

    List<Books> findByBookID(Long ID);
    void findByTitle(String title);
    List<Books> findByAuthorName(String AuthorName);
    List<Books> findAll();


}
