import java.sql.*;
import java.util.*;
public class Doctor {
	  Scanner scanner=new Scanner(System.in);
	  public void addDoctor(Connection connection) throws SQLException {
		  System.out.print("Enter doctor name: ");
		  String name = scanner.nextLine();
		
		  System.out.print("Enter doctor specialization: ");
		  String specialization = scanner.nextLine();
		
		  System.out.print("Enter doctor department: ");
		  String department = scanner.nextLine();
		
		  String query = "INSERT INTO doctors (name, specialization, department) VALUES (?, ?, ?)";
		  PreparedStatement pstmt = connection.prepareStatement(query);
		  pstmt.setString(1, name);
		  pstmt.setString(2, specialization);
		  pstmt.setString(3, department);
		
		  int rowsInserted = pstmt.executeUpdate();
		  if (rowsInserted > 0) {
		      System.out.println("Doctor added successfully!");
		  } else {
		      System.out.println("Failed to add doctor. Please try again.");
		  }
		
		  pstmt.close();
		}
	    public void viewDoctors(Connection connection) throws SQLException {
	        String query = "SELECT * FROM doctors";
	        Statement stmt = connection.createStatement();
	        ResultSet rs = stmt.executeQuery(query);

	        System.out.println("Doctors:");
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
	            String specialization = rs.getString("specialization");
	            String department = rs.getString("department");

	            System.out.println("ID: " + id);
	            System.out.println("Name: " + name);
	            System.out.println("Specialization: " + specialization);
	            System.out.println("Department: " + department);
	            System.out.println();
	        }

	        rs.close();
	        stmt.close();
	    }
	    public void searchDoctorByName(Connection connection) throws SQLException { 
	        System.out.print("Enter the name of the doctor to search: ");
	        String name = scanner.nextLine();

	        String query = "SELECT * FROM doctors WHERE name LIKE ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setString(1, "%" + name + "%");

	        ResultSet rs = pstmt.executeQuery();

	        System.out.println("Search Results:");
	        while (rs.next()) {
	            int id = rs.getInt("id");
	            String doctorName = rs.getString("name");
	            String specialization = rs.getString("specialization");
	            String department = rs.getString("department");

	            System.out.println("ID: " + id);
	            System.out.println("Name: " + doctorName);
	            System.out.println("Specialization: " + specialization);
	            System.out.println("Department: " + department);
	            System.out.println();
	        }

	        rs.close();
	        pstmt.close();
	    }

	    public void updateDoctorDetails(Connection connection) throws SQLException {
	        System.out.print("Enter the ID of the doctor to update: ");
	        int id = scanner.nextInt();
	        scanner.nextLine(); 

	        String query = "SELECT * FROM doctors WHERE id = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, id);

	        ResultSet rs = pstmt.executeQuery();

	        if (rs.next()) {
	            System.out.println("Current Details:");
	            System.out.println("Name: " + rs.getString("name"));
	            System.out.println("Specialization: " + rs.getString("specialization"));
	            System.out.println("Department: " + rs.getString("department"));
	            System.out.println();

	            System.out.println("Enter new details:");

	            System.out.print("Enter doctor name: ");
	            String name = scanner.nextLine();

	            System.out.print("Enter doctor specialization: ");
	            String specialization = scanner.nextLine();

	            System.out.print("Enter doctor department: ");
	            String department = scanner.nextLine();

	            String updateQuery = "UPDATE doctors SET name = ?, specialization = ?, department = ? WHERE id = ?";
	            PreparedStatement updateStmt = connection.prepareStatement(updateQuery);
	            updateStmt.setString(1, name);
	            updateStmt.setString(2, specialization);
	            updateStmt.setString(3, department);
	            updateStmt.setInt(4, id);

	            int rowsUpdated = updateStmt.executeUpdate();
	            if (rowsUpdated > 0) {
	                System.out.println("Doctor details updated successfully!");
	            } else {
	                System.out.println("Failed to update doctor details. Please try again.");
	            }

	            updateStmt.close();
	        } else {
	            System.out.println("Doctor not found with the given ID.");
	        }

	        rs.close();
	        pstmt.close();
	    }

	    public void deleteDoctor(Connection connection) throws SQLException {
	        System.out.print("Enter the ID of the doctor to delete: ");
	        int id = scanner.nextInt();

	        String query = "DELETE FROM doctors WHERE id = ?";
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        pstmt.setInt(1, id);

	        int rowsDeleted = pstmt.executeUpdate();
	        if (rowsDeleted > 0) {
	            System.out.println("Doctor deleted successfully!");
	        } else {
	            System.out.println("Failed to delete doctor. Please try again.");
	        }

	        pstmt.close();
	    }
}
