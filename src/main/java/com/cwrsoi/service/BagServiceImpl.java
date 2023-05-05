package com.cwrsoi.service;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.BagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BagServiceImpl implements BagService{

    @Autowired
    private BagRepository bagRepo;

    /*public List<Bag> listBag(UserDtls id) {
        return bagRepo.findByUserId(id);
    }*/

    @Override
    public List<Bag> getBagItemsByUser(UserDtls user) {
        return bagRepo.findByUser(user);
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
