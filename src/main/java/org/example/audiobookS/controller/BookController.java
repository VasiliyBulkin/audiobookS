package org.example.audiobookS.controller;


import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.Book;
import org.example.audiobookS.repos.AuthorRepo;
import org.example.audiobookS.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;
import java.util.Set;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/book")
public class BookController {

    @Autowired
    private AuthorRepo authorRepo;

    @Autowired//this is annotation for injection dependencies in field
    private BookRepo bookRepo;

    @GetMapping("{authorname}")
    public String bookList(
            @PathVariable String authorname,
            Model model){
        Author author;
        author = authorRepo.findByAuthorname(authorname);
        Set<Book> books;
        books = author.getBooks();
        model.addAttribute("books", books);
        return "bookList";
    }
}
