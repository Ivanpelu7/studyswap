package com.example.prueba3000.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
    private Connection conexion;
    private String cadenaConexion = "jdbc:mysql://localhost:3306/studyswap";
    private String nombreUsuario = "root";
    private String password = "121cat6.T";

    public Connection getConexion() {

        try {
            DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
            this.conexion = DriverManager.getConnection(this.cadenaConexion, this.nombreUsuario, this.password);

            return this.conexion;

        } catch (SQLException e) {
            e.printStackTrace();

            return null;
        }
    }

    public void cerrarConexion() {

        try {
            this.conexion.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
