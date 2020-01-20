package org.example.audiobookS.repos;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.Book;
import org.example.audiobookS.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
    List<Book> findByAuthorAuthornameLike(String authorname);
    List<Book> findByAuthorAuthornameContaining(String authorname);
    List<Book> findByAuthorAuthornameContainingAndNameContaining(String authorname, String name);
    List<Book> findByAuthorAuthornameContainingAndNameContainingOrderByName(String authorname, String name);
    List<Book> findByAuthorAuthornameContainingAndNameContainingOrderByAuthorAuthorname(String authorname, String name);
    List<Book> findByAuthorAuthornameContainingAndNameContainingOrderByAuthorAuthornameAscNameAsc(String authorname, String name);
    List<Book> findByAuthorAuthornameContainingAndNameContainingOrderByNameAscAuthorAuthornameAsc(String authorname, String name);



   /* @Query(value = "SELECT * FROM  book_genre \n" +
            "INNER JOIN book  ON book_genre.book_id = book.id \n" +
            "INNER JOIN genre  ON book_genre.genre_id = genre.id \n" +
            "INNER JOIN author  ON book.author_id = author.id \n" +
            "WHERE  book.name LIKE :b AND author.authorname LIKE :a AND genre.name LIKE :g", nativeQuery = true)
    List<Book>findByMatchBookNameAndAuthorNameAndGenreName(@Param("b") String book, @Param("a") String author, @Param("g") String genre);*/


    @Query(value = "SELECT * FROM book\n" +
            " JOIN author ON book.author_id = author.id\n" +
            " JOIN book_genre ON book_genre.book_id = book.id\n" +
            " JOIN genre ON book_genre.genre_id = genre.id" +
            " JOIN user_id ON book.id = user_id.book_id" , nativeQuery = true)
    List<Book>findByBookAllJoinAuthorGenres();

    @Query(value = "SELECT * FROM book\n" +
            " JOIN author ON book.author_id = author.id\n" +
            " JOIN book_genre ON book_genre.book_id = book.id\n" +
            " JOIN genre ON book_genre.genre_id = genre.id" +
            " JOIN user_id ON book.id = user_id.book_id WHERE  book.name LIKE :b AND author.authorname LIKE :a AND genre.name LIKE :g", nativeQuery = true)
    Set<Book> findByMatchBookNameAndAuthorNameAndGenreName(@Param("b") String book, @Param("a") String author, @Param("g") String genre);

    @Query(value = "SELECT * FROM book\n" +
            " JOIN author ON book.author_id = author.id\n" +
            " JOIN book_genre ON book_genre.book_id = book.id\n" +
            " JOIN genre ON book_genre.genre_id = genre.id" +
            " JOIN user_id ON book.id = user_id.book_id WHERE  book.name LIKE :b AND author.authorname LIKE :a AND genre.name LIKE :g ORDER BY book.name ", nativeQuery = true)
    Set<Book>findByMatchBookNameAndAuthorNameAndGenreNameOrderByBook(@Param("b") String book, @Param("a") String author, @Param("g") String genre);

    @Query(value = "SELECT * FROM book\n" +
            " JOIN author ON book.author_id = author.id\n" +
            " JOIN book_genre ON book_genre.book_id = book.id\n" +
            " JOIN genre ON book_genre.genre_id = genre.id" +
            " JOIN user_id ON book.id = user_id.book_id WHERE  book.name LIKE :b AND author.authorname LIKE :a AND genre.name LIKE :g ORDER BY author.authorname ", nativeQuery = true)
    Set<Book>findByMatchBookNameAndAuthorNameAndGenreNameOrderByAuthor(@Param("b") String book, @Param("a") String author, @Param("g") String genre);

    @Query(value = "SELECT * FROM book\n" +
            " JOIN author ON book.author_id = author.id\n" +
            " JOIN book_genre ON book_genre.book_id = book.id\n" +
            " JOIN genre ON book_genre.genre_id = genre.id" +
            " JOIN user_id ON book.id = user_id.book_id WHERE  book.name LIKE :b AND author.authorname LIKE :a AND genre.name LIKE :g ORDER BY genre.name ", nativeQuery = true)
    Set<Book>findByMatchBookNameAndAuthorNameAndGenreNameOrderByGenre(@Param("b") String book, @Param("a") String author, @Param("g") String genre);


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