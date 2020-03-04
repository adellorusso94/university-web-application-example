package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class elencoStudenti
 */
@WebServlet("/elencoStudenti")
public class ElencoStudenti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ElencoStudenti() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Abbiamo preso username e password
		String appello = request.getParameter("appello");
		
		// Abbiamo stabilito una connessione ad db
		Connection conn = Connessione.getCon();
			
		try {
					
			// Ottieni dati su appelli
			PreparedStatement smt = conn.prepareStatement("select matricola, nome, cognome, data_nascita\r\n" + 
					"from prenotazione inner join studente\r\n" + 
					"on prenotazione.stud_prenotato = studente.matricola\r\n" +
					"where prenotazione.app_prenotato=?");
			smt.setString(1, appello);
			ResultSet rs = smt.executeQuery();
			
			while(rs.next()) {
				RequestDispatcher rd = request.getRequestDispatcher("studenti.jsp");
				request.setAttribute("tabella_studenti", rs);
				rd.forward(request,response);
			}
			
			// Appelli non disponibili
			RequestDispatcher rd1 = request.getRequestDispatcher("studenti.jsp");
			String messaggio = "Nessuno studente è iscritto all'appello.";
			request.setAttribute("messaggio", messaggio);
			rd1.forward(request,response);
			
		} catch (SQLException e) {
					
			System.out.println("Errore");
					
		}

	}

}
