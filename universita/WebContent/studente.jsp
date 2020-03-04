<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>UNISS - Università degli Studi di Sassari</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%
		// Ottieni dati dello studente dalla sessione creata
		String matricola = (String) session.getAttribute("matricola");
		if (matricola == null) {
			response.sendRedirect("index.jsp");
		}
		String nome = (String) session.getAttribute("nome");
		String cognome = (String) session.getAttribute("cognome");
		String data_nascita = (String) session.getAttribute("data_nascita");
	%>
	<p align="center">
		<img src="IMAGES/logo.png">
	</p>
	<div align="center">
		<h4>Profilo Studente</h4>
		<table>
			<tr>
				<th>Matricola</th>
				<td>
					<%
						out.print(matricola);
					%>
				</td>
			</tr>
			<tr>
				<th>Nome</th>
				<td>
					<%
						out.print(nome);
					%>
				</td>
			</tr>
				<th>Cognome</th>
				<td>
					<%
						out.print(cognome);
					%>
				</td>
			<tr>
				<th>Data di Nascita</th>
				<td>
					<%
						out.print(data_nascita);
					%>
				</td>
			</tr>
		</table>
	</div>
	<div align="center" class="float">
		<br>
		<form action="corsi" method="post">
			<input type="submit" value="Corsi">
		</form>
		<br>
		<form action="appelli" method="post">
			<input type="submit" value="Appelli">
		</form>
		<br>
		<form action="logout" method="post">
			<input type="submit" value="Esci">
		</form>
		<br>
	</div>
</body>
</html>