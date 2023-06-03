

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ServeMedicines
 */
@WebServlet("/ServeMedicines")
public class ServeMedicines extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServeMedicines() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Doctor d = new Doctor();

		String mail = request.getParameter("mail");
		String session = request.getParameter("session");
		ArrayList<Medicine> MedicineList;
		MedicineList = new ArrayList<>();
        Gson gson = new GsonBuilder().create();
        String json = "";
		if (d.isLogged(mail,session)) {
			ConnectionClass conect;		
			try {
				conect = new ConnectionClass();
				String query = "Select * from medicine;"; 
				ResultSet res = conect.executeQuery(query);
				while (res.next()) {
					Medicine medicine = new Medicine();
					medicine.load(res.getInt("id"));
					MedicineList.add(medicine);
				}
				
				json = gson.toJson(MedicineList);
				response.getWriter().append(json);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

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
