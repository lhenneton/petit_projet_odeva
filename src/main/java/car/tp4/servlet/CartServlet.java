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
import car.tp4.entity.CartBean;

/**
 * Servlet that handles all between jsp and beans
 * 
 * @author Claire Auvray & Laury Henneton
 */
@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	@EJB
	private BookBean bookBean;

	@EJB
	private CartBean cartBean;

	/**
	 * handles all GET methods : see current cart and see all validated carts
	 * filter
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cart cart = (Cart) request.getSession().getAttribute("cart");

		if (request.getParameter("seeAll") != null) {
			request.setAttribute("carts", cartBean.getAllCart());
			request.getSession().getAttribute("cart");

			request.getRequestDispatcher("/jsp/allCarts.jsp").forward(request, response);
		}
		request.setAttribute("books", cart.getBooks());
		request.getRequestDispatcher("/jsp/cart.jsp").forward(request, response);

	}

	/**
	 * handles all POST methods : add and delete books of the cart, empty cart and validate cart
	 * 
	 * @param request
	 * @param response
	 */
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cart cart = (Cart) request.getSession().getAttribute("cart");
		if (request.getParameter("ajouterPanier") != null) {
			int id = Integer.parseInt(request.getParameter("ajouterPanier"));
			Book book = bookBean.getBookById(id);
			cart.addBook(book);
		} else if (request.getParameter("retirerPanier") != null) {
			int id = Integer.parseInt(request.getParameter("retirerPanier"));
			Book book = bookBean.getBookById(id);
			cart.removeBook(book);
		} else if (request.getParameter("viderPanier") != null) {
			cart.emptyCart();
		} else if (request.getParameter("validerPanier") != null) {
			cart.validateCart(bookBean);
			cartBean.addCart(cart);
			cart = new Cart();
		}
		request.getSession().setAttribute("cart", cart);
		request.setAttribute("books", bookBean.getAllBooks());

		doGet(request, response);

	}
}
