package org.example.audiobookS.repos;

import org.example.audiobookS.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepo extends JpaRepository<Author, Long> {
    List<Author> findByAuthornameContainingOrderByAuthorname(String authorname);

    List<Author> findByAuthornameContainingOrderByAuthornameDesc(String authorname);

    Author findByAuthorname(String authorname);

    List<Author> findByAuthornameContaining(String authorname);

    List<Author> findByBooksNameLike(String bookName);
}
