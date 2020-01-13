package org.example.audiobookS.controller;

import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.Book;
import org.example.audiobookS.domain.Role;
import org.example.audiobookS.domain.User;
import org.example.audiobookS.repos.AuthorRepo;
import org.example.audiobookS.repos.BookRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
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
            @RequestParam(required = false, defaultValue = "") String id,
            Model model
    ) {
        Iterable<Book> books;
        if (filter != null && !filter.isEmpty()) {
            books = bookRepo.findByNameContaining(filter);
        } else if (id != null && !id.isEmpty()) {
            books = bookRepo.findAllById(Collections.singleton(Long.parseLong(id)));
        } else if (filterAuthorId != null && !filterAuthorId.isEmpty()) {
            Optional<Author> authorById = authorRepo.findById(Long.parseLong(filterAuthorId));
            books = bookRepo.findByAuthorLike(authorById.get());
        } else if (filterAuthorName != null && !filterAuthorName.isEmpty()) {
            Author authors = authorRepo.findByAuthorname(filterAuthorName);
            if(authors != null ){
                books = bookRepo.findByAuthorLike(authors);
            }else {
                books = bookRepo.findAll();
            }
        }
        else {
            books = bookRepo.findAll();
        }


        model.addAttribute("id", id);
        model.addAttribute("books", books);
        model.addAttribute("filter", filter);
        model.addAttribute("filterAuthorId", filterAuthorId);
        model.addAttribute("filterAuthorName", filterAuthorName);
        Set <Role> roles = owner.getRoles();
        if(roles.contains(Role.ADMIN)){
          model.addAttribute("showLink", "showLink");
        }

        return "main";//return this VIEW file name from folder templates
    }


  /*  @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User owner,//???
            @RequestParam String name, Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Book book = new Book(name, owner);

        if (file != null && !file.getOriginalFilename().isEmpty()) {//проверяем есть ли файл не равный null
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {//если директория не существует - создаем ее
                uploadDir.mkdir();
            }
            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();
            file.transferTo(new File(uploadPath + "/" + resultFilename));
            book.setFilename(resultFilename);
        }
        bookRepo.save(book);
        Iterable<Book> books = bookRepo.findAll();
        model.put("books", books);
        return "main";
    }*/
}
