package car.tp4.entity;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * BookBean. Handles all the query for entity manager of a book
 * 
 * @author Claire Auvray & Laury Henneton
 *
 */
@Stateless
@Local
public class BookBean {

	@PersistenceContext(unitName = "book-pu")
	private EntityManager entityManager;

	/**
	 * init some books
	 * 
	 */
	public void init() {
		Book book1 = new Book("J. R. R. Tolkien", "The Lord of the Rings", 2004, 6);
		Book book2 = new Book("Honor de Balzac", "Le père Goriot", 1835, 3);
		addBook(book1);
		addBook(book2);
	}

	/**
	 * @param id
	 *            the book's id
	 * @return the book associated to the id
	 */
	public Book getBookById(int id) {
		return entityManager.find(Book.class, id);
	}

	/**
	 * Add a book in entity manager
	 * 
	 * @param book
	 *            the book to add
	 */
	public void addBook(Book book) {
		entityManager.persist(book);
	}

	/**
	 * Get all books
	 * 
	 * @return all the books in entity manager
	 */
	public List<Book> getAllBooks() {
		Query query = entityManager.createQuery("SELECT m from Book as m");
		return query.getResultList();
	}

	/**
	 * Delete a book
	 * 
	 * @param id
	 *            the book's id to remove of entity manager
	 */
	public void removeBook(int id) {
		entityManager.remove(entityManager.find(Book.class, id));
	}

	/**
	 * Return all the existing author in entity manager
	 * 
	 * @return all the existing authors
	 */
	public List<String> getAllAuthors() {
		Query query = entityManager.createQuery("SELECT DISTINCT m.author from Book as m");
		return query.getResultList();
	}

	/**
	 * @return all book order by date
	 */
	public List<Book> getAllBooksOrderByDate() {
		Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.year");
		return query.getResultList();
	}

	/**
	 * @return all books order by author
	 */
	public List<Book> getAllBooksOrderByAuthor() {
		Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.author");
		return query.getResultList();
	}

	/**
	 * @return all books order by title
	 */
	public List<Book> getAllBooksOrderByTitle() {
		Query query = entityManager.createQuery("SELECT m from Book as m ORDER BY m.title");
		return query.getResultList();
	}

	/**
	 * @param author
	 *            the book's author
	 * @return all books written by the author :author
	 */
	public List<Book> getAllBooksByAuthor(String author) {
		Query query = entityManager.createQuery("SELECT m from Book as m WHERE m.author='" + author + "'");
		return query.getResultList();
	}

	/**
	 * @param title
	 *            the book's substring title
	 * @return all the books witch contains the substring
	 */
	public List<Book> getAllBooksByTitle(String title) {
		Query query = entityManager.createQuery("SELECT m from Book as m WHERE m.title LIKE '%" + title + "%'");
		System.out.println(query.toString() + " " + query.getResultList().size());
		return query.getResultList();
	}

	/** Update the quantity of the book when validate order
	 * @param id book's id
	 * @param quantity quantity to set
	 */
	public void updateStock(int id, int quantity) {
		Query query = entityManager.createQuery(
				"UPDATE Book AS b SET b.stock =" + quantity +" WHERE b.id =" + id);
		query.executeUpdate();
	}
}