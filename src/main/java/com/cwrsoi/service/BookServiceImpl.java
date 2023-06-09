package com.cwrsoi.service;

import com.cwrsoi.model.BookDtls;
import com.cwrsoi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepo;

    @Override
    public BookDtls saveBook(BookDtls book) {
        return bookRepo.save(book);
    }

    @Override
    public List<BookDtls> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public BookDtls getBookById(Integer idBook) {
        return bookRepo.findById(idBook).get();
    }

    @Override
    public BookDtls editBook(BookDtls book) {
        BookDtls bookDtls = bookRepo.findByIdBook(book.getIdBook());

        bookDtls.setName(book.getName());
        bookDtls.setAuthor(book.getAuthor());
        bookDtls.setPrice(book.getPrice());
        return bookRepo.save(book);
    }

    @Override
    public String deleteBook(Integer idBook) {
        BookDtls book = bookRepo.getById(idBook);

        if(book!=null) {
            bookRepo.delete(book);
            return "Book Delete Successfully";
        }
        return "Something wrong";
    }

}
