package org.example.audiobookS.domain;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity //This tells Hibernate to make a table out of this class
public class Author {
    @Id //This is field identificator
    @GeneratedValue(strategy = GenerationType.AUTO)//This is field generated automatically
    private Integer id;
    private String name;

    public Author() {
    }

    public Author(String name) {
        this.name = name;
    }

    public Author(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
