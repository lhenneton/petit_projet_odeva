<%@page import="car.tp4.entity.Book" %>
<%@page import="java.util.Collection" %>
<%@page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8"%>
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
    <h3>Êtes-vous sur de vouloir ajouter ce livre ?</h3>
    <%
        String titre = request.getParameter("titre") != null ? request.getParameter("titre") : "";
        String auteur = request.getParameter("auteur") != null ? request.getParameter("auteur") : "";
        String annee = request.getParameter("annee") != null ? request.getParameter("annee") : "";
        String stock = request.getParameter("stock") != null ? request.getParameter("stock") : "";
    %>
    <form method="POST">
        <p>Titre : <%=titre%> </p>
        <p>Auteur : <%=auteur%> </p>
        <p>Année : <%=annee%></p>
        <p>Stock : <%=stock%></p> 
        <input id="titre" type="hidden" name="titre" required="required" value="<%=titre%>"/>
        <input id="auteur" type="hidden" name="auteur" required="required" value="<%=auteur%>"/>
        <input id="annee" type="hidden" name="annee" min="0" required="required" value="<%=annee%>"/>
        <input id="stock" type="hidden" name="stock" min="0" required="required" value="<%=stock%>"/>      

        <button type="submit" name="ajouter">Oui</button>
        <button type="submit" name="nePasAjouter">Non</button>

    </form>
    
</div>
</body>
</html>

