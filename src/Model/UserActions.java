
package Model;

import java.sql.*;
import java.util.ArrayList;

public class UserActions {

    protected static Statement statement;//eager-instantiation since we will need it null anyways since we build the statement up.
    protected static ResultSet rs;//same as statement
    private static Connection conn;

    private UserActions()
    {
        statement = null;
        rs = null;
        conn = null;
    }

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

    public static void deleteGrade(String coursename) throws SQLException {
        conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            // need to check if this quary works!!!//
            statement.executeUpdate("delete from gpa where course =('"+coursename+"')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
    }

    public static ArrayList getAllGrades() throws SQLException {
        ArrayList grades = new ArrayList();
        conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            rs=statement.executeQuery("SELECT COUNT(*) FROM gpa");
            rs = statement.executeQuery("select finalGrade from gpa");
            int i = 0;
            while (rs.next()) {
                grades.add(rs.getInt("finalGrade"));
                //maybe need to add i++ sagi???
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resetStatementAndRS();
        }
        return grades;
    }

    public static void printTable() throws SQLException {
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

    public static void resetStatementAndRS()
    {
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
    }
}



