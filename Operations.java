import java.util.*;
import java.sql.*;
class Operations extends Main{
	Scanner scanner = new Scanner(System.in);
	static Doctor d=new Doctor();
	static Patient p=new Patient();
    public void createTables(Statement statement) throws SQLException {
       
        String createPatientsTableQuery = "CREATE TABLE IF NOT EXISTS patients (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "age INT NOT NULL," +
                "gender VARCHAR(10) NOT NULL," +
                "address VARCHAR(100) NOT NULL)";
        try {
        	Statement stmt=connection.createStatement();
        }
        catch(Exception e) {
        	e.printStackTrace();
        }
        statement.executeUpdate(createPatientsTableQuery);

        String createDoctorsTableQuery = "CREATE TABLE IF NOT EXISTS doctors (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR(50) NOT NULL," +
                "specialization VARCHAR(50) NOT NULL," +
                "department VARCHAR(50) NOT NULL)";
        statement.executeUpdate(createDoctorsTableQuery);
    }

    public void displayMenu() {
        System.out.println("Hospital Management System");
        System.out.println("--------------------------");
        System.out.println("1. Add Patient");
        System.out.println("2. Add Doctor");
        System.out.println("3. View Patients");
        System.out.println("4. View Doctors");
        System.out.println("5. Search Patient by Name");
        System.out.println("6. Update Patient Details");
        System.out.println("7. Delete Patient");
        System.out.println("8. Search Doctor by Name");
        System.out.println("9. Update Doctor Details");
        System.out.println("10. Delete Doctor");
        System.out.println("11. Exit");
        System.out.print("Enter your choice: ");
    }

    public int readChoice() {
        return scanner.nextInt();
    }

    public boolean processChoice(Connection connection, int choice) throws SQLException {
        switch (choice) {
            case 1:
                p.addPatient(connection);
                break;
            case 2:
                d.addDoctor(connection);
                break;
            case 3:
                p.viewPatients(connection);
                break;
            case 4:
                d.viewDoctors(connection);
                break;
            case 5:
                p.searchPatientByName(connection);
                break;
            case 6:
                p.updatePatientDetails(connection);
                break;
            case 7:
                p.deletePatient(connection);
                break;
            case 8:
                d.searchDoctorByName(connection);
                break;
            case 9:
                d.updateDoctorDetails(connection);
                break;
            case 10:
                d.deleteDoctor(connection);
                break;
            case 11:
                System.out.println("Exiting...");
                return true;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
        return false;
    }
}