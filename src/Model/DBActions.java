
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBActions {

    private Statement statement;//eager-instantiation since we will need it null anyways since we build the statement up.
    private ResultSet rs;//same as statement
    private Connection conn;

    public DBActions() {
        statement = null;
        rs = null;
        conn = null;
    }
    //this func receives all of the params of the db table and and updates it with insert statement
    public void addGrade(String course, String semester, int testGrade, double credit, int finalGrade) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {

            statement = conn.createStatement();
            statement.execute("MERGE INTO GPA as g1" +
                    "USING GPA as g2" +
                    " ON g1.Course = g2.Course " +
                    "WHEN NOT MATCHED THEN INSERT VALUES ('" + course + "','" + semester + "'," + testGrade + "," + credit + "," + finalGrade + ")");
            //statement.execute("INSERT INTO GPA VALUES ('" + course + "','" + semester + "'," + testGrade + "," + credit + "," + finalGrade + ")");


        } catch (Exception e) {
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
            statement.executeUpdate("DELETE FROM GPA WHERE course =('" + coursename + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
    }

    //func receives all of the table params and updates the row which corresponds with the key value->course
    public void editGrade(String course, String semester, int testGrade, double credit, int finalGrade) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {

            statement = conn.createStatement();
            statement.executeUpdate("UPDATE GPA SET (semester= '" + semester + "' ,testGrade=" + testGrade + ",credit=" + credit + " ,finalGrade=" + finalGrade + ") where course='" + course + "'");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
    }

        //func connects and prints db table
        public void printTable () throws SQLException {
            conn = DBconnection.getDBConnection();
            try {
                statement = conn.createStatement();
                rs = statement.executeQuery("SELECT * FROM gpa");
                while (rs.next()) {
                    System.out.println("course= " + rs.getString("course") + " semester= " + rs.getString("semester") + " testGrade= " + rs.getInt("testGrade") + " credits= " + rs.getDouble("credits") + " finalGrade= " + rs.getInt("finalGrade"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                resetStatementAndRS();
            }
        }
        //func closes all the connection params(we use it at the end of any method which int this class)
        public void resetStatementAndRS() {
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
        //func retrieves the values of the column finalGrade from the db and returns it via Arraylist(for gpa calculation)
        public List<Integer> getFinalGrades() throws SQLException {
            List testGrades = new ArrayList();
            conn = DBconnection.getDBConnection();
            try {
                statement = conn.createStatement();
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
    //func retrieves the values of the column credits from the db and returns it via Arraylist(for gpa calculation)
    public List<Double> getCredits() throws SQLException {
        List credits = new ArrayList();
        Connection conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT finalGrade,credits FROM gpa");
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



