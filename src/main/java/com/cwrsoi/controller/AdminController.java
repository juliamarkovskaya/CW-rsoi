package com.cwrsoi.controller;

import com.cwrsoi.model.BookDtls;
import com.cwrsoi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private BookService bookService;

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

    @GetMapping("/addNewBook")
    public String addNewBook() {
        return "admin/add_book";
    }


}
