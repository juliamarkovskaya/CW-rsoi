package com.cwrsoi.service;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.BookDtls;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.BagRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface BagService {

    //public List<Bag> getAllBagItems();

    //public Bag getBagByUserId(UserDtls id);

    public List<Bag> getBagItemsByUser(UserDtls user);

    //void removeItem(Integer idBook);
}
