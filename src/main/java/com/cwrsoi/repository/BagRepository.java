package com.cwrsoi.repository;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.BookDtls;
import com.cwrsoi.model.UserDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface BagRepository extends JpaRepository<Bag, Integer> {

     public List<Bag> findByUser(UserDtls user);

     //public List<Bag> findByUserId(Integer id);

     //public Bag findByIdBookAndUser(Integer idBook, UserDtls user);

     public Bag findByBookAndUser(BookDtls book, UserDtls user);

     public Bag getAllByUser(UserDtls user);

     //@Transactional
     //public Bag deleteAllByUser(UserDtls user);

     //public Bag findByUserId(UserDtls id);

}
