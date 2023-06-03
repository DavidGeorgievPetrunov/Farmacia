import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Xip {

	int id;
	Medicine medicine;
	Patient patient;
	Date date;
	
	public Xip() {
		// TODO Auto-generated constructor stub
	}

	public Xip(int id, Medicine medicine, Patient patient, Date date) {
		this.id = id;
		this.medicine = medicine;
		this.patient = patient;
		this.date = date;
		// TODO Auto-generated constructor stub
	}

	public void load(int id) {
		ConnectionClass conect;		
		try {
			conect = new ConnectionClass();
			String query = "Select * from xip where id ='"+id+"';"; 
			ResultSet res = conect.executeQuery(query);
			res.next();
			Patient patient = new Patient();
			Medicine medicine = new Medicine();
			patient.load(res.getString("id_patient"));
			medicine.load(res.getInt("id_medicine"));
			
			this.id = res.getInt("id");
			this.medicine = medicine;
			this.patient = patient;
			this.date = res.getDate("date");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
