package com.cwrsoi.service;

import com.cwrsoi.model.BookDtls;

import java.util.List;

public interface BookService {

    public BookDtls saveBook(BookDtls book);

    public List<BookDtls> getAllBooks();

    public BookDtls getBookById(Integer idBook);

    public BookDtls editBook(BookDtls book);

    public String deleteBook(Integer idBook);

}
