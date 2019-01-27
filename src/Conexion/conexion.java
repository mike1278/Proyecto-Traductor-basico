package Conexion;

import org.controlsfx.control.Notifications;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;

public class conexion {

    private String driver = "com.mysql.jdbc.Driver";
    Connection conectar = null;
    String url="jdbc:mysql://localhost/lenguajes",username="root";

    public Connection conexion() {
        try {
            Class.forName(driver);
            conectar = DriverManager.getConnection(url,username,"");
        } catch (ClassNotFoundException | SQLException e) {
            Notifications.create().darkStyle().text(e.getMessage()).title("Error al conectar a la base de datos").showError();
        }
        return conectar;
    }
}
