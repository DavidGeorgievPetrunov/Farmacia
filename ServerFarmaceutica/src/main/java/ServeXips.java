

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServeXips
 */
@WebServlet("/ServeXips")
public class ServeXips extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServeXips() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Doctor d = new Doctor();

		String mail = request.getParameter("mail");
		String session = request.getParameter("session");

		System.out.println(mail+" "+session);
		
		Boolean isLog = d.isLogged(mail,session);
		if (isLog = true) {
			d.load(mail);
			d.loadReleaseList();
			response.getWriter().append(d.getTable());
		} else {
			System.out.println("Hay un error con los datos del sessionStorage");
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
