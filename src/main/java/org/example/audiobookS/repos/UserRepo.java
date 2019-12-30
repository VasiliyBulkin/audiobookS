package org.example.audiobookS.repos;

import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
