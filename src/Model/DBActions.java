
package Model;

import ViewModel.CourseDetails;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DBActions {

    private Statement statement;//eager-instantiation since we will need it null anyways since we build the statement up.
    private ResultSet rs;//same as statement
    private Connection conn;
    private  Logger logger;


    public DBActions() {
        statement = null;
        rs = null;
        conn = null;
        logger=Logger.getLogger(DBActions.class.getName());
    }
    //this func receives all of the params of the db table and and updates it with insert statement
    public void addGrade(String course,int year, int semester, int testGrade, double credit, int finalGrade) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {

            statement = conn.createStatement();
            logger.info("adding new grade...");
            statement.execute("INSERT INTO GPA VALUES ('" + course + "'," + year + "," +  + semester + "," + testGrade + "," + credit + "," + finalGrade + ")");

        } catch (Exception e) {
            logger.warning(e.getMessage());
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }

    }
    //func receives key_value ->course and deletes specific row which corresponds with this value
    public void deleteGrade(String coursename) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            logger.info("deleting grade...");
            statement.executeUpdate("DELETE FROM GPA WHERE course =('" + coursename + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
    }

    //func receives all of the table params and updates the row which corresponds with the key value->course
    public void editGrade(String course, int year, int semester, int testGrade, double credit, int finalGrade) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            logger.info("updating grade...");
            statement.executeUpdate("UPDATE GPA SET (shana= '" + year + "' ,semester= '" + semester + "' ,testGrade=" + testGrade + ",credit=" + credit + " ,finalGrade=" + finalGrade + ") where course='" + course + "'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
    }

    //func connects and prints db table
    public ArrayList<CourseDetails> getGradeTable () throws SQLException {
        ArrayList<CourseDetails> courseDetails=new ArrayList<>();
        conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            logger.info("retrieving grades table...");
            rs = statement.executeQuery("SELECT * FROM gpa");
            logger.info("printing grades table...");
            //rs.next();
            while (rs.next()) {
                courseDetails.add(new CourseDetails(rs.getString("course"),rs.getInt("shana"),rs.getInt("semester"),rs.getInt("testGrade")
                        ,rs.getDouble("credits"),rs.getInt("finalGrade")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
        return courseDetails;
    }
    //func closes all the connection params(we use it at the end of any method which is in this class)
    private void resetStatementAndRS() {
    logger.info("closing connection to db...");
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

    public Integer getFinalGrade(String coursename) throws SQLException {
    Integer grade=0;
    conn=DBconnection.getDBConnection();
    try {
        statement = conn.createStatement();
        rs = statement.executeQuery("SELECT finalGrade FROM GPA WHERE course=('" + coursename + "')");
        rs.next();
        grade= rs.getInt("finalGrade");
    }catch (Exception e){
        e.printStackTrace();
    }finally {
        resetStatementAndRS();
    }
    return  grade;
    }

    public Double getCredit(String coursename) throws SQLException {
        Double credit=0.0;
        conn=DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT credits FROM GPA WHERE course=('" + coursename + "')");
            rs.next();
            credit= rs.getDouble("credits");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            resetStatementAndRS();
        }
        return credit;

    }



    //func retrieves the values of the column finalGrade from the db and returns it in an array(for gpa calculation)
        public List<Integer> getGradesList() throws SQLException {
            List testGrades = new ArrayList();
            conn = DBconnection.getDBConnection();
            try {
                statement = conn.createStatement();
                logger.info("retrieving final grades list...");
                rs = statement.executeQuery("SELECT finalGrade FROM GPA");
                while (rs.next()) {
                    testGrades.add(rs.getInt("finalGrade"));
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                resetStatementAndRS();
            }
            return testGrades;
        }
    //func retrieves the values of the column credits from the db and returns it in an array(for gpa calculation)
    public List<Double> getCreditsList() throws SQLException {
        List credits = new ArrayList();
        Connection conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            logger.info("retrieving credits list...");
            rs = statement.executeQuery("SELECT credits FROM GPA");
            while (rs.next()) {
                credits.add(rs.getDouble("credits"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
        return credits;
    }


}



