package mypackage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
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
 * Servlet implementation class Prenotazione
 */
@WebServlet("/Prenotazione")
public class Prenotazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Prenotazione() {
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
	
		HttpSession session = request.getSession();
		
		String matricola = (String) session.getAttribute("matricola");
		String idAppello = request.getParameter("appello");
		
		// Abbiamo stabilito una connessione ad db
		Connection conn= Connessione.getCon();
		
		try {
			
			PreparedStatement smt = conn.prepareStatement("select idAppello, materia, data from appello inner join corso on appello.idMateria = corso.idcorso where idAppello=CAST(? AS UNSIGNED INTEGER)");
			smt.setString(1, idAppello);
			ResultSet rs = smt.executeQuery();
			rs.next();
			
			PreparedStatement smt2 = conn.prepareStatement("insert into prenotazione (stud_prenotato,app_prenotato) values (CAST(? AS UNSIGNED INTEGER),CAST(? AS UNSIGNED INTEGER))");
			smt2.setString(1, matricola);
			smt2.setString(2, idAppello);
			smt2.executeUpdate();
			
			String nomeMateria = rs.getString("materia");
			Date dataAppello = rs.getDate("data");
			RequestDispatcher rd = request.getRequestDispatcher("appelli.jsp");
			request.setAttribute("materia", nomeMateria);
			request.setAttribute("data", dataAppello);
			rd.forward(request, response);
			
		}catch (SQLException e) {
			
			System.out.println(e.getMessage());
			
		}
	}
	
}