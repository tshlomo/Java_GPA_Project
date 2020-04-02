package Model;

import ViewModel.CourseDetails;

import java.sql.Connection;
import java.sql.SQLException;

public class TestIicle {

    public static void main(String[] args) throws DBActionsException, SQLException {
        //DBconnection.getDBConnection();
        DBActions dbact = new DBActions();
        dbact.printTable();
        //dbact.getFinalGrade("infi");
        //dbact.addGrade(new CourseDetails("klila",3,3,75,4.0,77));
        //Calculations cal=new Calculations();
        //double y=cal.calculate_GPA();
        //System.out.println(y);
        //Double x=cal.gpaByGrade("klila",60);
        //System.out.println(x);


        //CourseDetails course=null;
        //course= dbact.getCourse("klila");

        //Double x=cal.gpaByGrade("infi",20);
        //System.out.println(" lala   "+x);

//        System.out.println("now deleting bdida--------------------------------------------------------------------------------------------------");
        //dbact.deleteGrade("ski");
         //dbact.printTable();
        //Connection x=DBconnection.GetDBConnection();
        //System.out.println(x);
    }
}
