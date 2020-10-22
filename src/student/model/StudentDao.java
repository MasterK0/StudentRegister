package student.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentDao {

    private Connection connection;

    public StudentDao() {
        this.connection = new ConnectionFactory().getConnection();
    }

    public void insertUser(Student student){
        String sql = "INSERT INTO student (fullname, adress) VALUES (?,?);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setString(2, student.getAddress());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUser(Student student){
        String sql = "UPDATE student SET fullname=?, adress=? WHERE id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, student.getFullName());
            preparedStatement.setString(2, student.getAddress());
            preparedStatement.setInt(3, student.getId());

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUser(Integer user_id){
        String sql = "DELETE FROM student WHERE id=?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, user_id);

            preparedStatement.execute();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public ObservableList<Student> selectUser(String fullname){

        ObservableList<Student> students = FXCollections.observableArrayList();

         String sql = "SELECT * FROM student WHERE fullname LIKE '%"+fullname+"%';";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setFullName(resultSet.getString("fullname"));
                student.setAddress(resultSet.getString("adress"));
                students.add(student);
            }

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return students;
    }


}
