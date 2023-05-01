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

    /*@Override
    public BookDtls saveBook(BookDtls book) {
        return bookRepo.save(book);
    }*/

    /*@Override
    public BookDtls addNewBook(BookDtls book) {
        //book.setIdBook(book.getIdBook());
        //BookDtls book = bookRepo.findById(idBook).get();
        return bookRepo.save(book);
    }*/

    /*@Override
    public BookDtls addNewBook(BookDtls book) {
        return bookRepo.save(book);
    }*/

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
    public String deleteBook(Integer idBook) {
        BookDtls book = bookRepo.findById(idBook).get();

        if(book!=null) {
            bookRepo.delete(book);
            return "Book Delete Successfully";
        }
        return "Something wrong";
    }

    @Override
    public BookDtls editBook(BookDtls book, Integer idBook) {
        BookDtls oldBook = bookRepo.findById(idBook).get();

        oldBook.setName(book.getName());
        oldBook.setAuthor(book.getAuthor());
        oldBook.setPrice(book.getPrice());

        return bookRepo.save(oldBook);
    }
}
