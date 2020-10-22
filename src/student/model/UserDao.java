package student.model;

import java.sql.Connection;

public class UserDao {

    private Connection connection;

    public UserDao() {
        this.connection = new ConnectionFactory().getConnection();
    }


}
