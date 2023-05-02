package com.cwrsoi.controller;

import com.cwrsoi.model.BookDtls;
import com.cwrsoi.repository.BookRepository;
import com.cwrsoi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.awt.print.Book;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookRepository bookRepo;

    @GetMapping("/")
    public String home() {
        return "admin/home";
    }

    @GetMapping("/books")
    public String getAllBooks(Model model) {
        List<BookDtls> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "admin/books";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {

        BookDtls book = new BookDtls();
        model.addAttribute("book", book);
        return "admin/add_book";
    }

    @PostMapping("/saveNew")
    public String saveBook(@ModelAttribute("book") BookDtls book, HttpSession session) {
        BookDtls newbook = bookService.saveBook(book);
        if(newbook != null) {
            session.setAttribute("msg", "Book added successfully");
            System.out.println("success");
        } else {
            session.setAttribute("msg", "Something wrong");
        }
        return "redirect:/admin/addBook";
    }

    @GetMapping("/updateBook")


    /*@GetMapping("/updateBook")
    public String loadUpdateBook() {
        return "admin/update_book";
    }

    @PostMapping("/bookInfoUpdate")
    public String updateBook(Model model, Authentication authentication,
                             @RequestParam String name, @RequestParam String author,
                             @RequestParam Double price, Integer idBook, HttpSession session) throws IOException {
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        BookDtls oldBook = bookRepo.findByIdBook(idBook);

        oldBook.setName(name);
        oldBook.setAuthor(author);
        oldBook.setPrice(price);

        bookRepo.save(oldBook);
        model.addAttribute("oldBook", oldBook);
        session.setAttribute("msg", "Изменения сохранены.");
        return "redirect:/admin/updateBook";
    }*/



    @PostMapping("/save")
    public String save(BookDtls book) {
        bookRepo.save(book);

        return "redirect:/books";
    }
}
