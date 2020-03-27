package Model;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Caluclations {

    //func calculates and returns final grade based on final test score and percentage + hw score and percentage
    public static int calcu_final_grade(int testGrade,int testPercent,int hwGrade,int hwPercent){
        int score= testGrade*(testPercent/100)+hwGrade*(hwPercent/100);
        return score;
    }


    //func receives grades array + correspondent credits array and calculates gpa
public static int calcu_gpa() throws SQLException {
        double credits_sum=0;
        double grades_sum=0;
        int i=0;
    List<Integer> grades = DBActions.getFinalGrades();
    List<Double>credits=DBActions.getCredits();
    for (Integer x:grades) {
        grades_sum+=x*credits.get(i);
        credits_sum+=credits.get(i);
    }
    return (int)(grades_sum/credits_sum);
}
    }

