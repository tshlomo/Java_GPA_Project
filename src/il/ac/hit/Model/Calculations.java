package il.ac.hit.Model;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Interfaces.ICalcGPA;
import il.ac.hit.Interfaces.IDBSimpleActions;
import il.ac.hit.ViewModel.CourseDetails;

import java.util.List;
import java.util.logging.Logger;

public class Calculations implements ICalcGPA {

    //Setting the logger for this class
    private Logger logger= Logger.getLogger(Calculations.class.getName());
    private IDBSimpleActions dbActions;

    public Calculations(){
        dbActions = new Model();
    }

    /**
     * calculates and returns the final grade of a course.
     * func receives params from the add grade panel by the user
     * <p>
     * the calculation of a final grade of every course is based on the scores of its final test and the hw/mid-test .
     * each score mentioned has its own affect on the final grade based on its percentage.
     * the calculation of a final grade is the sum of every score multiplied by its percentage and divided by 100
     *
     * @param testGrade   final test score
     * @param testPercent final test percentage
     * @param hwGrade     hw/mid-test score
     * @param hwPercent   hw/mid-test percentage
     * @return  final grade
     */

    //Func calculates and returns final grade based on final test score and percentage + hw score and percentage
    public Integer calculate_Final_Grade(Double testGrade, Double testPercent, Double hwGrade, Double hwPercent){
        logger.info("calculating final grade based on test and hw percentage...");
        Double score = (testGrade*(testPercent/100))+(hwGrade*(hwPercent/100));
        return score.intValue();
    }

    /**
     * Calculates and returns the GPA of the grades table.
     * The func receives params from the add grade panel by the user
     * <p>
     * func gets the details list of the table via the getGradeTable method.
     * it calculates the sum of all the final grades multiplied by their specific credits value
     * than the sum is divided by the sum of the credits in total(hence the credit_sum param)
     *
     * @throws DBActionsException
     * @see  Model#getGradeTable()  uses this method to retrieve the values of all the courses in order to calculate the gpa
     * @return  calculated total grade gpa
     */


    //Func calculates gpa of the grades table in db
    @Override
    public Double calculate_GPA() throws DBActionsException {
        List<CourseDetails> courseTable;
        Double credits_sum=0.0;
        Double grades_sum=0.0;
        //getting the values from db table
        courseTable=dbActions.getGradeTable();
        logger.info("calculating gpa...");
        // gpa calculation formula is = (sum of all grades multiplied by their credits)/(sum of credits)
        for (CourseDetails courseDetails: courseTable){
            grades_sum+= (courseDetails.getFinalGrade())*(courseDetails.getCredits());
            credits_sum+=courseDetails.getCredits();
        }
        return (grades_sum/credits_sum);
    }

    /**
     * calculates and returns updated gpa after changing a specific grade.
     * func receives name of the course which its final grade will be changed and the new grade
     * courseName needs to be correct name of the course and grade between 0-100(this is final grade already)
     * this func gives the user the opportunity to check how his gpa will change by entering a different grade
     * <p>
     * func gets the details list of the table via the getGradeTable method.
     * func get details of the specific course it needs to change via the getCourse method
     * it calculates the sum of all the final grades multiplied by their specific credits value
     * it subtracts the present course grade multiplied by its credits and than adds the new course grade multiplied by the same credits
     * than the sum is divided by the sum of the credits in total(hence the credit_sum param)
     *
     * @param courseName correct name of the course that its grade would be replaced
     * @param newGrade new grade entered by the user to replace the precent grade in the calculation
     * @throws DBActionsException
     * @see  Model#getCourse(String)  uses this method to retrieve the details of the course we want to change
     * @see  Model#getGradeTable()  uses this method to retrieve the values of all the courses in order to calculate the gpa
     * @return  calculated total grade gpa
     */

    //Calculates new gpa by replacing one of the grades with a new one entered by the user func receives the name of the course and the new grade
    @Override
    public Double gpaByGrade(String courseName,Integer newGrade) throws  DBActionsException {
        Double credits_sum = 0.0;
        Double grades_sum = 0.0;
        List<CourseDetails> courseTable;
        courseTable=dbActions.getGradeTable();
        //getting specific course details
        CourseDetails course=dbActions.getCourse(courseName);
        //calculating current gpa
        logger.info("calculating gpa...");
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

