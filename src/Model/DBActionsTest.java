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

    @org.junit.jupiter.api.Test
    void addGrade() throws SQLException {
        DBActions dbact = new DBActions();
        conn=DBconnection.getDBConnection();
        statement=conn.createStatement();
        try {
            dbact.addGrade("africa in black and white", 3, 75, 3.5, 90);
            rs=statement.executeQuery("SELECT * FROM GPA WHERE course='africa in black and white'");



        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @org.junit.jupiter.api.Test
    void deleteGrade() throws SQLException {
        DBActions dbact = new DBActions();
        conn=DBconnection.getDBConnection();
        statement=conn.createStatement();
        try{
        statement.execute("INSERT INTO GPA VALUES ('snowboarding',3,70,2.5,90)");
        rs=statement.executeQuery("")

    }catch (Exception e){
            e.printStackTrace();
        }

    @org.junit.jupiter.api.Test
    void editGrade() {
    }

    @org.junit.jupiter.api.Test
    void getFinalGrade() {
    }
}