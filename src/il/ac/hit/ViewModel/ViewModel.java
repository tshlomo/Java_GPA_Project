package il.ac.hit.ViewModel;

import il.ac.hit.Interfaces.*;
import il.ac.hit.Model.Calculations;
import il.ac.hit.Model.Model;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.View.TableHomeFrame;

public class ViewModel implements IFindNewGPA {

    private IDBSimpleActions dbActions;
    private IViewSimpleActions tableHomeFrame;
    private ICalcGPA calculations;

    public ViewModel(TableHomeFrame tableHomeFrame){
        dbActions = new Model();
        this.tableHomeFrame = tableHomeFrame;
        calculations = new Calculations();
    }

    //The func is sending to the view the new GPA value to update
    private void updateGPA(Double calculate_gpa) {
        tableHomeFrame.updateGPA(calculate_gpa);
    }

    //this func receives all of the params of the db table and and updates it
    public void updateTable() throws DBActionsException {
        updateGPA(calculations.calculate_GPA());
        tableHomeFrame.updateGradesTable(dbActions.getGradeTable());
    }

    /**
     * adds CourseDetails object's values to the gpa table in the db.
     * The CourseDetails argument was instantiated before and must hold all correct (not null) values.
     * <p>
     * This method inserts object values via insert statement.
     *
     * @param  courseDetails  an instantiated CourseDetails object holding the values of a course
     * @throws DBActionsException if an sql exception occurred
     */

    //The function trying to add a grade based on the values inside the courseDetails var
    @Override
    public void addGrade(CourseDetails courseDetails) throws DBActionsException
    {
        try {
            dbActions.addGrade(courseDetails);
        } catch (DBActionsException e) {
            //logger message
            tableHomeFrame.editGrade(courseDetails);
        } finally {
            updateTable();
        }
    }

    /**
     * deletes specific course from db table
     * The String coursename must hold a correct name of the specific course we intend to delete.
     * <p>
     * func establishes connection to db
     * func deletes row from table via executeUpdate statement
     *
     * @param  courseName  correct name of the course that would be deleted
     * @throws DBActionsException if an sql exception occurred
     * @see Model
     */

    //func receives key_value ->course and deletes specific row which corresponds with this value
    @Override
    public void deleteGrade(String courseName) throws DBActionsException {
        if(courseName.equals("updateTable"))
            updateTable();
        else {
            dbActions.deleteGrade(courseName);
            updateTable();
        }
    }

    /**
     * updates grades of a specific course in table
     * The CourseDetails argument was instantiated before and must hold all correct (not null) values.
     * <p>
     * func establishes connection to db
     * func updates a specific course grades from table with new values via executeUpdate statement
     *
     * @param  courseDetails  an instantiated CourseDetails object holding the values of a course
     * @throws DBActionsException if an sql exception occurred
     * @see Model
     */

    //func receives all of the table params and updates the row which corresponds with the key value->course
    @Override
    public void editGrade(CourseDetails courseDetails) throws DBActionsException{
        dbActions.editGrade(courseDetails);
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
     * @throws DBActionsException if an sql exception occurred
     * @see Calculations - class with the gpa calculation methods
     * @return  calculated total grade gpa
     */

    //func calculates new gpa by replacing one of the grades with a new one entered by the user func receives the name of the course and the new grade
    @Override
    public Double newGPA(String courseName, Integer newGrade) throws DBActionsException{
        return calculations.gpaByGrade(courseName,newGrade);
    }
}
