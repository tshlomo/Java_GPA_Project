package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;

public interface IFindNewGPA extends ISimpleActions {
    Double newGPA(String courseName, Integer newGrade) throws DBActionsException;
}
