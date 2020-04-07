package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;

/**
 * <code>IFindNewGPA</code> interface in an extension for <code>ISimpleActions</code> interface and is adding up
 * upon it another function to handle a new GPA calculation based on an imaginary grade
 * @see ISimpleActions
 */
public interface IFindNewGPA extends ISimpleActions {
    /**
     * <code>newGPA</code> function is used to get a newly calculated GPA after replacing the relevant <code>courseName</code> current grade with the <code>newGrade</code> inserted by the user
     * @param courseName    the key->value <code>courseName</code> that his grade should be replaced with
     * @param newGrade  the new grade to be replaced with the current one
     * @return the newly calculated GPA
     * @throws DBActionsException in case of an SQL error
     */
    Double newGPA(String courseName, Integer newGrade) throws DBActionsException;
}
