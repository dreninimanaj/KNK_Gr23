package com.example.knk_gr23.Reposirtory;

import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.database.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PendingLoanRepository {

    // Method to get all pending loans
    public static List<Loan> getAllPendingLoans() {
        List<Loan> loans = new ArrayList<>();
        String query = "SELECT * FROM loans WHERE loan_status = 'pending'";

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            // Log the execution of the query
            System.out.println("Executing query: " + query);

            while (rs.next()) {
                loans.add(getFromResultSet(rs));
            }
        } catch (SQLException e) {
            System.err.println("Failed to fetch pending loans: " + e.getMessage());
            e.printStackTrace();
        }
        return loans;
    }

    // Helper method to create a Loan object from a ResultSet
    private static Loan getFromResultSet(ResultSet result) {
        try {
            int loan_id = result.getInt("loan_id");
            int client_id = result.getInt("client_id");
            BigDecimal loan_amount = result.getBigDecimal("loan_amount");
            BigDecimal interest_rate = result.getBigDecimal("interest_rate");
            Date loan_start_date = result.getDate("loan_start_date");
            Date loan_end_date = result.getDate("loan_end_date");
            String loan_status = result.getString("loan_status");

            return new Loan(loan_id, client_id, loan_amount, interest_rate, loan_start_date, loan_end_date, loan_status);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
