package com.cwrsoi.controller;

import com.cwrsoi.model.BookDtls;
import com.cwrsoi.repository.BookRepository;
import com.cwrsoi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.print.Book;
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
    public String saveBook(@ModelAttribute("book") BookDtls book) {
        BookDtls bookDtls = bookService.saveBook(book);
        if(bookDtls != null) {
            System.out.println("success");
        } else {
            System.out.println("не записало???");
        }
        return "redirect:/admin/addBook";
    }

    /*@PostMapping("/addNewBook")
    public String addnewbook(@ModelAttribute BookDtls book) {
        BookDtls bookDtls = bookService.addNewBook(book);
        if(bookDtls != null) {
            System.out.println("success");
        } else {
            System.out.println("не записало???");
        }
        return "redirect:admin/books";
    }*/

    @PostMapping("/save")
    public String save(BookDtls book) {
        bookRepo.save(book);

        return "redirect:/books";
    }
}
