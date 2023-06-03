import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class Doctor extends Person {

	String pass;
	LocalDate lastlog;
	String session;
	ArrayList<Xip> releaseList;
	
	
	public Doctor() {
		this.releaseList = new ArrayList<>();
	}

	public Doctor(String name, String mail,	String pass, 
			LocalDate lastlog, String session, ArrayList<Xip> releaseList) {
		super(name, mail);
		this.pass = pass;
		this.lastlog = lastlog;
		this.session = session;
		this.releaseList = new ArrayList<>();
	}
	
	public void login(String mail, String pass) throws ClassNotFoundException {
		ConnectionClass conect;
		conect = new ConnectionClass();
								
			for (int i = 0; i < 9; i++) {
				Random rand = new Random();
				int randomNum = rand.nextInt(9 - 1 + 1) + 1;
				if (i == 0) {

				this.session = ""+randomNum;
				} else {
					this.session += randomNum;
				}
			}
				
		String update = "UPDATE doctor SET last_log = SYSDATE() , session ='"+this.session+"' where mail ='"+mail+"';";
		conect.executeUpdate(update);
				
		load(mail);
	}
	
	public boolean isLogged(String mail, String session) {
		ConnectionClass conect;
		try {
			conect = new ConnectionClass();
			String query = "Select * from doctor where mail ='"+mail+"' and session ='"+session+"';"; 
			ResultSet res = conect.executeQuery(query);
			res.next();
			
			return true;						

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
	
	public void load(String id) {
		
		ConnectionClass conect;
			try {
				conect = new ConnectionClass();
				String query = "Select * from doctor where mail ='"+id+"';"; 
				ResultSet res = conect.executeQuery(query);
				res.next();
				try {
					this.name = res.getString("name");
					this.mail = res.getString("mail");
					this.pass = res.getString("pass");
					this.lastlog = res.getDate("last_log").toLocalDate();
					this.session = res.getString("session");
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}


		
	}
	
	public void loadReleaseList() {
		ConnectionClass conect;
		try {
			conect = new ConnectionClass();
			String query = "Select * from xip where doctor_mail ='"+mail+"'and date >= CURDATE();"; 
			ResultSet res = conect.executeQuery(query);
			while (res.next()) {
				Xip xip = new Xip();
				xip.load(res.getInt("id"));
				this.releaseList.add(xip);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getTable() {
		
        StringBuffer table = new StringBuffer ("");
        table.append("<thead>"+
          "<th>IDChip</th>"+
          "<th>NombreMedicamento</th>"+
          "<th>MailPaciente</th>"+
          "<th>NombrePaciente</th>"+
          "<th>FechaFin</th>"+
          "</thead>");
        for (int i = 0; i <this.releaseList.size() ; i++) {
            table.append("<tr>" +
                    "<td>"+this.releaseList.get(i).id+"</td>"+
                    "<td>"+this.releaseList.get(i).medicine.name+"</td>"+
                    "<td>"+this.releaseList.get(i).patient.mail+"</td>"+
                    "<td>"+this.releaseList.get(i).patient.name+"</td>"+
                    "<td>"+this.releaseList.get(i).date+"</td>" +
                    "</tr>");
        }


        return " "+ table;
	}

}
