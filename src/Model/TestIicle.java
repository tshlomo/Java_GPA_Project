package Model;

import java.sql.Connection;
import java.sql.SQLException;

public class TestIicle {

    public static void main(String[] args) throws SQLException {
        //DBconnection.getDBConnection();
        DBActions dbact = new DBActions();
        dbact.addGrade("bdida","b",76,2.5,79);
        dbact.printTable();
//        System.out.println("now deleting bdida--------------------------------------------------------------------------------------------------");
//        dbact.deleteGrade("bdida");
//        dbact.printTable();
//        for (Double d :
//                dbact.getCredits()) {
//            System.out.println(d);
//        }
        //Connection x=DBconnection.GetDBConnection();
        //System.out.println(x);
    }
}
