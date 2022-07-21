
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
    static Connection con;
    public static Connection createConnection(){

        try {
            // load driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // create connection
            String url = "jdbc:mysql://localhost:3306/abhishek";
            String user = "root";
            String password = "pagla";
            con = DriverManager.getConnection(url, user, password);
        } catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
}
