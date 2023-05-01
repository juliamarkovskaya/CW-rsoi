package com.cwrsoi.repository;

import com.cwrsoi.model.BookDtls;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookDtls, Integer> {

    public boolean existsById(Integer idBook);
    //public BookDtls delete(Integer idBook);
}
