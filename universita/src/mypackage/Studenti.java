package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Studenti
 */
@WebServlet("/studenti")
public class Studenti extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public Studenti() {
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
		
		// Abbiamo stabilito una connessione ad db
		Connection conn = Connessione.getCon();
		
		try {
			
			// Ottieni dalla sessione id professore
			HttpSession session = request.getSession();
			String idProfessore = (String) session.getAttribute("id");
			
			// Ottieni dati su appelli
			PreparedStatement smt = conn.prepareStatement("select idAppello, Data, idMateria, Materia\r\n" + 
					"from appello inner join corso\r\n" + 
					"on appello.idMateria = corso.idcorso\r\n" +
					"where corso.cattedra=?");
			smt.setString(1, idProfessore);
			ResultSet rs = smt.executeQuery();
			rs.next();
						
			// Risposta contenente pagina dove dirigersi
			RequestDispatcher rd = request.getRequestDispatcher("studenti.jsp");
			request.setAttribute("elenco_appelli", rs);
			rd.forward(request,response);
						
		} catch (Exception e) {
			
			System.out.println("Errore");
			
		}
		
	}
}
