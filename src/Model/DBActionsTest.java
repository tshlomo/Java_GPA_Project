package Model;

import ViewModel.CourseDetails;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DBActionsTest {
    Statement statement = null;
    ResultSet rs = null;
    Connection conn = null;

    @org.junit.jupiter.api.Test
    void addGrade() throws SQLException {
        Integer i=0;
        Object [] array={3,75,90,2.0,99};
        DBActions dbact = new DBActions();
        conn = DBconnection.getDBConnection();
        statement = conn.createStatement();
        try {
            dbact.addGrade(new CourseDetails("snowboarding", 3, 75, 90, 2.0,99)); // adding same param values like in the array for comparing //
            rs = statement.executeQuery("SELECT * FROM GPA WHERE course='snowboarding'");
            while(rs.next()){
                assertEquals(array[i],rs.getObject(i+2));
            }
            dbact.deleteGrade("snowboarding");
        } catch (Exception e) {
            e.printStackTrace();
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
            conn = null;
            rs = null;
            statement = null;

        }
    }

    @org.junit.jupiter.api.Test
    void deleteGrade() throws SQLException {
        DBActions dbact = new DBActions();
        conn = DBconnection.getDBConnection();
        statement = conn.createStatement();
        try {
            statement.execute("INSERT INTO GPA VALUES ('ski',3,3,70,2.5,90)");
            dbact.deleteGrade("ski");
            rs = statement.executeQuery("SELECT count(*) FROM GPA WHERE course=('ski')");
            rs.next();
            assertEquals(0, rs.getInt(1));

        } catch (Exception e) {
            e.printStackTrace();
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
        void editGrade () throws SQLException {
            DBActions dbact = new DBActions();
            conn = DBconnection.getDBConnection();
            statement = conn.createStatement();
            try {
                statement.execute("INSERT INTO GPA VALUES ('scala',3,3,70,2.5,90)");
                dbact.editGrade(new CourseDetails("scala", 3, 3, 70, 2.5, 85));  // updating the finalGrade param //
                rs = statement.executeQuery("SELECT finalGrade FROM GPA WHERE course=('scala')");
                rs.next();
                assertEquals(85,rs.getDouble("finalGrade"));
                dbact.deleteGrade("scala");
            } catch (Exception e) {
                e.printStackTrace();
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
        void getFinalGrade () throws SQLException {
            DBActions dbact = new DBActions();
            conn = DBconnection.getDBConnection();
            statement = conn.createStatement();
            try {
                statement.execute("INSERT INTO GPA VALUES ('groovy',3,3,70,2.5,90)");
                rs = statement.executeQuery("SELECT finalGrade FROM GPA WHERE course=('groovy')");
                rs.next();
                assertEquals(90, rs.getInt("finalGrade"));
                dbact.deleteGrade("groovy");

            } catch (Exception e) {
                e.printStackTrace();
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
    void getCredit () throws SQLException {
        DBActions dbact = new DBActions();
        conn = DBconnection.getDBConnection();
        statement = conn.createStatement();
        try {
            statement.execute("INSERT INTO GPA VALUES ('africa in black and white',3,3,70,4.0,90)");
            rs = statement.executeQuery("SELECT credits FROM GPA WHERE course=('africa in black and white')");
            rs.next();
            assertEquals(4.0, rs.getDouble("credits"));
            dbact.deleteGrade("africa in black and white");

        } catch (Exception e) {
            e.printStackTrace();
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
   }