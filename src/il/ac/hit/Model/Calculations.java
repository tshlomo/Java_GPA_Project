package il.ac.hit.Model;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.ViewModel.CourseDetails;

import java.util.List;
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
        List<CourseDetails> courseTable;
        Double credits_sum=0.0;
        Double grades_sum=0.0;
        //getting the values from db table
        courseTable=dbActions.getGradeTable();
        // gpa calculation formula is = (sum of all grades multiplied by their credits)/(sum of credits)
        for (CourseDetails courseDetails: courseTable){
            grades_sum+= (courseDetails.getFinalGrade())*(courseDetails.getCredits());
            credits_sum+=courseDetails.getCredits();
        }


        return (grades_sum/credits_sum);
    }


    public Double gpaByGrade(String courseName,Integer newGrade) throws  DBActionsException {
        Double credits_sum = 0.0;
        Double grades_sum = 0.0;
        List<CourseDetails> courseTable;
        DBActions dbActions = new DBActions();
        courseTable=dbActions.getGradeTable();
        //getting specific course details
        CourseDetails course=dbActions.getCourse(courseName);
        //calculating current gpa
        for (CourseDetails courseDetails: courseTable){
            grades_sum+= (courseDetails.getFinalGrade())*(courseDetails.getCredits());
            credits_sum+=courseDetails.getCredits();
        }
        //removing old grade from calculation
        grades_sum-=(course.getCredits())*(course.getFinalGrade());
        //adding new grade to calculation
        grades_sum+=(newGrade)*(course.getCredits());
        return (grades_sum/credits_sum);

    }

}

