package ControlEmployees.infra;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    private ConnectionFactory(){}

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection("jdbc:postgresql://localhost/funcionarios", "postgres", "postgres");
        } catch (SQLException error) {
            throw new RuntimeException(error);
        }
    }
}
