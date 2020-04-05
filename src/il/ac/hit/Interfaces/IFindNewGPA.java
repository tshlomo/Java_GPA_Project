package il.ac.hit.Interfaces;

import il.ac.hit.Exceptions.DBActionsException;

public interface IFindNewGPA extends ISimpleActions {
    public Double newGPA(String courseName, Integer newGrade) throws DBActionsException;
}
