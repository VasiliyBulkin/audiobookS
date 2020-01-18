package org.example.audiobookS.controller;


import org.example.audiobookS.domain.*;
import org.example.audiobookS.repos.AuthorRepo;
import org.example.audiobookS.repos.BookRepo;
import org.example.audiobookS.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
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

    @Autowired
    private UserRepo userRepo;

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
       // String filterMod = "%"+filterAuthorName +"%";
       // authors = authorRepo.findByBooksNameLike(filterMod);

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
        Author author = authorById.get();
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

        owner.getBooks().add(book);
        //userRepo.save(owner);
        bookRepo.save(book);
        String authorName = author.getAuthorname();
        return "redirect:/book/" + authorName;

      //  Set<Book> books= author.getBooks();
      //  books.add(book);
       // Genre myGenre = new Genre(genre);
       // myGenre.addBook(book);
       // bookRepo.deleteById();
       // String[] mSomeGenres = { "detective", "horror", "genre" };
      //  Set<Genre> genres = new HashSet<>();
      //  for (String g : mSomeGenres){
       //     genres.add(new Genre());
      //  }
   /*
        Set<Genre> genres = new HashSet<>();
        for (String g : mSomeGenres){
            genres.add(new Genre(g));
        }
//user_id bigint,
        for(Genre myGenre: genres){
            myGenre.addBook(book);
        }
*/
       // book.setGenres(genres);

       // Iterable<Book> books = authorById.get().getBooks();
        //model.put("books", books);
       // model.put("authorId", authorId);

        //return "bookList";
       // return "redirect:/author";
    }

    @GetMapping("delete/{authorid}")
    public String deleteAuthor(
            @PathVariable String authorid,
            Model model
    ){
        Optional<Author> authorById = authorRepo.findById(Long.parseLong(authorid));
        Author author = authorById.get();

        if(author.getBooks().isEmpty()){
            model.addAttribute("messageSuccess","The "+ author.getAuthorname()+ " has been deleted!");
            authorRepo.deleteById(Long.parseLong(authorid));
        }else {
            model.addAttribute("messageDenied","The "+ author.getAuthorname()+ " has books!");
        }
            List<Author> authors = authorRepo.findAll();
            model.addAttribute("authors",authors);
            model.addAttribute("author", author);
            return "authorList";
    }

    @GetMapping("/add")
    public  String authorSave(
            @RequestParam String authorname,
            Model model
    ){
       Author authorsOld = authorRepo.findByAuthorname(authorname);
       if(authorsOld  == null){
           Author author = new Author(authorname);
           authorRepo.save(author);
           model.addAttribute("messageOK","Name added!");
       } else {
          model.addAttribute("messageNotOk","This name already exists!");
       }
        Iterable<Author> authors = authorRepo.findAll();
        model.addAttribute("authorname", authorname);
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
