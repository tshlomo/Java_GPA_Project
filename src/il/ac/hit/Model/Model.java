/**
 * The package <code>Model</code> is in charged on the heavy work, calculations and DB connections
 */
package il.ac.hit.Model;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Interfaces.IDBSimpleActions;
import il.ac.hit.ViewModel.CourseDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * This class holds the methods which will be interacting with the DB
 * giving the program the ability to retrieve/update information in the DB and do the "heavy" work
 * @see IDBSimpleActions the interface implemented within this class
 */
public class Model implements IDBSimpleActions {

    //announcing the class fields
    private Statement statement;
    private ResultSet rs;
    private Connection conn;
    private Logger logger;

    /**
     * the constructor for this class - sets all it's fields to null and instantiating the logger
     */
    public Model() {
        statement = null;
        rs = null;
        conn = null;
        logger=Logger.getLogger(Model.class.getName());
    }

    /**
     * adds <code>CourseDetails</code> object's values to the gpa table in the db.
     * The <code>CourseDetails</code> argument was instantiated before and must hold all correct (not null) values.
     * <p>
     * This method inserts object values via insert statement.
     *
     * @param  courseDetails  an instantiated <code>CourseDetails</code> object holding the values of a course
     * @throws DBActionsException if an sql exception occurred
     * @see  DbConnection#getDBConnection() uses this method to connect to db
     * @see CourseDetails
     */
    //this func receives all of the params of the course and and updates it with insert statement
    @Override
    public void addGrade(CourseDetails courseDetails) throws DBActionsException {
        try {
            conn = DbConnection.getDBConnection();

            statement = conn.createStatement();
            logger.info("adding new grade...");
            statement.execute("INSERT INTO GPA VALUES ('" + courseDetails.getCourseName()
                    + "'," + courseDetails.getYear()
                    + "," + courseDetails.getSemester()
                    + "," + courseDetails.getTestGrade()
                    + "," + courseDetails.getCredits()
                    + "," + courseDetails.getFinalGrade() + ")");

            logger.info("grade has been successfully added");

        } catch (SQLException e) {
            logger.warning("Problem adding a grade..");
            throw new DBActionsException("problem adding grade " + e.getMessage(),e);
        } finally {
            resetStatementAndRS();
        }
    }

    /**
     * deletes specific course from db table
     * The <code>String</code> <code>courseName</code> must hold a correct name of the specific course we intend to delete.
     * <p>
     * func establishes connection to db
     * func deletes row from table via executeUpdate statement
     *
     * @param  courseName  correct name of the course that would be deleted
     * @throws DBActionsException if an sql exception occurred
     * @see  DbConnection#getDBConnection() uses this method to connect to db
     */
    //func receives key_value ->course and deletes specific row which corresponds with this value
    @Override
    public void deleteGrade(String courseName) throws DBActionsException {
        try {
            conn = DbConnection.getDBConnection();
            statement = conn.createStatement();
            logger.info("deleting grade...");
            statement.executeUpdate("DELETE FROM GPA WHERE course =('" + courseName + "')");
            logger.info("grade has been successfully deleted");
        } catch (SQLException e) {
            logger.warning("Problem deleting a grade..");
            throw new DBActionsException("problem deleting grade",e);
        } finally {
            resetStatementAndRS();
        }
    }

