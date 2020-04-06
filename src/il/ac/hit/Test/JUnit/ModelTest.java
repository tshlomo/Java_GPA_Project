package il.ac.hit.Test.JUnit;

import il.ac.hit.Interfaces.IDBSimpleActions;
import il.ac.hit.Model.Model;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Model.DBconnection;
import il.ac.hit.ViewModel.CourseDetails;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Statement statement;
    ResultSet rs;
    Connection conn;
    IDBSimpleActions dbAct;

    // problem with dbAct interface and getCourse method !! //

    @BeforeEach
    void beforeEach() throws DBActionsException {
        conn = DBconnection.getDBConnection();
        dbAct = new Model();
    }

    @AfterEach
    void afterEach()throws DBActionsException{
        if (statement != null) try {
            statement.close();
        } catch (SQLException e) {
            throw new DBActionsException("problem closing Statement variable", e);
        }
        if (rs != null) try {
            rs.close();
        } catch (SQLException e) {
            throw new DBActionsException("problem closing ResultSet variable", e);
        }
        finally {
            dbAct=null;
            conn=null;
            rs = null;
            statement = null;
        }
    }

    @org.junit.jupiter.api.Test
    void addGrade() throws DBActionsException {
        CourseDetails courseDetails;
        CourseDetails addedCourse = new CourseDetails("snowboarding", 3, 75, 90, 2.0, 99);
        try {
            statement = conn.createStatement();
            dbAct.addGrade(addedCourse); // adding same param values like in the array for comparing //
            courseDetails=dbAct.getCourse("snowboarding");
            assertTrue(courseDetails.isSameCourse(addedCourse));
        } catch (SQLException e) {
            throw new DBActionsException("problem adding/retrieving course", e);
        } finally {
            dbAct.deleteGrade("snowboarding");
            courseDetails = null;
            addedCourse = null;
        }
    }

    @org.junit.jupiter.api.Test
    void deleteGrade() throws DBActionsException {
        try {
            statement = conn.createStatement();
            statement.execute("INSERT INTO GPA VALUES ('ski',3,3,70,2.5,90)");
            dbAct.deleteGrade("ski");
            rs = statement.executeQuery("SELECT count(*) FROM GPA WHERE course=('ski')");
            rs.next();
            assertEquals(0, rs.getInt(1));
        } catch (SQLException e) {
            throw new DBActionsException("problem deleting grade",e);
        }
    }

    @org.junit.jupiter.api.Test
    void editGrade() throws DBActionsException {
        try {
            statement = conn.createStatement();
            statement.execute("INSERT INTO GPA VALUES ('scala',3,3,70,2.5,90)");
            dbAct.editGrade(new CourseDetails("scala", 3, 3, 70, 2.5, 85));  // updating the finalGrade param //
            rs = statement.executeQuery("SELECT finalGrade FROM GPA WHERE course=('scala')");
            rs.next();
            assertEquals(85, rs.getDouble("finalGrade"));

        } catch (SQLException e) {
            throw new DBActionsException("problem updating the grade", e);
        }
    }

    @org.junit.jupiter.api.Test
    void getCourse() throws DBActionsException {
        CourseDetails addedCourse = new CourseDetails("fifa",3,2,90,2.0,99); // those param values will be compared in the assert methods ahead //
        CourseDetails courseDetails;
        try {
            statement = conn.createStatement();
            dbAct.addGrade(addedCourse);  //adding the newCourse to db
            courseDetails=dbAct.getCourse("fifa");
            assertTrue(courseDetails.isSameCourse(addedCourse));

        } catch (SQLException e) {
            throw new DBActionsException("problem adding/retrieving course", e);
        }finally {
            dbAct.deleteGrade("fifa");
            courseDetails=null;
            addedCourse=null;

        }
    }


}