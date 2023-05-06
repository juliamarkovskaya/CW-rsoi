package com.cwrsoi.service;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.BookDtls;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.BagRepository;
import com.cwrsoi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BagServiceImpl implements BagService{

    @Autowired
    private BagRepository bagRepo;

    @Autowired
    private BookRepository bookRepo;
    /*public List<Bag> listBag(UserDtls id) {
        return bagRepo.findByUserId(id);
    }*/

    @Override
    public List<Bag> getBagItemsByUser(UserDtls user) {
        return bagRepo.findByUser(user);
    }

    @Override
    public Bag addItemToBag(Integer idBook, Integer orderQuantity, UserDtls user) {
        Integer addedQuantity = orderQuantity;

        BookDtls book = bookRepo.findById(idBook).get();

        Bag bagItem = bagRepo.findByBookAndUser(book, user);

        if(bagItem != null) {
            addedQuantity = bagItem.getOrderQuantity() + orderQuantity;
            bagItem.setOrderQuantity(addedQuantity);
        } else {
            bagItem = new Bag();
            bagItem.setOrderQuantity(orderQuantity);
            bagItem.setUser(user);
            bagItem.setBook(book);
        }

        return bagRepo.save(bagItem);
        //return addedQuantity;
    }

    @Override
    public String removeItem(Integer idBag) {
        Bag bagItem = bagRepo.getById(idBag);
        //BookDtls book = bookRepo.findById(idBook).get();
        //Bag bagItem = bagRepo.findByBookAndUser(book, user);
            bagRepo.delete(bagItem);
            return "Book Delete Successfully";
    }

    /*@Override
    public void removeItem(Integer idBook) {
        Bag bag = bagRepo.findByIdBookAndUser(idBook, user.ge);
    }*/

    /*@Override
    public Bag getBagByUserId(UserDtls id) {
        return bagRepo.findByUser(id);
    }*/

    /*@Override
    public Bag getBagById(Integer id) {
        return bagRepo.findByUserId(id).get();
    }*/
}
