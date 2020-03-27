package Model;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBconnection {
    public static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    public static String protocol = "jdbc:derby:gpaDB;create=true";


    public static Connection GetDBConnection() throws SQLException {
        Connection conn = null;
        Statement statement = null;
        ResultSet rs = null;

        try {
            //Instantiating the dirver class will indirectly register
            //this driver as an available driver for DriverManager
                Class.forName(driver);
                //Getting a connection by calling getConnection
                conn = DriverManager.getConnection(protocol);
            statement = conn.createStatement();
            rs = statement.executeQuery("select * from gpa");
            if (!rs.next()) {
                String quary = "create table gpa(course varchar(255),year1 varchar(255),semester varchar(255),testGrade int,credits double,finalGrade int)";
                statement.execute(quary);
                //statement.executeUpdate("insert into gpa values('infi','c','a',76,3.5,98)");

            }

        } catch (Exception e) {
            System.out.println("table gpa does not exist");;
        } finally {
            if (statement != null) try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return conn;
    }
}