package com.cwrsoi.service;

import com.cwrsoi.model.BookDtls;

import java.util.List;

public interface BookService {

    public BookDtls saveBook(BookDtls book);

    //public BookDtls addNewBook(BookDtls book);

    public List<BookDtls> getAllBooks();

    public BookDtls getBookById(Integer idBook);

    public String deleteBook(Integer idBook);

    public BookDtls editBook(BookDtls book, Integer idBook);

}
