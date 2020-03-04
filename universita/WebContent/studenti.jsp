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
		
		// Gestione dati studenti
		ResultSet res1 = (ResultSet) request.getAttribute("elenco_appelli");
		ResultSet res2 = (ResultSet) request.getAttribute("tabella_studenti");
		
		// Gestione appelli non disponibili
		String msg = (String) request.getAttribute("messaggio");
	%>
	<p align="center">
		<img src="IMAGES/logo.png">
	</p>
	<%
		if (msg != null) {
	%>
		<div align="center">
			<h4><%out.print(msg);%></h4>
		</div>
	<%
		} else {
			if (res1 != null) {
	%>
		<div align="center">
			<h4>Prenotazioni appelli di <%=res1.getString("Materia")%></h4>
			<form action="elencoStudenti" method="post">
				<select name="appello">
				<%
					do {
				%>
					<option value="<%=res1.getInt("idAppello")%>">Appello del <%=res1.getDate("Data")%></option>
				<%
					} while (res1.next());
				%>
				</select>
				<br>
				<br>
				<input type="submit" value="Ricerca">
			</form>
		</div>
		<%
			} else if (res2 != null) {
		%>
		<div align="center">
			<h4>Studenti prenotati</h4>
			<table>
			<tr>
				<th>Matricola</th>
				<th>Nome</th>
				<th>Cognome</th>
				<th>Data di Nascita</th>
			</tr>
			<%
				do {
			%>
			<tr>
				<td><%=res2.getInt("matricola")%></td>
				<td><%=res2.getString("nome")%></td>
				<td><%=res2.getString("cognome")%></td>
				<td><%=res2.getString("data_nascita")%></td>
			</tr>
			<%
				} while (res2.next());
			%>
		</table>
		</div>
	<%
		}
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