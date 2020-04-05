package il.ac.hit.Test.JUnit;

import il.ac.hit.Interfaces.ISimpleActions;
import il.ac.hit.Model.Model;
import il.ac.hit.Exceptions.DBActionsException;
import il.ac.hit.Model.DBconnection;
import il.ac.hit.ViewModel.CourseDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {
    Statement statement = null;
    ResultSet rs = null;
    Connection conn = null;

    @org.junit.jupiter.api.Test
    void addGrade() throws DBActionsException {
        Integer i=0;
        Object [] array={"snowboarding",3,75,90,2.0,99};
        ISimpleActions dbact = new Model();
        conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            dbact.addGrade(new CourseDetails("snowboarding", 3, 75, 90, 2.0,99)); // adding same param values like in the array for comparing //
            rs = statement.executeQuery("SELECT * FROM GPA WHERE course='snowboarding'");
            while(rs.next()){
                assertEquals(array[i],rs.getObject(i+1));
            }
            dbact.deleteGrade("snowboarding");
        } catch (SQLException e) {
           throw new DBActionsException("problem adding a grade",e);
        }finally {
            if (statement != null) try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @org.junit.jupiter.api.Test
    void deleteGrade() throws DBActionsException {
        Model dbact = new Model();
        conn = DBconnection.getDBConnection();
        try {
            statement = conn.createStatement();
            statement.execute("INSERT INTO GPA VALUES ('ski',3,3,70,2.5,90)");
            dbact.deleteGrade("ski");
            rs = statement.executeQuery("SELECT count(*) FROM GPA WHERE course=('ski')");
            rs.next();
            assertEquals(0, rs.getInt(1));

        } catch (SQLException e) {
           throw new DBActionsException("problem deleting grade",e);
        } finally {
            if (statement != null) try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            conn = null;
            rs = null;
            statement = null;
        }
        }

        @org.junit.jupiter.api.Test
        void editGrade () throws DBActionsException {
            Model dbact = new Model();
            conn = DBconnection.getDBConnection();
            try {
                statement = conn.createStatement();
                statement.execute("INSERT INTO GPA VALUES ('scala',3,3,70,2.5,90)");
                dbact.editGrade(new CourseDetails("scala", 3, 3, 70, 2.5, 85));  // updating the finalGrade param //
                rs = statement.executeQuery("SELECT finalGrade FROM GPA WHERE course=('scala')");
                rs.next();
                assertEquals(85,rs.getDouble("finalGrade"));

            } catch (SQLException e) {
                throw new DBActionsException("problem updating the grade",e);
            } finally {
                dbact.deleteGrade("scala");
                if (statement != null) try {
                    statement.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (rs != null) try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    @org.junit.jupiter.api.Test
    void getCourse() throws DBActionsException {
        Model dbact = new Model();
        CourseDetails newCourse=new CourseDetails("fifa",3,2,90,2.0,99);  // those param values will be compared in the assert methods ahead //
        conn = DBconnection.getDBConnection();
        try {
           statement = conn.createStatement();
            dbact.addGrade(newCourse);
            CourseDetails course=dbact.getCourse("fifa");
            assertEquals("fifa",course.getCourseName());
            assertEquals(3,course.getYear());
            assertEquals(2,course.getSemester());
            assertEquals(90,course.getTestGrade());
            assertEquals(2.0,course.getCredits());
            assertEquals(99,course.getFinalGrade());

        } catch (SQLException e) {
           throw new DBActionsException("problem getting a course from db",e);
        }finally {
            dbact.deleteGrade("fifa");
            if (statement != null) try {
                statement.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (rs != null) try {
                rs.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }




   }