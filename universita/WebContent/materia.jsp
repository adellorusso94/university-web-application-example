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
		// Controllo sessione
		String idProfessore = (String) session.getAttribute("id");
		if (idProfessore == null) {
			response.sendRedirect("index.jsp");
		}
	
		// Gestione dati materia ed appelli
		String materia = (String) request.getAttribute("materia");
		ResultSet res = (ResultSet) request.getAttribute("tabella_appelli");
	%>
	<p align="center">
		<img src="IMAGES/logo.png">
	</p>
	<%
		if (res != null) {
	%>
	<div align="center">
		<h4>Appelli di <%=materia%></h4>
		<table>
			<tr>
				<th>ID</th>
				<th>Data</th>
			</tr>
			<%
				while (res.next()) {
			%>
			<tr>
				<td><%=res.getInt("idAppello")%></td>
				<td><%=res.getDate("Data")%></td>
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
		<form action="professore.jsp">
    		<input type="submit" value="Indietro"/>
		</form>
	</div>
</body>
</html>