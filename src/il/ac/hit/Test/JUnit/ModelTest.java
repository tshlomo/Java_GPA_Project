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
        CourseDetails courseDetails = new CourseDetails("snowboarding", 3, 75, 90, 2.0, 99);
        CourseDetails addedCourse = new CourseDetails("snowboarding", 3, 75, 90, 2.0, 99);
        try {
            statement = conn.createStatement();
            dbAct.addGrade(addedCourse); // adding same param values like in the array for comparing //
            rs = statement.executeQuery("SELECT * FROM GPA WHERE course='snowboarding'");
            while (rs.next()) {
                assertTrue(courseDetails.isSameCourse(addedCourse));
            }
        } catch (SQLException e) {
            throw new DBActionsException("problem adding a grade", e);
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
        } finally {
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
                throw new DBActionsException("problem closing Statement variable",e);
            }
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                throw new DBActionsException("problem closing ResultSet variable",e);
            }
            conn = null;
            rs = null;
            statement = null;
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
        } finally {
            dbAct.deleteGrade("scala");
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
        }
    }

    @org.junit.jupiter.api.Test
    void getCourse() throws DBActionsException {
        CourseDetails newCourse=new CourseDetails("fifa",3,2,90,2.0,99);  // those param values will be compared in the assert methods ahead //
        try {
            statement = conn.createStatement();
            dbAct.addGrade(newCourse);  //adding the newCourse to db
            CourseDetails course=dbAct.getCourse("fifa");
            assertEquals("fifa",course.getCourseName());
            assertEquals(3,course.getYear());
            assertEquals(2,course.getSemester());
            assertEquals(90,course.getTestGrade());
            assertEquals(2.0,course.getCredits());
            assertEquals(99,course.getFinalGrade());

        } catch (SQLException e) {
            throw new DBActionsException("problem getting a course from db",e);
        }finally {
            dbAct.deleteGrade("fifa");
            newCourse=null;
            if (statement != null) try {
                statement.close();
            } catch (SQLException e) {
                throw new DBActionsException("problem closing Statement variable",e);
            }
            if (rs != null) try {
                rs.close();
            } catch (SQLException e) {
                throw new DBActionsException("problem closing ResultSet variable",e);
            }

        }
    }


}