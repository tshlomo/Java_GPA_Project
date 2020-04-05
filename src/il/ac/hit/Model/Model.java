
package il.ac.hit.Model;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Interfaces.ISimpleActions;
import il.ac.hit.ViewModel.CourseDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Model implements ISimpleActions {

    private Statement statement;//eager-instantiation since we will need it null anyways since we build the statement up.
    private ResultSet rs;//same as statement
    private Connection conn;
    private  Logger logger;


    public Model() {
        statement = null;
        rs = null;
        conn = null;
        logger=Logger.getLogger(Model.class.getName());
    }

    //this func receives all of the params of the db table and and updates it with insert statement
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

    //func receives all of the table params and updates the row which corresponds with the key value->course
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

    public CourseDetails getCourse (String courseName) throws DBActionsException{
        //CourseDetails course = null;
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
    //func closes all the connection params(we use it at the end of any method which is in this class)
    private void resetStatementAndRS() {
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
        conn = null;
        rs = null;
        statement = null;
    }


}



