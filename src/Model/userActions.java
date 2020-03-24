
package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class userActions implements ServerActions {
    @Override
    public void add_grade(String course, String semester, int testGrade, double credit, int finalGrade) throws SQLException {
        Connection conn = DBconnection.GetDBConnection();
        try {
            Statement statement = conn.createStatement();
            statement.executeUpdate("insert into gpa values(course,semester,testGrade,credit,finalGrade)");

        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
    }

    public void deleteGrade(String coursename) throws SQLException {
        Connection conn = DBconnection.GetDBConnection();
        try {
            Statement statement = conn.createStatement();
            // need to check if this quary works!!!//
            statement.executeUpdate("delete from gpa where course=coursename");
        } catch (Exception e) {
            e.printStackTrace();
        }
        conn.close();
    }

    public int[] getAllGrades() throws SQLException {
        Connection conn = DBconnection.GetDBConnection();
        try {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery("select finalGrade from gpa");
            int i = 0;
            int[] grades = {0};
            while (rs.next()) {
                grades[i] = rs.getInt(finalGrade);
                i++;
            }

        }
        catch (Exception e){
            e.printStackTrace();
        }
        return grades;
        conn.close();
    }

}



