package Model;

import java.sql.SQLException;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class Caluclations {

    private Logger logger= Logger.getLogger(Caluclations.class.getName());

    //func calculates and returns final grade based on final test score and percentage + hw score and percentage
    public Integer calculate_Final_Grade(Integer testGrade, Integer testPercent, Integer hwGrade, Integer hwPercent){
        Integer score= testGrade*(testPercent/100)+hwGrade*(hwPercent/100);
        return score;
    }


    //func receives grades array + correspondent credits array and calculates gpa
    public Double calculate_GPA() throws SQLException {
        Double credits_sum=0.0;
        Double grades_sum=0.0;
        Integer i=0;
        DBActions dbActions = new DBActions();
        List<Integer> grades = dbActions.getGradesList();
        List<Double>credits=dbActions.getCreditsList();
        logger.info("calculating gpa...");
        for (Integer x:grades) {
            grades_sum+=x*credits.get(i);
            credits_sum+=credits.get(i);
            i++;
        }
        return (grades_sum/credits_sum);
    }


    public Double GpaByGrade(String coursename,Integer newGrade) throws SQLException {
        Double credits_sum = 0.0;
        Double grades_sum = 0.0;
        Integer oldGrade =0;
        Double credit=0.0;
        Integer i=0;
        DBActions dbActions = new DBActions();
        List<Integer> grades = dbActions.getGradesList();
        List<Double> credits = dbActions.getCreditsList();
        logger.info("calculating new gpa...");
        for (Integer x : grades) {
            grades_sum += x * credits.get(i);
            credits_sum += credits.get(i);
            i++;
        }
        credit=dbActions.getCredit(coursename);
        oldGrade=dbActions.getFinalGrade(coursename);
        grades_sum-=oldGrade*credit;
        grades_sum+=newGrade*credit;
        return (grades_sum/credits_sum);

    }

}

