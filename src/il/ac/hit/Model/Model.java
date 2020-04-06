
package il.ac.hit.Model;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Interfaces.IDBSimpleActions;
import il.ac.hit.ViewModel.CourseDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Model implements IDBSimpleActions {

    /**
     * declaring class params which will be used to connecet and work with the db
     * each one of those params will be closed and nulled at the end of each method
     * +logger
     */

    private Statement statement;//eager-instantiation since we will need it null anyways since we build the statement up.
    private ResultSet rs;//same as statement
    private Connection conn;
    private Logger logger;


    public Model() {
        statement = null;
        rs = null;
        conn = null;
        logger=Logger.getLogger(Model.class.getName());
    }

    /**
     * adds CourseDetails object's values to the gpa table in the db.
     * The CourseDetails argument was instantiated before and must hold all correct (not null) values.
     * <p>
     * This method inserts object values via insert statement.
     *
     * @param  courseDetails  an instantiated CourseDetails object holding the values of a course
     * @throws DBActionsException if an sql exception occurred
     * @see  DBconnection#getDBConnection() uses this method to connect to db
     */

    //this func receives all of the params of the course and and updates it with insert statement
    @Override
    public void addGrade(CourseDetails courseDetails) throws DBActionsException {
        try {
            conn = DBconnection.getDBConnection();

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
            throw new DBActionsException("problem adding grade " + e.getMessage(),e);
        } finally {
            resetStatementAndRS();
        }
    }

        /**
         * deletes specific course from db table
         * The String coursename must hold a correct name of the specific course we intend to delete.
         * <p>
         * func establishes connection to db
         * func deletes row from table via executeUpdate statement
         *
         * @param  coursename  correct name of the course that would be deleted
         * @throws DBActionsException if an sql exception occurred
         * @see  DBconnection#getDBConnection() uses this method to connect to db
         */

    //func receives key_value ->course and deletes specific row which corresponds with this value
    @Override
    public void deleteGrade(String courseName) throws DBActionsException {
        try {
            conn = DBconnection.getDBConnection();
            statement = conn.createStatement();
            logger.info("deleting grade...");
            statement.executeUpdate("DELETE FROM GPA WHERE course =('" + courseName + "')");
            logger.info("grade has been successfully deleted");
        } catch (SQLException e) {
            throw new DBActionsException("problem deleting grade",e);
        } finally {
            resetStatementAndRS();
        }
    }

    /**
     * updates grades of a specific course in table
     * The CourseDetails argument was instantiated before and must hold all correct (not null) values.
     * <p>
     * func establishes connection to db
     * func updates a specific course grades from table with new values via executeUpdate statement
     *
     * @param  courseDetails  an instantiated CourseDetails object holding the values of a course
     * @throws DBActionsException if an sql exception occurred
     * @see  DBconnection#getDBConnection() uses this method to connect to db
     */

    //func receives all of the course params and updates the row which corresponds with the key value->course
    @Override
    public void editGrade(CourseDetails courseDetails) throws DBActionsException {
        try {
            conn = DBconnection.getDBConnection();
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
            throw new DBActionsException("problem updating grade",e);
        } finally {
            resetStatementAndRS();
        }
    }

    /**
     * returns a CourseDetails type list of all courses in table.
     * <p>
     * func establishes connection to db
     * retrieves course values from table via query into the CourseDetails object instantiation in the loop
     *
     * @throws DBActionsException if an sql exception occurred
     * @see  DBconnection#getDBConnection() uses this method to connect to db
     * @return list of all courses values
     */
    //The function returns a list of courseDetails with all the courses and parameters that the db holds
    @Override
    public List<CourseDetails> getGradeTable () throws DBActionsException{
        List<CourseDetails> courseDetails=new ArrayList<>();
        try {
            conn = DBconnection.getDBConnection();
            statement = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_READ_ONLY);
            logger.info("retrieving grades table...");
            rs = statement.executeQuery("SELECT * FROM GPA ORDER BY course");
            while (rs.next()) {
                courseDetails.add(new CourseDetails(rs.getString("course"),rs.getInt("shana"),rs.getInt("semester"),rs.getInt("testGrade")
                        ,rs.getDouble("credits"),rs.getInt("finalGrade")));
            }
        } catch (SQLException e) {
            throw new DBActionsException("problem getting grades table from db",e);
        } finally {
            resetStatementAndRS();
        }
        return courseDetails;
    }

    /**
     * returns a specific course from db table
     * The String coursename must hold a correct name of the specific course we intend to retrieve.
     * <p>
     * func establishes connection to db
     * func retrieves a row from table via query with the corresponding courseName and
     * instantiates a CourseDetails object with the retrieved values.
     *
     * @param  courseName  correct name of the course that would be deleted
     * @throws DBActionsException if an sql exception occurred
     * @see  DBconnection#getDBConnection() uses this method to connect to db
     * @return values of a specific course
     */
    //func returns a specific course from table by the courseName it gets
    public CourseDetails getCourse (String courseName) throws DBActionsException{
        try {
            conn = DBconnection.getDBConnection();
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
            throw new DBActionsException("problem getting course from table",e);
        } finally {
            resetStatementAndRS();
        }
        return null;
    }


    /**
     * closes all params which are used to connect and work with db.
     * this func is called at the end of any method which connects to db
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



