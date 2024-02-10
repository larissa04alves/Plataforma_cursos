package Persistencia;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class postgresConnection {
    public Statement statement;
    public ResultSet resultset;

    private static String driver = "org.postgresql.Driver";
    private static String url = "jdbc:postgresql://localhost:5432/Plataforma_cursos";
    private static String usuario = "postgres";
    private static String senha = "admin";
    public static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            conectar();
        }
        return connection;
    }

    public static void conectar() {
        try {
            System.setProperty("jdbc.Drivers", driver);
            connection = DriverManager.getConnection(url, usuario, senha);
            System.out.println("Conectado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(postgresConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao conectar!");
        }
    }

    public void desconectar() {
        try {
            connection.close();
            System.out.println("Desconectado com sucesso!");
        } catch (SQLException ex) {
            Logger.getLogger(postgresConnection.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao desconectar!");
        }
    }
}


