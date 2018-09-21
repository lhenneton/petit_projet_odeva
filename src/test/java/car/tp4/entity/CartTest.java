package car.tp4.entity;

import static org.junit.Assert.*;

import org.junit.Test;

public class CartTest {

	@Test
	public void testInitialisationCart() {
		Cart cart = new Cart();
		
		assertNotNull(cart);
		assertNotNull(cart.getBooks());
		assertEquals(0, cart.getBooks().size());
	}
	
	@Test
	public void testAddBook() {
		Cart cart = new Cart();
		Book book = new Book("Claire","Laury",1996,47);
		cart.addBook(book);
		
		assertEquals(1, cart.getBooks().size());
		assertTrue(cart.getBooks().keySet().contains(book));
		assertEquals(1,Integer.parseInt(cart.getBooks().get(book).toString()));
		
		cart.addBook(book);
		assertEquals(1, cart.getBooks().size());
		assertTrue(cart.getBooks().keySet().contains(book));
		assertEquals(2,Integer.parseInt(cart.getBooks().get(book).toString()));
	}

	
	@Test
	public void testRemoveBookWithQtt1() {
		Cart cart = new Cart();
		Book book = new Book("Claire","Laury",1996,47);
		cart.addBook(book);
		cart.removeBook(book);
		
		assertEquals(0, cart.getBooks().size());
		assertFalse(cart.getBooks().keySet().contains(book));
		
	}
	
	@Test
	public void testRemoveBookWithQtt2() {
		Cart cart = new Cart();
		Book book = new Book("Claire","Laury",1996,47);
		cart.addBook(book);
		cart.addBook(book);
		cart.removeBook(book);
		
		assertEquals(1, cart.getBooks().size());
		assertTrue(cart.getBooks().keySet().contains(book));
		assertEquals(1,Integer.parseInt(cart.getBooks().get(book).toString()));
	}
	
	
	@Test
	public void testEmptyCart() {
		Cart cart = new Cart();
		Book book = new Book("Claire","Laury",1996,47);
		cart.addBook(book);
		cart.addBook(book);
		cart.emptyCart();
		
		assertNotNull(cart);
		assertNotNull(cart.getBooks());
		assertEquals(0, cart.getBooks().size());
	}
}
