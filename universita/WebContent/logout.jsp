<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>UNISS - Universit� degli Studi di Sassari</title>
	<link rel="stylesheet" href="styles.css">
</head>
<body>
	<%
		// Controllo sessione
		session.invalidate();
		response.sendRedirect("index.jsp");
	%>
</body>
</html>