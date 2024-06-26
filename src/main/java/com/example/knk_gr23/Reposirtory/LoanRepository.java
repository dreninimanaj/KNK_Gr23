package com.example.knk_gr23.Reposirtory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.knk_gr23.Models.Client;
import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.database.DatabaseUtil;

public class LoanRepository {

    // Method to get all loans by client_id
    public static List<Loan> getAllLoansByUser(Client client)
    {
        //System.out.println("Preparing statement: " + query);
        System.out.println("Client ID: " + client.getClientId());
//        User user = new User();
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loans WHERE client_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

//            int clientId = user.getId(); // Assuming user_id is the client_id
            // Log the prepared statement and the client_id being queried


            System.out.println("Preparing statement: " + query);
            System.out.println("Client ID: " + client.getClientId());
            System.out.println("Preparing statement: " + query);
//            System.out.println("Setting client_id parameter: " + clientId);

            System.out.println("Preparing statement: " + query);
            System.out.println("Client ID: " + client.getClientId());
            pstmt.setInt(1, client.getClientId());
            System.out.println("bkans");
            try (ResultSet rs = pstmt.executeQuery()) {

                // Log the execution of the query
                System.out.println("Executing query...");

                System.out.println("hsjkdn d");
                while (rs.next()) {
                    // Create and add the Loan object from the ResultSet to the list
                    System.out.println("1"+getFromResultSet(rs));
                    loans.add(getFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch loans by client_id: " + e.getMessage());
            e.printStackTrace();
        }
        return loans;
    }
    public static Loan findLoanById(int loanId) {
        String query = "SELECT * FROM loans WHERE loan_id = ?";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, loanId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return getFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch loan by ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // Helper method to create a Loan object from a ResultSet
    private static Loan getFromResultSet(ResultSet result)  {
        try {
            int loan_id = result.getInt("loan_id");
            int client_id = result.getInt("client_id");
            BigDecimal loan_amount = result.getBigDecimal("loan_amount");
            BigDecimal interest_rate = result.getBigDecimal("interest_rate");
            Date loan_start_date = result.getDate("loan_start_date");
            Date loan_end_date = result.getDate("loan_end_date");
            String loan_status = result.getString("loan_status");
            System.out.println(loan_id);
            return new Loan(loan_id, client_id, loan_amount, interest_rate, loan_start_date, loan_end_date, loan_status);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public static boolean updateLoanStatus(int loanId, String status) throws SQLException {
        String query = "UPDATE loans SET loan_status = ? WHERE loan_id = ?";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, status);
            pstmt.setInt(2, loanId);

            int affectedRows = pstmt.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.err.println("Failed to update loan status: " + e.getMessage());
            throw e;
        }
    }
}
