import java.sql.ResultSet;
import java.sql.SQLException;

public class Patient extends Person {

	public Patient() {
		// TODO Auto-generated constructor stub
	}

	public Patient(String name, String mail) {
		super(name, mail);
		// TODO Auto-generated constructor stub
	}

	public void load(String id) {
		ConnectionClass conect;		
		try {
			conect = new ConnectionClass();
			String query = "Select * from patient where mail ='"+id+"';"; 
			ResultSet res = conect.executeQuery(query);
			res.next();
			
			this.mail = res.getString("mail");
			this.name = res.getString("name");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
