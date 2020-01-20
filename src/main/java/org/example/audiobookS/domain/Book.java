package org.example.audiobookS.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity //This tells Hibernate to make a table out of this class
public class Book {
    @Id //This is the identification field
    @GeneratedValue(strategy = GenerationType.AUTO)//This is field generated automatically
    private Long id;

    @NotNull
    @Size(
            min = 1,
            max = 255,
            message = "Name is required, maximum 255 characters."
    )
    private String name; //first name and last name of author with space
//-----------------------------------------------------------------------
    /*@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;*/

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_id",
            joinColumns = {@JoinColumn (name = "book_id")},
            inverseJoinColumns = {@JoinColumn(nullable = false)}
    )
    private User owner;


//---------------------------------------------------------------------------
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

  //-----------------------------------------------------------------
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
        name = "book_genre",
        joinColumns = {@JoinColumn (name = "book_id")},
        inverseJoinColumns = {@JoinColumn(name = "genre_id")}
    )
    private Set<Genre> genres;

    public Set<Genre> getGenres() {
        return genres;
    }

    public  String getGenresName(){

        return !genres.isEmpty()? genres.toString().replaceAll("^\\[|\\]$", ""): "<none>";
    }



    public void setGenres(Set<Genre> genres) {
        this.genres = genres;
    }
//-------------------------------------------------------------

    private String filename;

    public Book() {
    }

    public Book(String name) {
        this.name = name;
    }

    public Book(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Book(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }

    public Book(String name, User owner, Author author) {
        this.name = name;
        this.owner = owner;
        this.author = author;
    }

    public Book(@NotNull @Size(
            min = 1,
            max = 255,
            message = "Name is required, maximum 255 characters."
    ) String name, User owner, Author author, Set<Genre> genres) {
        this.name = name;
        this.owner = owner;
        this.author = author;
        this.genres = genres;
    }

    //-----------------------------------------------------------------------------------------------
    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    //-----------------------------------------------------------------------------------------------
    public  String getOwnerName(){
      return owner !=null ? owner.getUsername(): "<none>";
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    //-----------------------------------------------------------------------------------------------
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //-----------------------------------------------------------------------------------------------

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    //-----------------------------------------------------------------------------------------------
    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public  String getAuthorName(){
       return author !=null ? author.getAuthorname(): "<none>";
    }
    //-------------------------------------------------------------------------------------------------


    public Book(@NotNull @Size(
            min = 1,
            max = 255,
            message = "Name is required, maximum 255 characters."
    ) String name, Author author) {
        this.name = name;
        this.author = author;
    }

    //----------------------------------------------------------------------------------------------------
    @Override
    public String toString() {
        return String.format("Book [id=%s, name=%s, owner=%s, author=%s]", id, name, owner, author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(name, book.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}

// genres.stream().map(Genre::getName).collect(Collectors.toList()))
