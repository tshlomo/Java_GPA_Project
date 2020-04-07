package il.ac.hit.ViewModel;

import il.ac.hit.Interfaces.*;
import il.ac.hit.Model.Calculations;
import il.ac.hit.Model.Model;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.View.TableHomeFrame;

/**
 * in charge of communication between the methods in the view and the methods in the model
 * implements the <code>IFindNewGPA</code> interface
 * @see IFindNewGPA the interface used in this class
 */
public class ViewModel implements IFindNewGPA {

    private IDBSimpleActions dbActions;
    private IViewSimpleActions tableHomeFrame;
    private ICalcGPA calculations;

    /**
     * constructor for the ViewModel class
     * instantiates new Model and Calculations for db and calculation purposes
     *
     * @param tableHomeFrame gets the frame using the interface <code>IViewSimpleActions</code>
     */

    public ViewModel(TableHomeFrame tableHomeFrame){
        dbActions = new Model();
        this.tableHomeFrame = tableHomeFrame;
        calculations = new Calculations();
    }

    /**
     * sends the new calculated gpa value to the view
     *
     * @param calculate_gpa calculated gpa value
     */

    //The func is sending to the view the new GPA value to update
    private void updateGPA(Double calculate_gpa) {
        tableHomeFrame.updateGPA(calculate_gpa);
    }

    /**
     * gets all the values from the db and updates it
     */

    //this func receives all of the params of the db table and and updates it with insert statement
    public void updateTable() throws DBActionsException {
        updateGPA(calculations.calculate_GPA());
        tableHomeFrame.updateGradesTable(dbActions.getGradeTable());
    }

    /**
     * calls the <code>addGrade</code> method from the <code>Model</code>
     * edits grade in case of <b>duplication</b> of courses names.
     * updates the table at the end of the action
     *
     * @param  courseDetails  an instantiated CourseDetails object holding the values of a course
     * @throws DBActionsException if an sql exception occurred
     * @see CourseDetails
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
     * calls the delete method from the <code>Model</code>
     * updates the table at the end of the action
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
     * calls the editGrade method from the Model
     * updates the table
     *
     * @param  courseDetails  an instantiated CourseDetails object holding the values of a course
     * @throws DBActionsException if an sql exception occurred
     * @see Model
     * @see CourseDetails
     */
    //func receives all of the table params and updates the row which corresponds with the key value->course
    @Override
    public void editGrade(CourseDetails courseDetails) throws DBActionsException{
        dbActions.editGrade(courseDetails);
    }

    /**
     * calls the <code>gpaByGrade</code> method from the <code>Calculations</code>

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
