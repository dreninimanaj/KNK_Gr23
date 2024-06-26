package com.example.knk_gr23.Controllers.Client;
import com.example.knk_gr23.Services.LoanService;
import com.example.knk_gr23.App.Navigator;
import com.example.knk_gr23.App.SessionMenager;
import com.example.knk_gr23.Models.Loan;
import com.example.knk_gr23.Models.dto.PaymentDto;
import com.example.knk_gr23.Services.LoanCalculatorService;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.List;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class LoanComponentController implements Initializable {
    
    @FXML
    private Label loanIdLabel;

    @FXML
    private Label loanAmountLabel;

    @FXML
    private Label loanStatusLabel;

    @FXML
    private Button toTAble_btn;

    @FXML
    private Button pay_btn;

    @FXML
    public void handlePay(ActionEvent ae) {
        Navigator.navigate(ae, Navigator.PAY_PAGE);
    }

    @FXML
    public void handleTable(ActionEvent ae) {
        int loanId = Integer.parseInt(loanIdLabel.getText());
//        SessionMenager.setClient();
//        List<PaymentDto> payments = LoanCalculatorService.getPaymentsForLoan(loanId);
        SessionMenager.setLoan(LoanService.getLoanById(loanId));
        Navigator.navigate(ae, Navigator.TABLE_PAGE);
    }
    public void setData(Loan loan) {
        loanIdLabel.setText(String.valueOf(loan.getLoan_id()));
        loanAmountLabel.setText(String.valueOf(loan.getLoan_amount()));
        loanStatusLabel.setText(loan.getLoan_status());
    }
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        this.toTAble_btn.setText(resourceBundle.getString("lblTable"));
//        this.pay_btn.setText(resourceBundle.getString("lblPayloan"));
    }
}
