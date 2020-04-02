package Model;

import ViewModel.CourseDetails;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.logging.Logger;

public class Calculations {

    private Logger logger= Logger.getLogger(Calculations.class.getName());

    //func calculates and returns final grade based on final test score and percentage + hw score and percentage
    public Integer calculate_Final_Grade(Double testGrade, Double testPercent, Double hwGrade, Double hwPercent){
        logger.info("calculating final grade based on test and hw percentage...");
        Double score = (testGrade*(testPercent/100))+(hwGrade*(hwPercent/100));
        return score.intValue();
    }


    //func receives grades array + correspondent credits array and calculates gpa
    public Double calculate_GPA() throws DBActionsException {
        DBActions dbActions = new DBActions();
        ArrayList<CourseDetails> courseTable;
        Double credits_sum=0.0;
        Double grades_sum=0.0;
        Integer i = 0;
        //getting the values from db table
        courseTable=dbActions.getGradeTable();
        // gpa calculation formula is = (sum of all grades multiplied by their credits)/(sum of credits)
        while (i<courseTable.size()){
            grades_sum+= (courseTable.get(i).getFinalGrade())*(courseTable.get(i).getCredits());
            credits_sum+=courseTable.get(i).getCredits();
            i++;
        }


        return (grades_sum/credits_sum);
    }


    public Double gpaByGrade(String coursename,Integer newGrade) throws  DBActionsException {
        Double credits_sum = 0.0;
        Double grades_sum = 0.0;
        Integer i=0;
        ArrayList<CourseDetails> courseTable;
        DBActions dbActions = new DBActions();
        courseTable=dbActions.getGradeTable();
        //getting specific course details
        CourseDetails course=dbActions.getCourse(coursename);
        //calculating current gpa
        while (i<courseTable.size()){
            grades_sum+= (courseTable.get(i).getFinalGrade())*(courseTable.get(i).getCredits());
            credits_sum+=courseTable.get(i).getCredits();
            i++;
        }
        //removing old grade from calculation
        grades_sum-=(course.getCredits())*(course.getFinalGrade());
        //adding new grade to calculation
        grades_sum+=(newGrade)*(course.getCredits());
        return (grades_sum/credits_sum);

    }

}

