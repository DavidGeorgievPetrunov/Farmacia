import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.print.attribute.standard.OutputDeviceAssigned;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		Doctor d = new Doctor();
		ConnectionClass conect;
		try {
			conect = new ConnectionClass();
			String query = "Select * from doctor where mail ='"+user+"' and pass ='"+pass+"';"; 
			ResultSet res = conect.executeQuery(query);
			
			int rowcount = 0;
				
			while (res.next()) {
				rowcount += 1;
			}
			System.out.println(rowcount);
			if ( rowcount != 1 ) {
				response.getWriter().append("Error");	
			} else {
				response.getWriter().append(d.session);	
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		try {
			d.login(user, pass);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = request.getParameter("user");
		String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		
		if ( user == null || pass == null || user == "" || pass == "") {
			response.getWriter().append("Porfavor, inserta valores validos");
		} else {
			ConnectionClass conect;
			try {
				conect = new ConnectionClass();
				String query ="Select * from doctor where mail ='"+user+"';"; 
				ResultSet rs = conect.executeQuery(query);
				int rowcount = 0;
				while (rs.next()) {
					rowcount += 1;
					}
					
				if (rowcount == 0) {
											
					String update = "insert into doctor (mail,pass,name) values ('"+user+"','"+pass+"','"+name+"');"; 
					conect.executeUpdate(update);
					response.getWriter().append("Se ha creado el usuario");
				
				} else {
				
					response.getWriter().append("El usuario ya existe");
				
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
	}

}
