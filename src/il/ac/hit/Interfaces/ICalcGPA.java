package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;

public interface ICalcGPA {
    Double calculate_GPA() throws DBActionsException;
    Double gpaByGrade(String courseName, Integer newGrade) throws DBActionsException;
}
