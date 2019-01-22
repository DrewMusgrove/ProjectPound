/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uk.co.drewmusgrove.projectpound;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author drew.musgrove
 */
public class LoginController implements Initializable {

    @FXML
    private TextField tf_username;
    @FXML
    private PasswordField pw_password;
    @FXML
    private Button bt_login;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleLoginAction(ActionEvent event) {
        try {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/Scene.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1)); 
        stage.setTitle("JavaFX and Maven");
        stage.show();
        
        //Gets current stage
        Stage currentStage = (Stage) bt_login.getScene().getWindow();
        // do what you have to do
        currentStage.close();
    } catch(Exception e) {
        e.printStackTrace();
    }
    }
    
}
