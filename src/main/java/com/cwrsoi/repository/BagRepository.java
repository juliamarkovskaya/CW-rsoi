package com.cwrsoi.repository;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.BookDtls;
import com.cwrsoi.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BagRepository extends JpaRepository<Bag, Integer> {

     public List<Bag> findByUser(UserDtls user);

    public List<Bag> findByUserId(Integer id);

    public Bag findByBookAndUser(BookDtls book, UserDtls user);

    //public Bag findByUserId(UserDtls id);

}
