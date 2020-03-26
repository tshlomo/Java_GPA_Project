
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.ConsoleHandler;

public class UserActions  {

    public static void add_grade(String course,String year, String semester, int testGrade, double credit, int finalGrade) throws SQLException {
        Connection conn = DBconnection.GetDBConnection();
        Statement statement = null;
        try {

            statement = conn.createStatement();
            statement.execute("insert into gpa values ("+course+","+year+","+semester+","+testGrade+","+credit+","+finalGrade+")");


        } catch (Exception e) {
            e.printStackTrace();
        }

        finally {

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
        try {
            Statement statement = conn.createStatement();
            // need to check if this quary works!!!//
            statement.executeUpdate("delete from gpa where course=coursename");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            conn.close();
        }catch (Exception e){
            System.out.println("connection not closed");
        }
    }

    public static int[] getAllGrades() throws SQLException {
        int[] grades={0};
        Connection conn = DBconnection.GetDBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select finalGrade from gpa");
            int i = 0;
            while (rs.next()) {
                grades[i] = rs.getInt("finalGrade");
                i++;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        try {
            conn.close();
        }catch (Exception e){
            System.out.println("connection not closed");
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
                System.out.println("course= " + rs.getString("course") + " semester= " + rs.getString("semester") + " testGrade= " + rs.getInt("testGrade") + " credit= " + rs.getDouble("credit") + " finalGrade= " + rs.getInt("finalGrade"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            conn.close();
        }catch (Exception e){
            System.out.println("connection not closed");
        }
    }

}



