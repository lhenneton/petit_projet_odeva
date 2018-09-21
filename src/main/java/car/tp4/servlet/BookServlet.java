package car.tp4.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import car.tp4.entity.Book;
import car.tp4.entity.BookBean;
import car.tp4.entity.Cart;

/**
 * Servlet that handles all between jsp and beans
 * 
 * @author Claire Auvray & Laury Henneton
 *
 */
@WebServlet("/books")
public class BookServlet extends HttpServlet {

	private static final long serialVersionUID = 7810114505545166203L;
	private static final String URL_BOOK_JSP = "/jsp/book.jsp";
	private static final String URL_CONFIRMBOOK_JSP = "/jsp/confirmFormBook.jsp";
	@EJB
	private BookBean bookBean;

	/**
	 * Initiation : add a book in the list of available books
	 */
	@Override
	public void init() throws ServletException {
		bookBean.init();
	}

	/**
	 * handles all GET methods : get all authors, all books, basket and handles
	 * filter
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (cart == null) {
			request.getSession().setAttribute("cart", new Cart());
		}
		if (request.getParameter("sort") != null && request.getParameter("sort").equals("sort_year")) {
			request.setAttribute("books", bookBean.getAllBooksOrderByDate());
		} else if (request.getParameter("sort") != null && request.getParameter("sort").equals("sort_author")) {
			request.setAttribute("books", bookBean.getAllBooksOrderByAuthor());
		} else if (request.getParameter("sort") != null && request.getParameter("sort").equals("sort_title")) {
			request.setAttribute("books", bookBean.getAllBooksOrderByTitle());
		} else if (request.getParameter("allAuthors") != null
				&& !request.getParameter("allAuthors").equals("Tous les auteurs")) {
			request.setAttribute("books", bookBean.getAllBooksByAuthor(request.getParameter("allAuthors")));
		} else if (request.getParameter("recherche") != null) {
			request.setAttribute("books", bookBean.getAllBooksByTitle(request.getParameter("recherche")));
		} else {
			request.setAttribute("books", bookBean.getAllBooks());
		}
		request.setAttribute("authors", bookBean.getAllAuthors());

		request.getRequestDispatcher(URL_BOOK_JSP).forward(request, response);
	}

	/**
	 * handles all POST methods : add and delete books
	 * 
	 * @param request
	 * @param response
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (request.getParameter("confirmAjout") != null) {
			request.getRequestDispatcher(URL_CONFIRMBOOK_JSP).forward(request, response);
		} else if (request.getParameter("ajouter") != null) {
			String author = request.getParameter("auteur");
			String title = request.getParameter("titre");
			int year = Integer.parseInt(request.getParameter("annee"));
			int stock = Integer.parseInt(request.getParameter("stock"));
			Book book = new Book(author, title, year, stock);
			bookBean.addBook(book);
		} else if (request.getParameter("supprimer") != null) {
			int id = Integer.parseInt(request.getParameter("supprimer"));
			Book book = bookBean.getBookById(id);
			if (cart.getBooks().containsKey(book)) {
				cart.getBooks().remove(book);
			}
			bookBean.removeBook(id);
		}
		doGet(request, response);
	}

}
