package student.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import student.model.ConnectionFactory;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {


    Connection connection = new ConnectionFactory().getConnection();
    PreparedStatement preparedStatement;
    ResultSet resultSet;

    @FXML
    private TextField txt_username;

    @FXML
    private PasswordField txt_password;

    @FXML
    private Button btn_sign;

    @FXML
    private Label lbl_exit;

    @FXML
    private void handleClose(MouseEvent event) {
        //JOptionPane.showMessageDialog(null,"BLALALALLALALLAL");
        System.exit(0);
    }

    @FXML
    public void login(MouseEvent event) {

        //region check
        String username = txt_username.getText();
        String password = txt_password.getText();

        if (username.equals("") && password.equals("")) {
            JOptionPane.showMessageDialog(null, "Username or Password Blank");
        } else {
            try {
                preparedStatement = connection.prepareStatement("SELECT * FROM  users WHERE username=? AND password=?;");
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                resultSet = preparedStatement.executeQuery();

                if (resultSet.next()) {
                    loadWindow();
                } else {
                    JOptionPane.showMessageDialog(null, "Fail");
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        //endregion

        Stage stage = (Stage) btn_sign.getScene().getWindow();
        stage.close();



    }

    public void  loadWindow(){


        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("../view/studentsForm.fxml"));
            Stage primaryStage = new Stage();
            Scene scene = new Scene(root);

            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }



    @Override

    public void initialize(URL location, ResourceBundle resources) {

    }


}
