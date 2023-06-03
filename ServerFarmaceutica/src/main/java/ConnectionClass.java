import java.sql.*;

public class ConnectionClass {
    private Connection conn;
    private Statement st;
    private ResultSet rSet;
    private String host;
    private String dbname;
    private String port;
    private String password;
    private String username;

    public ConnectionClass () throws ClassNotFoundException {
    	this.host = "localhost";
        this.dbname = "farmacia";
        this.port = "3300";
        this.password = "";
        this.username = "root";

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb:// " + this.host + ":" + this.port + "/" + this.dbname;
            conn = (Connection)DriverManager.getConnection(url, this.username, this.password);
            System.out.println("Connected");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public ConnectionClass (String host, String dbname, String port, String password, String username)
            throws ClassNotFoundException {
        this.host = host;
        this.dbname = dbname;
        this.port = port;
        this.password = password;
        this.username = username;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb:// " + this.host + ":" + this.port + "/" + this.dbname;
            conn = (Connection)DriverManager.getConnection(url, this.username, this.password);
            System.out.println("Connected");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    public ResultSet executeQuery(String query) {
        
        try {
            st = conn.createStatement();
            rSet = st.executeQuery(query);
            System.out.println("Query Success");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rSet;
    }
    
    public void executeUpdate(String query) {
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Success");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
    public static void main(String[] args) {
		try {
			ConnectionClass conn = new ConnectionClass("localhost", "farmacia", "3300", "", "root");
			System.out.println("Connection");
			String mail = "prueba";
			String pass = "prueba";
			String query = "Select * from doctor where mail ='"+mail+"' and pass ='"+pass+"';"; 
			ResultSet res = conn.executeQuery(query);
			boolean know;
			try {
				know = res.next();
				System.out.println(know);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			conn.executeUpdate("insert into doctor values ('prueba','prueba','prueba','2023-12-2',1)");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("Ha ido mal");
		}
		
	}
}

