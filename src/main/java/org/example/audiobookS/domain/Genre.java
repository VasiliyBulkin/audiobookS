package org.example.audiobookS.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(
            min = 1,
            max = 255,
            message = "Name is required, maximum 255 characters."
    )
    private String name;


    //------------------------------------------------------------------------------------

    @ManyToMany(mappedBy = "genres", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Book> books;

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    public String getBooksName() {

        return !books.isEmpty() ? books.toString().replaceAll("^\\[|\\]$", "") : "<none>";
    }
    //---------------------------------------------------------------------

    public Genre() {
    }

    public Genre(@NotNull @Size(
            min = 1,
            max = 255,
            message = "Name is required, maximum 255 characters."
    ) String name) {
        this.name = name;
    }

    public Genre(@NotNull @Size(
            min = 1,
            max = 255,
            message = "Name is required, maximum 255 characters."
    ) String name, Set<Book> books) {
        this.name = name;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(name, genre.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
