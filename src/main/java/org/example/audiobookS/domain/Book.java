package org.example.audiobookS.domain;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User owner;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;


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

    @Override
    public String toString() {
        return String.format("Book [id=%s, name=%s, owner=%s, author=%s]", id, name, owner, author);
    }

}
