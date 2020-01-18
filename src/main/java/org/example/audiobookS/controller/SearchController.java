package org.example.audiobookS.controller;

import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.Book;
import org.example.audiobookS.domain.User;
import org.example.audiobookS.repos.AuthorRepo;
import org.example.audiobookS.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/search")
public class SearchController {
    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private AuthorRepo authorRepo;


    @GetMapping
    public String search(
            @AuthenticationPrincipal User owner,
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") String filterAuthorName,
            @RequestParam(required = false) String orderByBookName,
            @RequestParam(required = false) String orderByAuthorName,
            Model model
    ) {
        String filterMod = "%"+filter +"%";
        String filterAuthorNameMod = "%"+filterAuthorName+"%";
        List<Book> books;

        books = bookRepo.findByAuthorAuthornameContainingAndNameContainingOrderByNameAscAuthorAuthornameAsc(filterAuthorName, filter);
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
        model.addAttribute("orderByBookName", orderByBookName);
        model.addAttribute("orderByAuthorName", orderByAuthorName);
        return "search";//return this VIEW file name from folder templates
    }

}


