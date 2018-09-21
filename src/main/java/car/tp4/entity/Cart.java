package car.tp4.entity;

import java.util.HashMap;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Class define a cart
 * 
 * @author Claire Auvray & Laury Henneton
 */
@Entity
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * List of book associated with their quantity
     */
    private HashMap<Book,Integer> books;


    public Cart() {
        this.books = new HashMap<Book,Integer>();
    }

 
    public long getId() { return id; }

	public HashMap<Book, Integer> getBooks() {
		return books;
	}

	public void setBooks(HashMap<Book, Integer> books) {
		this.books = books;
	}

	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Add a book in the cart and update the stock of this book. Update the
	 * quantity of this book in the cart
	 * 
	 * @param book
	 *            the book to add in the cart
	 */
	public void addBook(Book book) {
		
		Integer quantite = books.get(book);
		if (quantite == null) {
			quantite = 1;
		} else {
			quantite += 1;
		}
		books.put(book, quantite);
	}
	
	/**
	 * Remove a book of the cart if only one left, else update the quantity of
	 * this book in the cart. Update the stock of this book
	 * 
	 * @param book
	 *            the book to remove and update quantity in the cart
	 */
	public void removeBook(Book book) {
		for (Book b : books.keySet()) {
			if (b.equals(book)) {
				Integer quantite = books.get(b);
				if (quantite == 1) {
					books.remove(b);
				} else {
					quantite -= 1;
					books.put(b, quantite);
				}
				break;
			}
		}
	}
	
	/**
	 * Empty the cart
	 */
	public void emptyCart() {
		books = new HashMap<Book, Integer>();
	}
	
	
	/** Update the stock of all books
	 * @param bookBean
	 */
	public void validateCart(BookBean bookBean) {
		for (Book b : books.keySet()) {
				Integer quantite = books.get(b);
				int quantityToUpdate=b.getStock()-quantite;
				bookBean.updateStock(b.getId(),quantityToUpdate);
				b.setStock(quantityToUpdate);
		}
	}
}
