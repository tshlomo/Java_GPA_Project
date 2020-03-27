
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBActions {

    protected static Statement statement;//eager-instantiation since we will need it null anyways since we build the statement up.
    protected static ResultSet rs;//same as statement
    private static Connection conn;

    private DBActions() {
        statement = null;
        rs = null;
        conn = null;
    }
    //this func receives all of the params of the db table and and updates it with insert statement
    public static void addGrade(String course, String year, String semester, int testGrade, double credit, int finalGrade) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {

            statement = conn.createStatement();
            statement.execute("insert into gpa values ('" + course + "','" + year + "','" + semester + "'," + testGrade + "," + credit + "," + finalGrade + ")");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }

    }
    //func receives key_value ->course and deletes specific row which corresponds with this value
    public static void deleteGrade(String coursename) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            statement.executeUpdate("delete from gpa where course =('" + coursename + "')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
    }

    //func receives all of the table params and updates the row which corresponds with the key value->course
    public static void editGrade(String course, String year, String semester, int testGrade, double credit, int finalGrade) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {

            statement = conn.createStatement();
            statement.executeUpdate("update gpa set (year1='" + year + "',semester= '" + semester + "' ,testGrade=" + testGrade + ",credit=" + credit + " ,finalGrade=" + finalGrade + ") where course='" + course + "'");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
    }

        //func connects and prints db table
        public static void printTable () throws SQLException {
            conn = DBconnection.getDBConnection();
            try {
                statement = conn.createStatement();
                rs = statement.executeQuery("SELECT * from gpa");
                while (rs.next()) {
                    System.out.println("course= " + rs.getString("course") + " year= " + rs.getString("year1") + " semester= " + rs.getString("semester") + " testGrade= " + rs.getInt("testGrade") + " credits= " + rs.getDouble("credits") + " finalGrade= " + rs.getInt("finalGrade"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                resetStatementAndRS();
            }
        }
        //func closes all the connection params(we use it at the end of any method which int this class)
        public static void resetStatementAndRS() {
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
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            conn = null;
            rs = null;
            statement = null;
        }
        //func retrieves the values of the column finalGrade from the db and returns it via Arraylist(for gpa calculation)
        public static List<Integer> getFinalGrades() throws SQLException {
            ArrayList testGrades = new ArrayList();
            int i=0;
            Connection conn = DBconnection.getDBConnection();
            try {
                statement = conn.createStatement();
                rs = statement.executeQuery("SELECT finalGrade FROM gpa");
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
    public static List<Double> getCredits() throws SQLException {
        ArrayList credits = new ArrayList();
        int i=0;
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



