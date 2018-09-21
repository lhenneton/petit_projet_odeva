<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="car.tp4.entity.Cart"%>
<%@page import="car.tp4.entity.Book"%>

<%@page import="java.util.Map"%>
<%@page import="java.util.Collection"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="jsp/css/theme.css">
<title>Current cart</title>
</head>
<body>

	<%
		Cart cart = (Cart) request.getSession().getAttribute("cart");
		Map<Book, Integer> books = cart.getBooks();
	%>
	<h3>Mon panier</h3>


	<%
		if (books.isEmpty()) {
			out.print("Le panier est vide");
			out.print("<br/>");
		} else {
			out.print("<table style='width:100%;text-align:center;' border='1px'>");
			out.print("<tr>");
			out.print("<th>Id</th>");
			out.print("<th>Auteur</th>");
			out.print("<th>Titre</th>");
			out.print("<th>Année</th>");
			out.print("<th>Quantité</th>");
			out.print("<th>Retirer du panier</th>");
			out.print("</tr>");
			for (Book book : books.keySet()) {
				out.print("<tr>");
				out.print("<td>" + book.getId() + "</td>");
				out.print("<td>" + book.getAuthor() + "</td>");
				out.print("<td>" + book.getTitle() + "</td>");
				out.print("<td>" + book.getYear() + "</td>");
				out.print("<td>" + books.get(book) + "</td>");
	%>
	<td>
		<form method="POST" action="cart">
			<button type="submit" name="retirerPanier" value="<%=book.getId()%>">-</button>
		</form>
	</td>
	</tr>
	<%
		}
		}
	%>
	</table>
	<form method="POST" action="cart">

		<button type="submit" name="viderPanier">Vider le panier</button>
	</form>

	<form method="POST" action="cart">
		<button type="submit" name="validerPanier">Passer la commande</button>
	</form>

	<form method="GET" action="books">
		<button type="submit" name="return">Retour</button>
	</form>
	</div>
</body>
</html>