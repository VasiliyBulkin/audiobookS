package org.example.audiobookS.repos;

import org.example.audiobookS.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepo  extends CrudRepository<Author, Long> {
}
