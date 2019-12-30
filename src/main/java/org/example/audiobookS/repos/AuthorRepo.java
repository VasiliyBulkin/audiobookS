package org.example.audiobookS.repos;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

import org.example.audiobookS.domain.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepo extends CrudRepository<Author, Long> {

    List<Author> findByNameContaining(String name);

}