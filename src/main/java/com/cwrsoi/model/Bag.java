package com.cwrsoi.model;

import javax.persistence.*;

@Entity
@Table(name = "bag")
public class Bag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idBag;

    @ManyToOne
    @JoinColumn(name = "id")
    private UserDtls user;

    @OneToOne
    @JoinColumn(name = "id_book")
    private BookDtls book;

}
