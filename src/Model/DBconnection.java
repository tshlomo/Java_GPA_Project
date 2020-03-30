package Model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Logger;


public class DBconnection {
    static String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    static String protocol = "jdbc:derby:gpadb;create=true";
    private static Logger logger=Logger.getLogger(DBconnection.class.getName());
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
                        //Instantiating the driver class will indirectly register
                        //this driver as an available driver for DriverManager
                        Class.forName(driver);
                        //Getting a connection by calling getConnection
                        logger.info("connecting to database...");
                        conn = DriverManager.getConnection(protocol);
                        logger.info(" connection has been established");
                        statement = conn.createStatement();
                        logger.info("searching for GPA table...");
                        rs = conn.getMetaData().getTables(null, "APP", "%", null);
                        if(!rs.next()){
                            logger.info("it seems that GPA table does not exist. don't worry we are making a new one right now...");
                            statement.execute("CREATE TABLE GPA(Course VARCHAR(255),shana INT,Semester INT,TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course))");
                            logger.info("A table has been created");
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
                    //sagi??
                }
            }
        }
        return conn;
    }
}