package com.cwrsoi.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
//@Table(name = "book_dtls")
public class BookDtls {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBook;

    private String name;

    private String author;

    private Double price;

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public BookDtls(String name, String author, Double price) {
        this.name = name;
        this.author = author;
        this.price = price;
    }

    public BookDtls() {
    }

    @Override
    public String toString() {
        return "BookDtls{" +
                "idBook=" + idBook +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
