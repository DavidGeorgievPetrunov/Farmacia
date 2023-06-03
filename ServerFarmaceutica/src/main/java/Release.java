

import java.io.IOException;
import java.security.KeyStore.TrustedCertificateEntry;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.Doc;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Release
 */
@WebServlet("/Release")
public class Release extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Release() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		String idXip = request.getParameter("idXip");
		String idMed = request.getParameter("idMed");
		String date = request.getParameter("date");
		String mailP = request.getParameter("mailP");

		System.out.println(idMed);
		
		if (mail == "" || mail == null || session == "" || session == null 
			|| idXip == "" || idXip == null || idMed == "" || idMed == null 
			|| date == "" || date == null || mailP == "" || mailP == null) {
			response.getWriter().append("Algun dato esta vacio");
		} else {
		
		System.out.println(date);
		
		Doctor d = new Doctor();		
		
		if (d.isLogged(mail, session)) {
			ConnectionClass conect;
			boolean error;
			error = false;
			try {
				conect = new ConnectionClass();
				
				String query = "Select id from xip";
				ResultSet res = conect.executeQuery(query);
				while (res.next()) {
					if (res.getInt("id") == Integer.parseInt(idXip)) {
						error = true;
					}
				}
				
				if (error == true) {
					response.getWriter().append("Inserta otro id de Xip");	
				} else {
					
					String update = "INSERT INTO xip VALUES ('"+idXip+"','"+mail+"','"+Integer.parseInt(idMed)+"','"+mailP+"','"+date+"');";
					conect.executeUpdate(update);
					response.getWriter().append("Exito");	
				}					

				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
