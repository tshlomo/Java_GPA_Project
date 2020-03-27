package Model;

import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class Caluclations {

    //func calculates and returns final grade based on final test score and percentage + hw score and percentage
    public Integer calculate_Final_Grade(Integer testGrade, Integer testPercent, Integer hwGrade, Integer hwPercent){
        Integer score= testGrade*(testPercent/100)+hwGrade*(hwPercent/100);
        return score;
    }


    //func receives grades array + correspondent credits array and calculates gpa
    public Integer calculate_GPA() throws SQLException {
        Double credits_sum=0.0;
        Double grades_sum=0.0;
        Integer i=0;
        DBActions dbActions = new DBActions();
        List<Integer> grades = dbActions.getFinalGrades();
        List<Double>credits=dbActions.getCredits();

        for (Integer x:grades) {
            grades_sum+=x*credits.get(i);
            credits_sum+=credits.get(i);
            i++;
        }
        return (int) (grades_sum/credits_sum);
    }
}

