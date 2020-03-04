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
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Abbiamo preso username e password
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		// Abbiamo stabilito una connessione ad db
		Connection conn = Connessione.getCon();
		
		try {
			// Definisci sessione
			HttpSession session;
			
			// Login studente
			Statement smt = conn.createStatement(); 
			ResultSet rs = smt.executeQuery("select username, password from studente");
			
			while(rs.next()) {
				
                // Abbiamo verificato con un check se la user e la password corridspondono a quelle presenti nel db
				if (rs.getString("username").equalsIgnoreCase(username) && rs.getString("password").equalsIgnoreCase(password)) {
					
					// Ottieni dati studente dal database
					PreparedStatement smt1 = conn.prepareStatement("select matricola, nome, cognome, data_nascita from studente where username=?");
					smt1.setString(1, username);
					ResultSet rs1 = smt1.executeQuery();
					rs1.next();
					
					String matricola = rs1.getString("matricola");
					String nome = rs1.getString("nome");
					String cognome = rs1.getString("cognome");
					String data_nascita = rs1.getString("data_nascita");
					
					// Crea sessione contenente dati studente
					session = request.getSession(true); 
					session.setAttribute("matricola", matricola);
					session.setAttribute("nome", nome);
					session.setAttribute("cognome", cognome);
					session.setAttribute("data_nascita", data_nascita);
					
					// Pagina dove dirigersi
					RequestDispatcher rd = request.getRequestDispatcher("studente.jsp");
                    rd.forward(request,response);
            
				}
				
			}
			
			// Login professore
			Statement smt2 = conn.createStatement();
			ResultSet rs2 = smt2.executeQuery("select username, password from professore");
			
			while(rs2.next()) {
				
				if(rs2.getString("username").equalsIgnoreCase(username) && rs2.getString("password").equalsIgnoreCase(password)){
					
					// Ottieni dati professore dal database
					PreparedStatement smt3 = conn.prepareStatement("select idProfessore, nome, cognome, data_nascita from professore where username=?");
					smt3.setString(1, username);
					ResultSet rs3 = smt3.executeQuery();
					rs3.next();
					
					String idProfessore = rs3.getString("idProfessore");
					String nome = rs3.getString("nome");
					String cognome = rs3.getString("cognome");
					String data_nascita = rs3.getString("data_nascita");
					
					// Crea sessione contenente dati professore
					session = request.getSession(true);
					session.setAttribute("id", idProfessore);
					session.setAttribute("nome", nome);
					session.setAttribute("cognome", cognome);
					session.setAttribute("data_nascita", data_nascita);
					
					// Pagina dove dirigersi
					RequestDispatcher rd1 = request.getRequestDispatcher("professore.jsp");
					rd1.forward(request, response);
				}
				
			}
			
			// Username e Password non presenti
			RequestDispatcher rd2 = request.getRequestDispatcher("index.jsp");
			String messaggio = "Username e Password non sono presenti";
			request.setAttribute("messaggio", messaggio);
			rd2.forward(request, response);
			
		} catch (Exception e ) {
			
			System.out.println(e.getMessage());
			
		}
				
	}
	
}