    /**
     * updates grades of a specific course in table
     * The <code>CourseDetails</code> argument was instantiated before and must hold all correct (not null) values.
     * <p>
     * func establishes connection to db
     * func updates a specific course grades from table with new values via <code>executeUpdate</code> statement
     *
     * @param  courseDetails  an instantiated <code>CourseDetails</code> object holding the values of a course
     * @throws DBActionsException if an sql exception occurred
     * @see  DbConnection#getDBConnection() uses this method to connect to db
     * @see CourseDetails
     */
    //func receives all of the course params and updates the row which corresponds with the key value->course
    @Override
    public void editGrade(CourseDetails courseDetails) throws DBActionsException {
        try {
            conn = DbConnection.getDBConnection();
            statement = conn.createStatement();
            logger.info("updating grade...");
            statement.executeUpdate("UPDATE GPA SET shana=" + courseDetails.getYear()
                    + " ,semester=" + courseDetails.getSemester()
                    + " ,testGrade=" + courseDetails.getTestGrade()
                    + " ,credits=" + courseDetails.getCredits()
                    + " ,finalGrade=" + courseDetails.getFinalGrade()
                    + " WHERE course='" + courseDetails.getCourseName() + "'");
            logger.info("grade has been successfully updated");
        } catch (SQLException e) {
            logger.warning("Problem updating a grade..");
            throw new DBActionsException("problem updating grade",e);
        } finally {
            resetStatementAndRS();
        }
    }

    /**
     * returns a <code>CourseDetails</code> type list of all courses in table.
     * <p>
     * func establishes connection to db
     * retrieves course values from table via query into the <code>CourseDetails</code> object instantiation in the loop
     *
     * @throws DBActionsException if an sql exception occurred
     * @see  DbConnection#getDBConnection() uses this method to connect to db
     * @see CourseDetails
     * @see List
     * @return a <code>List</code> of all courses values
     */
    //The function returns a list of courseDetails with all the courses and parameters that the db holds
    @Override
    public List<CourseDetails> getGradeTable () throws DBActionsException{
        List<CourseDetails> courseDetails=new ArrayList<>();
        try {
            conn = DbConnection.getDBConnection();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            logger.info("retrieving grades table...");
            rs = statement.executeQuery("SELECT * FROM GPA ORDER BY course");
            while (rs.next()) {
                courseDetails.add(new CourseDetails(rs.getString("course"),rs.getInt("shana"),rs.getInt("semester"),rs.getInt("testGrade")
                        ,rs.getDouble("credits"),rs.getInt("finalGrade")));
            }
        } catch (SQLException e) {
            logger.warning("Problem getting grades table..");
            throw new DBActionsException("problem getting grades table from db",e);
        } finally {
            resetStatementAndRS();
        }
        return courseDetails;
    }

    /**
     * returns a specific course from db table
     * The <code>String</code> <code>courseName</code> must hold a correct name of the specific course we intend to retrieve.
     * <p>
     * func establishes connection to db
     * func retrieves a row from table via query with the corresponding courseName and
     * instantiates a CourseDetails object with the retrieved values.
     *
     * @param  courseName  correct name of the course that would be deleted
     * @throws DBActionsException if an sql exception occurred
     * @see  DbConnection#getDBConnection() uses this method to connect to db
     * @see CourseDetails
     * @return values of a specific course
     */
    //func returns a specific course from table by the courseName it gets
    public CourseDetails getCourse (String courseName) throws DBActionsException{
        try {
            conn = DbConnection.getDBConnection();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            logger.info("retrieving course...");
            rs = statement.executeQuery("SELECT * FROM GPA WHERE course=('"+courseName+"')");
            while (rs.next()) {
                return new CourseDetails(rs.getString("course"),rs.getInt("shana")
                        ,rs.getInt("semester"),rs.getInt("testGrade")
                        ,rs.getDouble("credits"),rs.getInt("finalGrade"));
            }
        } catch (SQLException e) {
            logger.warning("Problem getting course from table..");
            throw new DBActionsException("problem getting course from table",e);
        } finally {
            resetStatementAndRS();
        }
        return null;
    }


    /**
     * closes all params which are used to connect and work with DB and sets them to null.
     * this func is called at the end of any method which connects to DB
     *
     */
    //Private func - closes all the connection params(we use it at the end of any method which is in this class)
    private void resetStatementAndRS() {
        if (statement != null) try {
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (rs != null) try {
            rs.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        conn = null;
        rs = null;
        statement = null;
    }


}



