package org.example.audiobookS.repos;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.Book;
import org.example.audiobookS.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepo extends JpaRepository<Book, Long> {

    List<Book> findByNameContaining(String name);
    List<Book> findByNameLike(String name);
    List<Book> findByNameNotLike(String name);
    List<Book> findByOwnerLike(User owner);
    List<Book> findByAuthorLike(Author author);
    List<Book> findByNameAndAuthor(String name, Author author);
    List<Book> findByNameAndAuthorContaining(String name, Author author);
    List<Book> findByAuthorAndNameContaining(Author author, String name);
    List<Book> findByAuthorContaining(Author author);

    @Query(value = "SELECT * FROM book \n" +
            "JOIN author \n" +
            "ON book.author_id = author.id\n" +
            " WHERE book.name LIKE :b AND author.authorname LIKE :a", nativeQuery = true)
    List<Book>findByMatchNameAndAuthor(@Param("b") String book, @Param("a") String author);

    @Query(value = "SELECT * FROM book \n" +
            "JOIN author \n" +
            "ON book.author_id = author.id\n" +
            " WHERE book.name LIKE :b AND author.authorname LIKE :a ORDER BY author.authorname", nativeQuery = true)
    List<Book>findByMatchNameAndAuthorOrderByAuthorName(@Param("b") String book, @Param("a") String author);

    @Query(value = "SELECT * FROM book \n" +
            "JOIN author \n" +
            "ON book.author_id = author.id\n" +
            " WHERE book.name LIKE :b AND author.authorname LIKE :a ORDER BY book.name", nativeQuery = true)
    List<Book>findByMatchNameAndAuthorOrderBookName(@Param("b") String book, @Param("a") String author);


    @Query(value = "SELECT * FROM book \n" +
            "JOIN author \n" +
            "ON book.author_id = author.id\n" +
            " WHERE book.name LIKE :b AND author.authorname LIKE :a ORDER BY book.name, author.authorname", nativeQuery = true)
    List<Book>findByMatchNameAndAuthorOrderBookNameAndAuthorName(@Param("b") String book, @Param("a") String author);
}