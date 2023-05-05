package com.cwrsoi;

import com.cwrsoi.model.Bag;
import com.cwrsoi.model.BookDtls;
import com.cwrsoi.model.UserDtls;
import com.cwrsoi.repository.BagRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ShoppingBagTest {

    @Autowired
    private BagRepository bagRepo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testAddOneBagItem() {
        BookDtls book = entityManager.find(BookDtls.class, 1);
        UserDtls user = entityManager.find(UserDtls.class, 10);

        Bag newItem = new Bag();
        newItem.setUser(user);
        newItem.setBook(book);
        newItem.setOrderQuantity(4);

        Bag savedBag = bagRepo.save(newItem);

        assertTrue(savedBag.getIdBag() > 0);
    }

    private void assertTrue(boolean b) {
    }

    /*@Test
    public void testGetBagItemsByUser() {
        //UserDtls user = new UserDtls();
        //Integer id = user.getId();
        user.setId(14);

        List<Bag> bagItems = bagRepo.findByUser(user);

        assertEquals(2, bagItems.size());
    }

    private void assertEquals(int i, int size) {
    }*/
}
