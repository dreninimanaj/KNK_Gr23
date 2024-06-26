package com.example.knk_gr23.Controllers.Admin;

import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Reposirtory.ClientRepository;
import com.example.knk_gr23.Services.LoanService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


public class RequestComponentController{
    @FXML
    private Button viewUserInfoButton;
    @FXML
    private Button acceptLoanButton;
    @FXML
    private Label loanStatusLabel;
    @FXML
    private Label loanAmountLabel;
    @FXML
    private Label loanIdLabel;


    private Loan loan;

    @FXML
    private void handleAcceptLoan(ActionEvent event) {
        try {
            boolean success = LoanService.acceptLoan(loan.getLoan_id());
            if (success) {
                loanStatusLabel.setText("approved");
                acceptLoanButton.setDisable(true);  // Disable button after acceptance
            } else {
                System.err.println("Failed to accept loan with ID: " + loan.getLoan_id());
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while accepting loan with ID: " + loan.getLoan_id());
        }
    }


    @FXML
    private void handleViewUserInfo(ActionEvent event) {
        // Implement the logic to view user information
        System.out.println("Viewing user info for client ID: " + loan.getClient_id());
        loan.getClient_id();
        SessionMenager.setClient(ClientRepository.findClientByUserId(loan.getClient_id()));
        Navigator.navigate(event, Navigator.PROFILE_ADMIN);
    }
    public void setData(Loan loan) {
        this.loan = loan;
        loanIdLabel.setText(String.valueOf(loan.getLoan_id()));
        loanAmountLabel.setText(String.valueOf(loan.getLoan_amount()));
        loanStatusLabel.setText(loan.getLoan_status());
    }
}
