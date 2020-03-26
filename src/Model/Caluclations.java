package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Caluclations {

    public static int calcu_final_grade(int testGrade,int testPercent,int hwGrade,int hwPercent){
        int score= testGrade*(testPercent/100)+hwGrade*(hwPercent/100);
        return score;
    }

    public static int calcu_gpa() throws SQLException {
        Statement statement = null;
        ResultSet rs = null;
        double credits_sum = 0;
        double grades_sum = 0;
        Connection conn = DBconnection.GetDBConnection();
        try {
            statement = conn.createStatement();
            rs = statement.executeQuery("SELECT finalGrade,credits FROM gpa");
            while (rs.next()) {
                grades_sum += rs.getInt("finalGrade") * rs.getDouble("credits");
                credits_sum += rs.getDouble("credits");

            }
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
        return (int) (grades_sum / credits_sum);
    }
    }

