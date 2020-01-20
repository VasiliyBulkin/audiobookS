package org.example.audiobookS.controller;

import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.Book;
import org.example.audiobookS.domain.Genre;
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

import javax.swing.*;
import java.util.*;

@Controller
@RequestMapping("/search")
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

        System.out.println("orderBy = " + orderBy);
        if (orderBy == null) {
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreName(filterMod, filterAuthorNameMod, filterGenresMod);
        } else if (orderBy.equals("Author")) {
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreNameOrderByAuthor(filterMod, filterAuthorNameMod, filterGenresMod);
        } else if (orderBy.equals("Name")) {
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreNameOrderByBook(filterMod, filterAuthorNameMod, filterGenresMod);
        } else if(orderBy.equals("Genre")){
            //books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreName(filterMod, filterAuthorNameMod, filterGenresMod);
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreNameOrderByGenre(filterMod, filterAuthorNameMod, filterGenresMod);
        }else {
            books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreName(filterMod, filterAuthorNameMod, filterGenresMod);
        }
        //books = bookRepo.findByMatchBookNameAndAuthorNameAndGenreName(filterMod, filterAuthorNameMod, filterGenresMod);
        // List<Genre> genres;
        // genres = genreRepo.findByNameContaining(filterGenres);
        //System.out.println("genres =============================================== " + genres);
        //System.out.println("**********************************************");
        // for (Genre g :
        //        genres) {

        //System.out.println("book name = " + g.getBooksName());

        //    Set<Book> bookSet =  g.getBooks();
        //   for (Book b :
        //          bookSet) {
        //      b.getName();
        // System.out.println("b = " + b);
        //   }
        // }
        // System.out.println("**********************************************");
        // books = bookRepo.findByBookAllJoinAuthorGenres();

           /* if(orderByBookName == null){
            if(orderByAuthorName == null){
                books = bookRepo.findByMatchNameAndAuthor(filterMod, filterAuthorNameMod);
            } else {
                books = bookRepo.findByMatchNameAndAuthorOrderByAuthorName(filterMod, filterAuthorNameMod);
            }
        } else {
            if (orderByAuthorName == null){
                books = bookRepo.findByMatchNameAndAuthorOrderBookName(filterMod, filterAuthorNameMod);
            }
            else {
                books = bookRepo.findByMatchNameAndAuthorOrderBookNameAndAuthorName(filterMod, filterAuthorNameMod);
            }
        }*/
        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        model.addAttribute("filterAuthorName", filterAuthorName);
        model.addAttribute("filterGenres", filterGenres);
        // model.addAttribute("orderByBookName", orderByBookName);
        // model.addAttribute("orderByAuthorName", orderByAuthorName);
        return "search";//return this VIEW file name from folder templates
    }

}


