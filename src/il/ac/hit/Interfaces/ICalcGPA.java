package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Model.Calculations;

/**
 * This interface is intended to make it easier for us to get an object
 * that his solely purpose is to calculate the grades for us from inside the DB
 * @see Calculations the class that implements this interface
 */
public interface ICalcGPA {
    /**
     * calculate_GPA function calculates and returns the GPA based on the grades within the database
     * <p>
     * The function reaches to the database from within the Model package,
     * pulls all the courses grades and calculates the GPA based on a formula.
     * </p>
     * @see Calculations#calculate_GPA() The function used for this interface in this project
     * @return the calculated GPA
     * @throws DBActionsException in case of an SQL error
     */
    Double calculate_GPA() throws DBActionsException;
    /**
     * gpaByGrade function calculates and returns the new GPA based on the grades within the database,
     * while replacing the new grade in the calculated GPA
     * <p>
     * The function reaches to the database from within the Model package, pulls all the courses grades,
     * replacing the relevant course with the desired grade and calculates the GPA based on a formula.
     * </p>
     * @see Calculations#gpaByGrade(String, Integer)  The function used for this interface in this project
     * @param courseName the desired course that his grade going to be replaced with
     * @param newGrade the new grade that will go to the GPA formula instead the current one
     * @return the calculated GPA
     * @throws DBActionsException   in case of an SQL error
     */
    Double gpaByGrade(String courseName, Integer newGrade) throws DBActionsException;
}
