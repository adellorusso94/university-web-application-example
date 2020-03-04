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
		String matricola = (String) session.getAttribute("matricola");
		if (matricola == null) {
			response.sendRedirect("index.jsp");
		}
		
		// Gestione dati corsi
		ResultSet res = (ResultSet) request.getAttribute("tabella_corso");
	%>
	<p align="center">
		<img src="IMAGES/logo.png">
	</p>
	<%
		if (res != null) {
	%>
	<div align="center">
		<h4>Lista Corsi</h4>
		<table>
			<tr>
				<th>ID</th>
				<th>Materia</th>
				<th>Nome Docente</th>
				<th>Cognome Docente</th>
			</tr>
			<%
				while (res.next()) {
			%>
			<tr>
				<td><%=res.getInt("idcorso")%></td>
				<td><%=res.getString("materia")%></td>
				<td><%=res.getString("nome")%></td>
				<td><%=res.getString("cognome")%></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
	<%
		}
	%>
	<br>
	<div align="center" class="float">
		<form action="studente.jsp">
    		<input type="submit" value="Indietro"/>
		</form>
	</div>
</body>
</html>