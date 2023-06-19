import java.sql.*;
import java.util.*;

public class Main {

	static Connection connection=null;
	static Operations m=new Operations();
    public static void main(String arg[]) throws ClassNotFoundException{
        try {
            
        	Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/doctor_booking","admin","root");

          
            Statement statement = connection.createStatement();

            m.createTables(statement);

            
            boolean exit = false;
            while (!exit) {
                m.displayMenu();
                int choice = m.readChoice();
                exit = m.processChoice(connection, choice);
            }

   
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}