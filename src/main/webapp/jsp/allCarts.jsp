<%@page import="car.tp4.entity.Book"%>
<%@page import="car.tp4.entity.Cart"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Liste des commandes</title>
</head>
<body>
        <h3>Commandes</h3>
            <%
                Collection<Cart> carts = (Collection<Cart>) request.getAttribute("carts");
                for (Cart cart: carts) {
                	Map<Book, Integer> cartBooks = cart.getBooks();
                    out.print("<h4> Commande "+ cart.getId() + "</h4>");
					//out.print("<table>");
                    /*for (Book book : cartBooks.keySet()){
    					out.print("<tr><td>" + book.getId() + "</td>");
    					out.print("<td>" + book.getAuthor() + "</td>");
    					out.print("<td>" + book.getTitle() + "</td>");
    					out.print("<td>" + book.getYear() + "</td>");
    					out.print("<td>" + book.getStock() + "</td></tr>");
                    }*/

					//out.print("</table>");
                }
            %>

         <form method="GET" action="books">
         	<button type="submit" name="return">Retour</button>
        </form>
</body>
</html>