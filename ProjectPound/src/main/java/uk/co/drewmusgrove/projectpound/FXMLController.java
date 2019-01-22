package uk.co.drewmusgrove.projectpound;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class FXMLController implements Initializable {
    
    @FXML
    private Label label;
    @FXML
    private Button button;
    @FXML
    private Label lb_accountNumber;
    @FXML
    private Label lb_balance;
    @FXML
    private Label lb_name;
    @FXML
    private TextField tf_customerID;
    @FXML
    private Button buttonDB;
    @FXML
    private TextField tf_updateName;
    @FXML
    private TextField tf_updateAccountNo;
    @FXML
    private TextField tf_updateBalance;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
            Customer customer = new Customer();
            System.out.println("You clicked me!");
            label.setText("Hello World!");
            System.out.println(tf_customerID.getText());
            System.out.println(customer.getName(tf_customerID.getText()));
            String data[] = customer.selectCustomer(tf_customerID.getText());
            // Updated Name Label
            lb_name.setText(data[0]);
            // Updated account number label
            lb_accountNumber.setText(data[3]);
            // Updated account number label
            lb_balance.setText(data[2]); 
    }
    
    @FXML
    private void handleAddCustomerAction(ActionEvent event) 
    {
        Customer customer = new Customer();
        String balance = tf_updateBalance.getText();
        String name = tf_updateName.getText();
        String age = "21";
        String accountNo = tf_updateAccountNo.getText();
      
        label.setText(customer.createCustomer(name, age, accountNo, balance));
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
}
