package Model;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;



public class DBconnection {
    static Connection conn = null;
    private DBconnection() {
    }

    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String protocol = "jdbc:derby:gpaDB;create=true";


    public static Connection GetDBConnection() throws SQLException {

        Statement statement = null;
        ResultSet rs=null;
        try {
                //Instantiating the dirver class will indirectly register
                //this driver as an available driver for DriverManager
            if(conn==null) {
                Class.forName(driver);
                //Getting a connection by calling getConnection
                conn = DriverManager.getConnection(protocol);
            }
            statement = conn.createStatement();
            statement.execute("create table gpa(course varchar(255),semester varchar(255),testGrade int,credit double,finalGrade int)");
            statement.executeUpdate("insert into gpa values ('infi','a',85,3.5,90)");

        } catch (Exception e) {
            e.printStackTrace();
        }
        if (statement != null) try {
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;

    }
}