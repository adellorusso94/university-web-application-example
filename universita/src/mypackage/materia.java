package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class StampaStudenti
 */
@WebServlet("/materia")
public class materia extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public materia() {
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
			
			// Ottieni dati sulla materia dal database
			PreparedStatement smt = conn.prepareStatement("select idcorso, Materia from corso where cattedra=?");
			smt.setString(1, idProfessore);
			ResultSet rs = smt.executeQuery();
			rs.next();
			
			String idcorso = rs.getString("idcorso");
			String materia = rs.getString("materia");
			
			// Ottieni dati sugli appelli dal database
			PreparedStatement smt1 = conn.prepareStatement("select idAppello, Data from appello where idMateria=?");
			smt1.setString(1, idcorso);
			ResultSet appelli = smt1.executeQuery();
			
			// Risposta contenente pagina dove dirigersi
			RequestDispatcher rd = request.getRequestDispatcher("materia.jsp");
			request.setAttribute("materia", materia);
			request.setAttribute("tabella_appelli", appelli);
			rd.forward(request,response);
						
		} catch (Exception e) {
			
			System.out.println("Errore");
			
		}
		
	}
	
}
