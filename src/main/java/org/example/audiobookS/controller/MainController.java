package org.example.audiobookS.controller;

import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.Book;
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
import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

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


    @GetMapping("/main")
    public String main(
            @RequestParam(required = false, defaultValue = "") String filter,
            @RequestParam(required = false, defaultValue = "") String id,
            Model model
    ) {
        Iterable<Book> books;                   //At Model we put values and send to view
        if (filter != null && !filter.isEmpty()) {
            books = bookRepo.findByNameContaining(filter);//do request
        } else if (id != null && !id.isEmpty()) {

            books = bookRepo.findAllById(Collections.singleton(Long.parseLong(id)));//do request
        } else {
            books = bookRepo.findAll();//do show all list of books
        }

        model.addAttribute("books", books);//put value in the Model
        model.addAttribute("filter", filter);//put value in the  Model
        return "main";//return this VIEW file name from folder templates
    }

    @PostMapping("/main")
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
    }
}

// System.out.println("bookRepo =--------------------------------------------- " + bookRepo.count());
// Optional<Book> books1 = bookRepo.findById(57L);
//  Iterable<Long> iterableLong = Collections.singleton(15l);
//  Iterable<Book> books1 = bookRepo.findAllById(iterableLong);
//System.out.println("bookbyID =--------------------------------------------- " + books1);