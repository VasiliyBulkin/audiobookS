package org.example.audiobookS.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(
            min = 1,
            max = 255,
            message = "Name is required, maximum 255 characters."
    )
    private String name;


    //------------------------------------------

    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = {@JoinColumn (name = "genre_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private Set<Book> bookSet= new HashSet<>();

    public Set<Book> getBookSet() {
        return bookSet;
    }

    public void setBookSet(Set<Book> bookSet) {
        this.bookSet = bookSet;
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
    ) String name, Set<Book> bookSet) {
        this.name = name;
        this.bookSet = bookSet;
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
}
