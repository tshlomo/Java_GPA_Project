
package Model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;

public class DBActions {

    public static void add_grade(String course, String year, String semester, int testGrade, double credit, int finalGrade) throws SQLException {
        Connection conn = DBconnection.GetDBConnection();
        Statement statement = null;
        try {

            statement = conn.createStatement();
            statement.execute("insert into gpa values ('" + course + "','" + year + "','" + semester + "'," + testGrade + "," + credit + "," + finalGrade + ")");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("connection not closed");
            }


        }
    }

    public static void deleteGrade(String coursename) throws SQLException {
        Connection conn = DBconnection.GetDBConnection();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            // need to check if this quary works!!!//
            statement.executeUpdate("delete from gpa where course =('"+coursename+"')");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                conn.close();
            } catch (Exception e) {
                System.out.println("connection not closed");
            }


        }
    }

    public static int[] getAllGrades() throws SQLException {
         int[]grades={0};
        Statement statement = null;
        ResultSet rs = null;
        Connection conn = DBconnection.GetDBConnection();
        try {
            statement = conn.createStatement();
            rs=statement.executeQuery("SELECT COUNT(*) FROM gpa");
            rs = statement.executeQuery("select finalGrade from gpa");
            int i = 0;
            while (rs.next()) {
                grades[i]=rs.getInt("finalGrade");
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
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return grades;
    }

    public static void printTable() throws SQLException {
        Connection conn = DBconnection.GetDBConnection();
        Statement statement = null;
        ResultSet rs = null;
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT * from gpa");
            while (rs.next()) {
                System.out.println("course= " + rs.getString("course") + " year= " + rs.getString("year1") + " semester= " + rs.getString("semester") + " testGrade= " + rs.getInt("testGrade") + " credits= " + rs.getDouble("credits") + " finalGrade= " + rs.getInt("finalGrade"));
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
            if (conn != null) try {
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}



