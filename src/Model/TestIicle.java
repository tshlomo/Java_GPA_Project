package Model;

import java.sql.Connection;
import java.sql.SQLException;

public class TestIicle {

    public static void main(String[] args) throws SQLException {
        //DBconnection.getDBConnection();
        DBActions dbact = new DBActions();
        //dbact.getFinalGrade("infi");
        //Caluclations cal=new Caluclations();
        //int x=cal.calculate_GPA();
        //System.out.println(x);
        dbact.addGrade("math1","c",80,2.0,98);
        //dbact.printTable();
        //Double x=cal.gpaByGrade("infi",20);
        //System.out.println(" lala   "+x);

//        System.out.println("now deleting bdida--------------------------------------------------------------------------------------------------");
//        dbact.deleteGrade("bdida");
         dbact.printTable();
        //Connection x=DBconnection.GetDBConnection();
        //System.out.println(x);
    }
}
