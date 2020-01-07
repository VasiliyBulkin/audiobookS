package org.example.audiobookS.controller;


import org.example.audiobookS.domain.Book;
import org.example.audiobookS.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/book")
public class BookController {


    @Autowired//this is annotation for injection dependencies in field
    private BookRepo bookRepo;

    @GetMapping
    public String bookList(Model model){
        Iterable<Book> books;
        books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "bookList";
    }
}
