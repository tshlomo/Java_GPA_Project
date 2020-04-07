package il.ac.hit.Interfaces;

import il.ac.hit.ViewModel.CourseDetails;
import il.ac.hit.View.TableHomeFrame;

import java.util.List;

/**
 * <code>IViewSimpleActions</code> interface in an extension for <code>ISimpleActions</code> interface and is adding up
 * upon it another functions to update the course table and the GPA label within the user UI
 * @see ISimpleActions
 * @see TableHomeFrame the class that implements this interface
 */
public interface IViewSimpleActions extends ISimpleActions {
    /**
     * <code>updateGradesTable</code> function get a list of <code>CourseDetails</code> to be showed in the user UI
     * and updates accordingly
     * @param courseDetails the <code>CourseDetails</code> list to be inserted to the table
     * @see CourseDetails
     * @see List
     */
    void updateGradesTable(List<CourseDetails> courseDetails);

    /**
     * <cod>updateGPA</cod> function updates the user UI with the <code>newGPA</code> calculated after an
     * action performed by the user
     * @param newGPA    the new GPA after the formula
     */
    void updateGPA(Double newGPA);
}
