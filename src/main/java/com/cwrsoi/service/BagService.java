package com.cwrsoi.service;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.BookDtls;
import com.cwrsoi.model.UserDtls;

import java.util.List;

public interface BagService {

    //public List<Bag> getAllBagItems();

    //public Bag getBagByUserId(UserDtls id);

    public List<Bag> getBagItemsByUser(UserDtls user);

    public Bag addItemToBag(Integer idBook, Integer orderQuantity, UserDtls user);

    public String removeItem(Integer idBag);
}
