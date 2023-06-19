import java.util.*;
import java.sql.*;
public class Patient {
	  Scanner scanner = new Scanner(System.in);
	  public void addPatient(Connection connection) throws SQLException {
	        System.out.print("Enter patient name: ");
	        String name = scanner.nextLine();

	        System.out.print("Enter patient age: ");
	        int age = scanner.nextInt();
	        scanner.nextLine(); // Consume the newline character

	        System.out.print("Enter patient gender: ");
	        String gender = scanner.nextLine();

	        System.out.print("Enter patient address: ");
	        String address = scanner.nextLine();

	        String query = "INSERT INTO patients (name, age, gender, address) VALUES (?, ?, ?, ?)";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, name);
	        pstmt.setInt(2, age);
	        pstmt.setString(3, gender);
	        pstmt.setString(4, address);

	        int rowsInserted = pstmt.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("Patient added successfully!");
	        } else {
	            System.out.println("Failed to add patient. Please try again.");
	        }

	        pstmt.close();
	    }

	    public void viewPatients(Connection connection) throws SQLException {
	        String query = "SELECT * FROM patients";
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        System.out.println("Patients:");
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            int age = rs.getInt("age");
	            String gender = rs.getString("gender");
	            String address = rs.getString("address");

	            System.out.println("ID: " + id);
	            System.out.println("Name: " + name);
	            System.out.println("Age: " + age);
	            System.out.println("Gender: " + gender);
	            System.out.println("Address: " + address);
	            System.out.println();
	        }

	        rs.close();
	        stmt.close();
	    }

	    public void searchPatientByName(Connection connection) throws SQLException {
	        System.out.print("Enter the name of the patient to search: ");
	        String name = scanner.nextLine();

	        String query = "SELECT * FROM patients WHERE name LIKE ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, "%" + name + "%");

	        ResultSet rs = pstmt.executeQuery();

	        System.out.println("Search Results:");
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String patientName = rs.getString("name");
	            int age = rs.getInt("age");
	            String gender = rs.getString("gender");
	            String address = rs.getString("address");

	            System.out.println("ID: " + id);
	            System.out.println("Name: " + patientName);
	            System.out.println("Age: " + age);
	            System.out.println("Gender: " + gender);
	            System.out.println("Address: " + address);
	            System.out.println();
	        }

	        rs.close();
	        pstmt.close();
	    }

	    public void updatePatientDetails(Connection connection) throws SQLException {
	        System.out.print("Enter the ID of the patient to update: ");
	        int id = scanner.nextInt();
	        scanner.nextLine();

	        String query = "SELECT * FROM patients WHERE id = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, id);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            System.out.println("Current Details:");
	            System.out.println("Name: " + rs.getString("name"));
	            System.out.println("Age: " + rs.getInt("age"));
	            System.out.println("Gender: " + rs.getString("gender"));
	            System.out.println("Address: " + rs.getString("address"));
	            System.out.println();

	            System.out.println("Enter new details:");

	            System.out.print("Enter patient name: ");
	            String name = scanner.nextLine();

	            System.out.print("Enter patient age: ");
	            int age = scanner.nextInt();
	            scanner.nextLine();

	            System.out.print("Enter patient gender: ");
	            String gender = scanner.nextLine();

	            System.out.print("Enter patient address: ");
	            String address = scanner.nextLine();

	            String updateQuery = "UPDATE patients SET name = ?, age = ?, gender = ?, address = ? WHERE id = ?";
	            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
	            updateStmt.setString(1, name);
	            updateStmt.setInt(2, age);
	            updateStmt.setString(3, gender);
	            updateStmt.setString(4, address);
	            updateStmt.setInt(5, id);

	            int rowsUpdated = updateStmt.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Patient details updated successfully!");
	            } else {
	                System.out.println("Failed to update patient details. Please try again.");
	            }

	            updateStmt.close();
	        } else {
	            System.out.println("Patient not found with the given ID.");
	        }

	        rs.close();
	        pstmt.close();
	    }

	    public void deletePatient(Connection connection) throws SQLException {
	        System.out.print("Enter the ID of the patient to delete: ");
	        int id = scanner.nextInt();

	        String query = "DELETE FROM patients WHERE id = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, id);

	        int rowsDeleted = pstmt.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Patient deleted successfully!");
	        } else {
	            System.out.println("Failed to delete patient. Please try again.");
	        }

	        pstmt.close();
	    }
}
