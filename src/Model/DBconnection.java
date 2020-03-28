package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


public class DBconnection {
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    static String protocol = "jdbc:derby:gagadb;create=true";

    //eager-instantiating conn var which will connect with the db
    private static Connection conn = null;

    //the getDBConnection function is basically the getInstance function for regular singleton pattern.
    //using here double buffering singleton just to make sure we won't duplicate the instantiation of conn var
    public static Connection getDBConnection() throws SQLException {
        Statement statement = null;
        ResultSet rs = null;

        if(conn==null) {

            synchronized (DBconnection.class)
            {
                if(conn == null)
                {
                    try {
                        //Instantiating the dirver class will indirectly register
                        //this driver as an available driver for DriverManager
                        Class.forName(driver);
                        //Getting a connection by calling getConnection
                        conn = DriverManager.getConnection(protocol);
                        statement = conn.createStatement();
                        rs = conn.getMetaData().getTables(null, "APP", "%", null);
                        if(!rs.next()){
                            statement.execute("CREATE TABLE GPA(Course VARCHAR(255),Semester VARCHAR(255),TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course))");
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


                    }
                }//the second IF statement ends here
                else{
                    //SOMEHOW 2 threads managed to get into the synchronized part and one of them couldn't see conn as null since the other thread instantiated it.
                    //TODO log commands
                }
            }
        }
        return conn;
    }
}