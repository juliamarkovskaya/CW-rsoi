package com.cwrsoi.model;

import javax.persistence.*;

@Entity
@Table(name = "bag")
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBag;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserDtls user;

    @ManyToOne
    @JoinColumn(name = "id_book")
    private BookDtls book;

    private int orderQuantity;

    public Integer getIdBag() {
        return idBag;
    }

    public void setIdBag(Integer idBag) {
        this.idBag = idBag;
    }

    public UserDtls getUser() {
        return user;
    }

    public void setUser(UserDtls user) {
        this.user = user;
    }

    public BookDtls getBook() {
        return book;
    }

    public void setBook(BookDtls book) {
        this.book = book;
    }

    public int getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(int orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    @Transient
    public double getSubtotal() {
        return this.book.getPrice() * orderQuantity;
    }
}
