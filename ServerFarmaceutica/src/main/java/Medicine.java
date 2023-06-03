import java.sql.ResultSet;
import java.sql.SQLException;

public class Medicine {

	int id;
	String name;
	Float Tmax;
	Float Tmin;
	
	public Medicine() {
		// TODO Auto-generated constructor stub
	}
	
	public Medicine(int id, String name, Float Tmax, Float Tmin) {
		this.id = id;
		this.name = name;
		this.Tmax = Tmax;
		this.Tmin = Tmin;
		// TODO Auto-generated constructor stub
	}
	
	public void load(int id) {
		ConnectionClass conect;		
		try {
			conect = new ConnectionClass();
			String query = "Select * from medicine where id ='"+id+"';"; 
			ResultSet res = conect.executeQuery(query);
			res.next();
			
			this.id = res.getInt("id");
			this.name = res.getString("name");
			this.Tmax = res.getFloat("Tmax");
			this.Tmin = res.getFloat("Tmin");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
