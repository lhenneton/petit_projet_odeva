package car.tp4.entity;

import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * CartBean. Handles all the query for entity manager of a cart
 * 
 * @author Claire Auvray & Laury Henneton
 */
@Stateful
@Local
public class CartBean {

    @PersistenceContext(unitName = "cart-pu")
    private EntityManager entityManager;

    /**
     * Add a cart
     * @param cart the cart to add
     */
    public void addCart(Cart cart) {
        entityManager.persist(cart);
    }

    /**
     * get all carts validated
     * @return list of carts
     */
    public List<Book> getAllCart() {
        Query query = entityManager.createQuery("SELECT DISTINCT(c) FROM Cart AS c");
        return query.getResultList();
    }
}
