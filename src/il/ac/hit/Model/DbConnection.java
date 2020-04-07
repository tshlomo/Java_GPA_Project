package il.ac.hit.Model;
import il.ac.hit.Exceptions.DBActionsException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.logging.Logger;

/**
 * This class is a Singleton pattern and is in charge of the connection to the DB
 * it's only method is used throughout every action which interacts with the DB and get the connection
 */
public class DbConnection {
    private static final String driver = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String protocol = "jdbc:derby:gpaDB;create=true";
    private static Logger logger=Logger.getLogger(DbConnection.class.getName());
    //eager-instantiating conn var which will connect with the db
    private static Connection conn = null;

    /**
     * private constructor of class DbConnection loads driver
     */
    //private constructor not to be used outside the class
    private DbConnection() throws ClassNotFoundException{
        //Instantiating the driver class will indirectly register
        //this driver as an available driver for DriverManager
        Class.forName(driver);
    }

    /**
     * a static method - checks if there is a proper DB and if not creates one.
     * establishes connection to specific db and checks if the grade table exists, if not, creates one.
     * returns a Connection type object which will be used for all the other methods that need to work with the db.
     * <p>
     *     synchronizes with multiple threads trying to access the object
     * </p>
     *
     * @throws DBActionsException if an sql exception occurred
     * @return Connection type object
     */
    //the getDBConnection function is basically the getInstance function for regular singleton pattern.
    //using here double buffering singleton just to make sure we won't duplicate the instantiation of conn var
    public static Connection getDBConnection() throws DBActionsException {
        Statement statement = null;
        ResultSet rs = null;

        if(conn==null) {
            synchronized (DbConnection.class)
            {
                if(conn == null)
                {
                    try{
                        //Getting a connection by calling getConnection
                        logger.info("connecting to database...");
                        conn = DriverManager.getConnection(protocol);
                        logger.info(" connection to database has been established");
                        statement = conn.createStatement();
                        logger.info("searching for GPA table...");
                        rs = conn.getMetaData().getTables(null, "APP", "%", null);
                        if(!rs.next()){
                            logger.info("it seems that GPA table does not exist. don't worry we are making a new one right now...");
                            statement.execute("CREATE TABLE GPA(Course VARCHAR(255),shana INT,Semester INT,TestGrade INT,Credits DOUBLE,finalGrade INT, PRIMARY KEY(Course))");
                            logger.info("A table has been created");
                        }
                    } catch (SQLException e) {
                        logger.warning("Problem establishing a connection to db and finding 'gpa' table..");
                       throw new DBActionsException("error, problem establishing a connection to db and finding 'gpa' table",e);
                   } finally {
                       if (statement != null) try {
                            statement.close();
                       } catch (SQLException e) {
                           logger.warning("Problem closing statement..");
                           e.printStackTrace();
                        }
                       if (rs != null) try {
                            rs.close();
                        } catch (SQLException e) {
                           logger.warning("Problem closing ResultSet..");
                            e.printStackTrace();
                        }
                    }
                }//the second IF statement ends here
                else{
                    logger.info("error,two threads have managed to get into the synchronized block");
                }
            }
        }
        return conn;
    }
}