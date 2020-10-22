package student.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import student.model.Student;
import student.model.StudentDao;
import student.model.ConnectionFactory;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.util.ResourceBundle;

public class FormController implements Initializable {

    Connection connection;
    StudentDao dao = new StudentDao();
    HBox hBox = new HBox();
    Button editButton = new Button("Edit");
    Button deleteButton = new Button("Delete");

    @FXML
    private TextField tf_search;

    @FXML
    private TextField tf_id;

    @FXML
    private TextField tf_fullName;

    @FXML
    private TextField tf_adress;

    @FXML
    private Button btn_delete;

    @FXML
    private Button btn_update;

    @FXML
    private Button btn_create;

    @FXML
    private TableView<Student> tv_students;

    @FXML
    private TableColumn col_operations;

    @FXML
    private TableColumn<Student, Integer> col_id;

    @FXML
    private TableColumn<Student, String> col_fullName;

    @FXML
    private TableColumn<Student, String> col_address;

    @FXML
    private Button btn_new;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        connection = new ConnectionFactory().getConnection();
        showStudents();
    }

    @FXML
    void handMouseAction(MouseEvent event) {
        Object obj = tv_students.getSelectionModel().getSelectedItem();
        if (obj != null) {
            Student student = (Student) tv_students.getSelectionModel().getSelectedItem();
            tf_id.setText(student.getId().toString());
            tf_fullName.setText(student.getFullName());
            tf_adress.setText(student.getAddress());
        }
    }

    @FXML
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btn_update) {
            if (tf_id.getText() != "") {
                updateStudent();
            }
        }

        if (event.getSource() == btn_create) {
            createStudent();
        }

        if (event.getSource() == btn_delete) {
            if (tf_id.getText() != "") {
                dao.deleteUser(Integer.valueOf(tf_id.getText()));
                clearFields();
            }
        }
        showStudents();
    }

    @FXML
    void handleOnKeyReleased(KeyEvent event) {

        showStudents();

    }


    public void showStudents() {
        ObservableList<Student> list = dao.selectUser(tf_search.getText());

        col_id.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        col_fullName.setCellValueFactory(new PropertyValueFactory<Student, String>("fullName"));
        col_address.setCellValueFactory(new PropertyValueFactory<Student, String>("address"));
        tv_students.setItems(list);
    }

    private void createStudent() {
        Student student = new Student(tf_fullName.getText(), tf_adress.getText());
        dao.insertUser(student);
    }

    private void updateStudent() {
        Student student = new Student(Integer.valueOf(tf_id.getText()), tf_fullName.getText(), tf_adress.getText());
        dao.updateUser(student);
    }

    private void clearFields() {
        tf_id.setText("");
        tf_fullName.setText("");
        tf_adress.setText("");
    }
}
