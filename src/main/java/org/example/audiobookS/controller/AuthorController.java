package org.example.audiobookS.controller;


import org.example.audiobookS.domain.Author;
import org.example.audiobookS.domain.Book;
import org.example.audiobookS.domain.Role;
import org.example.audiobookS.domain.User;
import org.example.audiobookS.repos.AuthorRepo;
import org.example.audiobookS.repos.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintViolationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorRepo authorRepo;

    @Autowired//this is annotation for injection dependencies in field
    private BookRepo bookRepo;

    @GetMapping
    public String authorList(
            @RequestParam(required = false, defaultValue = "") String filterAuthorName,
            @RequestParam(required = false) String orderByAuthorName,
            Model model){
        List<Author> authors;
        String reOrderByAuthorName;
       if (orderByAuthorName != null && !orderByAuthorName.isEmpty()){
           if (orderByAuthorName.equals("checked")){
               authors = authorRepo.findByAuthornameContainingOrderByAuthorname(filterAuthorName);
               reOrderByAuthorName = "unchecked";
           } else {
               authors = authorRepo.findByAuthornameContainingOrderByAuthornameDesc(filterAuthorName);
               reOrderByAuthorName = "checked";
           }
       }
       else {
           authors = authorRepo.findByAuthornameContaining(filterAuthorName);
           reOrderByAuthorName = "unchecked";
       }
        model.addAttribute("authors",authors);
        model.addAttribute("filterAuthorName", filterAuthorName);
        model.addAttribute("orderByAuthorName", orderByAuthorName);
        model.addAttribute("reOrderByAuthorName", reOrderByAuthorName);
        return "authorList";
    }

    @Value("${upload.path}")
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
            @AuthenticationPrincipal User owner,
            @RequestParam String name,
            @RequestParam String authorId,
            Map<String, Object> model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        Optional<Author> authorById = authorRepo.findById(Long.parseLong(authorId));
        Book book = new Book(name, owner, authorById.get());
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
        Iterable<Book> books = authorById.get().getBooks();
        model.put("books", books);
        model.put("authorId", authorId);
        return "bookList";
       // return "redirect:/author";
    }

    @GetMapping("/add")
    public  String authorSave(
            @RequestParam String authorname, Model model
    ){
       Author authorsOld = authorRepo.findByAuthorname(authorname);
       if(authorsOld  == null){
           Author author = new Author(authorname);
           authorRepo.save(author);
           model.addAttribute("authorname","Name added!");
       } else {
           model.addAttribute("authorname","This name already exists!");
       }
        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authors", authors);
        return "authorList";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String genericErrorPage() {
        return "genericErrorView";
    }
    @ExceptionHandler(NoSuchElementException.class)
    public String genericNoSuchElementException() {
        return "genericNoSuchElementException";
    }

    @ExceptionHandler(javax.persistence.RollbackException.class)
        public  String genericRollbackException(){
        return "genericRollbackException";
    }

}
