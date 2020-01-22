package org.example.audiobookS.controller;

import org.example.audiobookS.domain.Book;
import org.example.audiobookS.domain.User;
import org.example.audiobookS.repos.AuthorRepo;
import org.example.audiobookS.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller//this id annotation for controller
public class MainController {//controller fo greeting
    @Autowired//this is annotation for injection dependencies in field
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;

    @Value("${upload.path}")// @Value позволяет внедрять простые значения таких типов, как int, boolean и String.
    private String uploadPath;

    @GetMapping("/")
    //The @GetMapping annotation ensures that HTTP GET requests to /greeting are mapped to the greeting() method.
    public String greeting(Map<String, Object> model) {
        return "greeting";//return this VIEW file name from folder templates
    }

    @ExceptionHandler(NumberFormatException.class)
    public String genericErrorPage() {
        return "genericErrorView";
    }

    @ExceptionHandler(NoSuchElementException.class)
    public String genericNoSuchElementException() {
        return "genericNoSuchElementException";
    }


    @GetMapping("/main")
    public String main(
            @AuthenticationPrincipal User owner,
            @RequestParam(required = false, defaultValue = "") String filterAuthorName,
            @RequestParam(required = false, defaultValue = "") String filterAuthorId,
            @RequestParam(required = false, defaultValue = "") String filter,
            Model model
    ) {
        Iterable<Book> books;
        books = bookRepo.findByAuthorAuthornameContainingAndNameContainingOrderByNameAscAuthorAuthornameAsc(filterAuthorName, filter);

        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        model.addAttribute("filterAuthorName", filterAuthorName);

        return "main";//return this VIEW file name from folder templates
    }

    @GetMapping("/user-owner/{user}")
    public String userBooks(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model
    ) {

        Set<Book> books = user.getBooks();
        model.addAttribute("books", books);
        return "userBooks";
    }

}
