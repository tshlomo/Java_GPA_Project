package Model;

import java.sql.SQLException;

public class TestIicle {

    public static void main(String[] args) throws SQLException {
        //DBconnection.getDBConnection();
        DBActions dbact = new DBActions();
        //dbact.getFinalGrade("infi");
        //Caluclations cal=new Caluclations();
        //int x=cal.calculate_GPA();
        //System.out.println(x);
        //dbact.addGrade("klila",3,3,80,2.0,98);
        //dbact.printTable();
        //Double x=cal.gpaByGrade("infi",20);
        //System.out.println(" lala   "+x);

//        System.out.println("now deleting bdida--------------------------------------------------------------------------------------------------");
        //dbact.deleteGrade("ski");
         dbact.printTable();
        //Connection x=DBconnection.GetDBConnection();
        //System.out.println(x);
    }
}
