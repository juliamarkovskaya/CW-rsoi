/*package com.cwrsoi.controller;

import com.cwrsoi.model.BookDtls;
import com.cwrsoi.repository.BookRepository;
import com.cwrsoi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller @RequestMapping({"/admin/books", "/admin/add_book"})
@RequiredArgsConstructor
public class BookController {

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private BookService bookService;


    /*@PostMapping("/save")
    public String save(BookDtls book) {
        bookRepo.save(book);

        return "redirect:/books";
    }*/


    /*@GetMapping("/delete")
    public String deleteBook(BookDtls book) {
        bookRepo.delete(book);

        return "redirect:/books";
    }*/

//}
