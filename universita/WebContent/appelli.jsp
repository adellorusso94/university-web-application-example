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
		String matricola = (String) session.getAttribute("matricola");
		if (matricola == null) {
			response.sendRedirect("index.jsp");
		}
	
		// Gestione dati appelli
		ResultSet res = (ResultSet) request.getAttribute("tabella_appelli");
		String materia = (String) request.getAttribute("materia");
		Date dataAppello = (Date) request.getAttribute("data");
	%>
	<p align="center">
		<img src="IMAGES/logo.png">
	</p>
	<%
		if (res != null) {
	%>
	<div align="center">
		<h4>Appelli disponibili</h4>
		<form action="Prenotazione" method="post">
			<select name="appello">
			<%
				while (res.next()) {
			%>
				<option value="<%=res.getInt(1)%>"><%=res.getString("Materia")%> - Appello del <%=res.getDate("Data")%></option>
			<%
				}
			%>
			</select>
			<br>
			<br>
			<input type="submit" value="Prenota">
		</form>
	</div>
	<%
		} else {
	%>
		<div align="center">
			<h4>Prenotazione effettuata con successo per l'esame di <%=materia%> del <%=dataAppello%>.</h4>
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