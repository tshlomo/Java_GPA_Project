package Model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class DBActionsTest {
    Statement statement = null;
    ResultSet rs = null;
    Connection conn = null;

//    @org.junit.jupiter.api.Test
//    void addGrade() throws SQLException {
//        DBActions dbact = new DBActions();
//        conn = DBconnection.getDBConnection();
//        statement = conn.createStatement();
//        try {
//            dbact.addGrade("africa in black and white", 3, 75, 3.5, 90);
//            rs = statement.executeQuery("SELECT * FROM GPA WHERE course='africa in black and white'");
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @org.junit.jupiter.api.Test
    void deleteGrade() throws SQLException {
        DBActions dbact = new DBActions();
        conn = DBconnection.getDBConnection();
        statement = conn.createStatement();
        try {
            statement.execute("INSERT INTO GPA VALUES ('snowboarding',3,3,70,2.5,90)");
            dbact.deleteGrade("snowboarding");
            rs = statement.executeQuery("SELECT count(*) FROM GPA WHERE course=('snowboarding')");
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
                statement.execute("INSERT INTO GPA VALUES ('ski',3,3,70,2.5,90)");
                dbact.editGrade("ski", 3, 3, 70, 2.5, 85);
                rs = statement.executeQuery("SELECT finalGrade FROM GPA WHERE course=('ski')");
                rs.next();
                dbact.deleteGrade("ski");
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
                statement.execute("INSERT INTO GPA VALUES ('ski',3,3,70,2.5,90)");
                rs = statement.executeQuery("SELECT finalGrade FROM GPA WHERE course=('ski')");
                rs.next();
                assertEquals(90, rs.getInt("finalGrade"));
                dbact.deleteGrade("ski");

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