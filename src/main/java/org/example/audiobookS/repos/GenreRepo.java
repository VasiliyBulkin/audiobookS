package org.example.audiobookS.repos;

import org.example.audiobookS.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GenreRepo extends JpaRepository<Genre, Long> {
    Genre findByName (String name);
    List<Genre> findByNameContaining(String string);

}
