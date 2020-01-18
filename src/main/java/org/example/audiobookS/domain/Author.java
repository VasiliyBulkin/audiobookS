package org.example.audiobookS.domain;



import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @NotNull
    @Size(
            min = 2,
            max = 255,
            message = "Name is required, minimum 2,  maximum 255 characters."
    )

    private String authorname;
//-------------------------------------------------------------------------
    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Book> books;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
//---------------------------------------------------------------------------------
    public Author() {
    }

    public Author(String authorname) {
        this.authorname = authorname;
    }

    public Author(Long id, String authorname) {
        this.id = id;
        this.authorname = authorname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }
}
