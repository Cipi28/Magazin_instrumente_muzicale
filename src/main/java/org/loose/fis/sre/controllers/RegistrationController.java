package org.loose.fis.sre.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.Main;
import org.loose.fis.sre.exceptions.LoginExceptions;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class RegistrationController {

    @FXML
    private Text registrationMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private Hyperlink gotoLOGIN;
    @FXML
    private ChoiceBox role;
    private Parent root;
    private Stage stage;

    @FXML
    public void initialize() {
        role.getItems().addAll("Buyer", "Seller");
    }

    @FXML
    public void handleRegisterAction() {
        if(usernameField.getText().isEmpty() || passwordField.getText().isEmpty()) {
            registrationMessage.setText("Complete All fields!");
        } else {
            try {
                UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
                registrationMessage.setText("Account created successfully!");
            } catch (UsernameAlreadyExistsException e) {
                registrationMessage.setText(e.getMessage());
            }
        }
    }

    public void handleLoginAc(ActionEvent event) throws Exception {
        this.root = (Parent)FXMLLoader.load(getClass().getClassLoader().getResource("LoginPage.fxml"));
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(this.root);
        this.stage.setTitle("Login");
        this.stage.setScene(scene);
        this.stage.show();
    }


}
