package org.example.audiobookS.controller;

import org.example.audiobookS.domain.Book;
import org.example.audiobookS.domain.User;
import org.example.audiobookS.repos.AuthorRepo;
import org.example.audiobookS.repos.BookRepo;
import org.example.audiobookS.repos.GenreRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
@RequestMapping("/search")//responsible for advanced search
public class SearchController {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired
    private GenreRepo genreRepo;

    @GetMapping
    public String search(
            @AuthenticationPrincipal User owner,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") String filterAuthorName,
            @RequestParam(required = false, defaultValue = "") String filterGenres,
            @RequestParam(required = false) String orderBy,

            Model model
    ) {
        String filterMod = "%" + filter + "%";
        String filterAuthorNameMod = "%" + filterAuthorName + "%";
        String filterGenresMod = "%" + filterGenres + "%";
        Set<Book> books;

        if (orderBy == null) {//default search
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreName(filterMod, filterAuthorNameMod, filterGenresMod);//default search
        } else if (orderBy.equals("Author")) {
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreNameOrderByAuthor(filterMod, filterAuthorNameMod, filterGenresMod);
        } else if (orderBy.equals("Name")) {
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreNameOrderByBook(filterMod, filterAuthorNameMod, filterGenresMod);
        } else if (orderBy.equals("Genre")) {
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreNameOrderByGenre(filterMod, filterAuthorNameMod, filterGenresMod);
        } else {
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreName(filterMod, filterAuthorNameMod, filterGenresMod);//default search
        }

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        model.addAttribute("filterAuthorName", filterAuthorName);
        model.addAttribute("filterGenres", filterGenres);
        return "search";
    }
}


