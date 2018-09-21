package car.tp4.entity;

import static org.junit.Assert.*;

import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class BookBeanTest {

	@Deployment
	public static WebArchive createDeployment() {
		return ShrinkWrap.create(WebArchive.class).addAsResource("META-INF/persistence.xml")
				.addPackage(Book.class.getPackage()).addAsWebInfResource(EmptyAsset.INSTANCE, "WEB-INF/beans.xml");
	}

	@PersistenceContext(unitName = "book-pu")
	EntityManager em;

	@EJB
	private BookBean bookBean;

	
	@Before
	public void clearData() {
		List<Book> books = bookBean.getAllBooks();
		for (Book b : books) {
			bookBean.removeBook(b.getId());
		}
	}
	
	@Test
	public void addBookTest() {
		Book b = new Book("Laury", "Claire", 1996, 47);
		bookBean.addBook(b);
		Book bpersist = em.find(Book.class, b.getId());
		assertEquals(bpersist.getAuthor(), b.getAuthor());
		assertEquals(bpersist.getTitle(), b.getTitle());
		assertEquals(bpersist.getYear(), b.getYear());
		assertEquals(bpersist.getStock(), b.getStock());
	}

	@Test
	public void removeBookTest() {
		Book b = new Book("Laury", "Claire", 1996, 47);
		bookBean.addBook(b);
		bookBean.removeBook(b.getId());
		Book bpersist = em.find(Book.class, b.getId());
		assertEquals(bpersist, null);
	}

	@Test
	public void getAllBookTest() {
		Book b = new Book("Laury", "Claire", 1996, 47);
		Book b2 = new Book("Henneton", "Auvray", 2018, 74);
		bookBean.addBook(b);
		bookBean.addBook(b2);
		List<Book> books = bookBean.getAllBooks();
		Book bpersist = em.find(Book.class, b.getId());
		Book b2persist = em.find(Book.class, b2.getId());
		assertTrue(books.contains(bpersist));
		assertTrue(books.contains(b2persist));
	}

	@Test
	public void getAllAuthorsTest() {
		Book b = new Book("Laury", "Claire", 1996, 47);
		Book b2 = new Book("Henneton", "Auvray", 2018, 74);
		bookBean.addBook(b);
		bookBean.addBook(b2);
		List<String> authors = bookBean.getAllAuthors();
		assertTrue(authors.contains("Laury"));
		assertTrue(authors.contains("Henneton"));
	}

	@Test
	public void getAllBooksOrderByDateTest() {
		Book b = new Book("Laury", "Claire", 2018, 47);
		Book b2 = new Book("Henneton", "Auvray", 1996, 74);
		bookBean.addBook(b);
		bookBean.addBook(b2);
		List<Book> books = bookBean.getAllBooksOrderByDate();
		assertEquals(books.get(0), b2);
		assertEquals(books.get(1), b);
	}
	
	@Test
	public void getAllBooksOrderByAuthorTest() {
		Book b = new Book("Laury", "Claire", 2018, 47);
		Book b2 = new Book("Henneton", "Auvray", 1996, 74);
		bookBean.addBook(b);
		bookBean.addBook(b2);
		List<Book> books = bookBean.getAllBooksOrderByAuthor();
		assertEquals(books.get(0), b2);
		assertEquals(books.get(1), b);
	}
		
	@Test
	public void getAllBooksOrderByTitleTest() {
		Book b = new Book("Laury", "Claire", 2018, 47);
		Book b2 = new Book("Henneton", "Auvray", 1996, 74);
		bookBean.addBook(b);
		bookBean.addBook(b2);
		List<Book> books = bookBean.getAllBooksOrderByTitle();
		assertEquals(books.get(0), b2);
		assertEquals(books.get(1), b);
	}
	
	@Test
	public void getAllBooksByAuthorTest() {
		Book b = new Book("Laury", "Claire", 2018, 47);
		Book b2 = new Book("Henneton", "Auvray", 1996, 74);
		Book b3 = new Book("Henneton", "Apprendre a jouer de la clarinnette", 2010, 4);
		bookBean.addBook(b);
		bookBean.addBook(b2);
		bookBean.addBook(b3);
		List<Book> books = bookBean.getAllBooksByAuthor("Henneton");
		assertEquals(books.get(0), b2);
		assertEquals(books.get(1), b3);
		assertEquals(books.size(), 2);
	}

	
	@Test
	public void getAllBooksByTitleTest() {
		Book b = new Book("Laury", "claire", 2018, 47);
		Book b2 = new Book("Henneton", "Auvray", 1996, 74);
		Book b3 = new Book("Henneton", "Apprendre a jouer de la clarinnette", 2010, 4);
		bookBean.addBook(b);
		bookBean.addBook(b2);
		bookBean.addBook(b3);
		List<Book> books = bookBean.getAllBooksByTitle("cla");
		assertEquals(books.get(0), b);
		assertEquals(books.get(1), b3);
		assertEquals(books.size(), 2);
	}
	

	
	@Test
	public void updateStockTest() {
		Book b = new Book("Laury", "claire", 2018, 47);
		bookBean.addBook(b);
		bookBean.updateStock(b.getId(),4);
		Book bpersist = em.find(Book.class, b.getId());
		assertEquals(bpersist.getStock(),4);
		
	}

}
