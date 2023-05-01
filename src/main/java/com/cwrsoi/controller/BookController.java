package com.cwrsoi.controller;

import com.cwrsoi.model.BookDtls;
import com.cwrsoi.repository.BookRepository;
import com.cwrsoi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping({"/admin/books", "/admin/add_book"})
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BookService bookService;

    /*@RequestMapping(path = {"admin/books"})
    public String getAllBooks(Model model) {
        List<BookDtls> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "admin/books";
    }*/

    /*@GetMapping("/books")
    public String getAllBooks(Model model) {
        List<BookDtls> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "admin/books";
    }*/

    @PostMapping("/save")
    public String save(BookDtls book) {
        bookRepo.save(book);

        return "redirect:/books";
    }

    @GetMapping(value = "/addNewBook")
    public String addBook() {
        return "admin/add_book";
    }

    /*@GetMapping("/delete")
    public String deleteBook(BookDtls book) {
        bookRepo.delete(book);

        return "redirect:/books";
    }*/

}
