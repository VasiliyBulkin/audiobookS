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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired//this is annotation for injection dependencies in field
    private BookRepo bookRepo;

    @GetMapping
    public String authorList(Model model){
        model.addAttribute("authors",authorRepo.findAll());
        return "authorList";
    }

    @Value("${upload.path}")// @Value позволяет внедрять простые значения таких типов, как int, boolean и String.
    private String uploadPath;

    @GetMapping("{authorid}")
    public  String bookAddForm(@PathVariable String authorid, Model model){
        Iterable <Author> authors;
        authors = authorRepo.findAllById(Collections.singleton(Long.parseLong(authorid)));
        System.out.println("author = " + authors);
        model.addAttribute("authors", authors);
        return "bookAdd";
    }

    @PostMapping("{authorid}")
    public String add(
            @AuthenticationPrincipal User owner,//???
            @RequestParam String name,
            @RequestParam String authorid,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Iterable <Author> authors = authorRepo.findAllById(Collections.singleton(Long.parseLong(authorid)));
        List<Author> authorList = new LinkedList<>();
        authors.forEach(authorList::add);
        int i = authorList.size();
        Author author = authorList.get(i-1);

        Book book = new Book(name, owner, author);

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
        return "redirect:/book";
    }

    @PostMapping
    public  String authorSave(
            @RequestParam String authorname, Model model
    ){
        Author author = new Author(authorname);
        authorRepo.save(author);
        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);
        return "redirect:/author";
    }
}
