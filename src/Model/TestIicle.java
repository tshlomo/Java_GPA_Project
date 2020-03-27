package Model;

import java.sql.SQLException;

public class TestIicle {

    public static void main(String[] args) throws SQLException {
        UserActions.addGrade("infi","c","a",76,3.5,98);
        //UserActions.deleteGrade("infi");
        //UserActions.printTable();
        //System.out.println("gpa is: "+Caluclations.calcu_gpa());
    }
}
