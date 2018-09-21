<%@page import="car.tp4.entity.Book"%>
<%@page import="car.tp4.entity.Cart"%>
<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.0/js/bootstrap.min.js">
</head>
<body>
	<div class="container">

		<form method="GET" action="cart">
			<button type="submit" name="seeCart">Afficher le panier</button>
		</form>

		<form method="GET" action="cart">
			<button type="submit" name="seeAll">Afficher toutes les commandes</button>
		</form>
		<h3>Ajouter un livre</h3>
		<%
			String titre = request.getParameter("titre") != null ? request.getParameter("titre") : "";
			String auteur = request.getParameter("auteur") != null ? request.getParameter("auteur") : "";
			String annee = request.getParameter("annee") != null ? request.getParameter("annee") : "";
			String stock = request.getParameter("stock") != null ? request.getParameter("stock") : "";
			String recherche = request.getParameter("recherche") != null ? request.getParameter("recherche") : "";
		%>
		<form method="POST">
			<label for="titre">Titre : </label> <input id="titre" type="text"
				name="titre" required="required" value="<%=titre%>" /> <label
				for="auteur">Auteur : </label> <input id="auteur" type="text"
				name="auteur" required="required" value="<%=auteur%>" /> <label
				for="annee">Année : </label> <input id="annee" type="number"
				name="annee" min="0" required="required" value="<%=annee%>" /> <label
				for="stock">Stock : </label> <input id="stock" type="number"
				name="stock" min="0" required="required" value="<%=stock%>" />

			<button type="submit" name="confirmAjout">Ajouter</button>
		</form>



		<h3>Librairie</h3>
		<form method="GET">

			<br> <label for="recherche">Titre : </label> <input
				id="recherche" type="text" name="recherche" value="<%=recherche%>" />
			<select name="allAuthors">
				<option>Tous les auteurs</option>
				<%
					Collection<String> authors = (Collection<String>) request.getAttribute("authors");

					for (String a : authors) {
						out.print("<option>" + a + "</option>");
					}
				%>
			</select>
			<p>Trier par :</p>
			<p>
				<input type="radio" name="sort" value="sort_year"> année</input>
			</p>
			<p>
				<input type="radio" name="sort" value="sort_author"> auteur</input>
			</p>
			<p>
				<input type="radio" name="sort" value="sort_title"> titre</input>
			</p>
			<button type="submit">Rechercher</button>
			<br> <br>
		</form>


		<table style="width: 100%; text-align: center;" border="1px">
			<tr>
				<th>Id</th>
				<th>Auteur</th>
				<th>Titre</th>
				<th>Année</th>
				<th>Stock</th>
				<th>Supprimer de la bibliothèque</th>
				<th>Ajouter au panier</th>
			</tr>

			<%
				Collection<Book> books = (Collection<Book>) request.getAttribute("books");
				Cart cart = (Cart) request.getSession().getAttribute("cart");
				Map<Book, Integer> cartBooks = cart.getBooks();

				for (Book book : books) {
					out.print("<tr>");
					out.print("<td>" + book.getId() + "</td>");
					out.print("<td>" + book.getAuthor() + "</td>");
					out.print("<td>" + book.getTitle() + "</td>");
					out.print("<td>" + book.getYear() + "</td>");
					out.print("<td>" + book.getStock() + "</td>");
			%>
			<td>
				<form method="POST">

					<button type="submit" name="supprimer" value="<%=book.getId()%>">-</button>
				</form>
			</td>
			<%
				if (((cartBooks.containsKey(book) && cartBooks.get(book) < book.getStock())
							|| !cartBooks.containsKey(book)) && book.getStock() > 0) {
			%>



			<td>
				<form method="POST" action="cart">
					<button type="submit" name="ajouterPanier"
						value="<%=book.getId()%>">+</button>
				</form>
			</td>
			<%
				}
					out.print("</tr>");
				}
			%>

		</table>




	</div>
</body>
</html>

