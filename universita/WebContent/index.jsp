<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="java.sql.*"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>UNISS - Università degli Studi di Sassari</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%
		// Controllo sessione
		String matricola = (String) session.getAttribute("matricola");
		if (matricola != null) {
			response.sendRedirect("studente.jsp");
		}
		
		String idProfessore = (String) session.getAttribute("id");
		if (idProfessore != null) {
			response.sendRedirect("professore.jsp");
		}
	%>
	<p align="center">
		<img src="IMAGES/logo.png">
	</p>
	<%
		String messaggio = (String) request.getAttribute("messaggio");
	%>
	<%
		if (messaggio != null) {
	%>
	<p align="center">
		<a style="color: black; font-weight: bold; font-size: 20px;">
			<%
				out.print(messaggio);
			%>
		</a>
		<%-- si poteva fare anche con l'espressione <%=messaggio%> --%>
	</p>
	<%
		}
	%>
	<div align="center" class="float">
		<form action="login" method="post">
			<p>
				<h4>Username</h4>
				<input type="text" name="username">
			</p>
			<p>
				<h4>Password</h4>
				<input type="password" name="password">
			</p>
			<p>
				<input type="submit" value="Accedi">
			</p>
		</form>
	</div>
</body>
</html>