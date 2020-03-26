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
        ResultSet rs = null;
        try {
            //Instantiating the dirver class will indirectly register
            //this driver as an available driver for DriverManager
            if (conn == null) {
                Class.forName(driver);
                //Getting a connection by calling getConnection
                conn = DriverManager.getConnection(protocol);
            }

            statement = conn.createStatement();
            rs = statement.executeQuery("select * from gpa");
            if (!rs.next()) {
                String quary = "create table gpa(course varchar(255),years varchar(255),semester varchar(255),testGrade int,credits double,finalGrade int)";
                statement.execute(quary);
                //statement.executeUpdate("insert into gpa values ('infi','a','a',85,3,90)");
            }
            //statement.execute("drop table gpa");

            rs = statement.executeQuery("SELECT * from gpa");
            while (rs.next()) {
                System.out.println("course= " + rs.getString("course") + " years= " + rs.getString("years") + " semester= " + rs.getString("semester") + " testGrade= " + rs.getInt("testGrade") + " credits= " + rs.getDouble("credits") + " finalGrade= " + rs.getInt("finalGrade"));
            }

        } catch (Exception e) {
            e.printStackTrace();
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
            return conn;

        }
    }
}