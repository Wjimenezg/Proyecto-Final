
package jugueteria;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
public class ConexionDB {
    private Connection conexion;
    
    public ConexionDB() throws SQLException{
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            String url = "jdbc:mariadb://" + "127.0.0.1" + ":" + "3306" + "/" + "jugueteriadb";
            this.conexion = DriverManager.getConnection(url, "root", "S3g23.");
            
        }catch (ClassNotFoundException e) {
            throw new SQLException("Error al cargar el controlador de MariaDB: " + e.getMessage());
        }
    }
    
        public ResultSet consultar(String sql) throws SQLException {
        Statement sentencia = conexion.createStatement();
        return sentencia.executeQuery(sql);
    }
    
        public Connection getConnection() {
        return conexion;
    }
        
        public void cerrar() throws SQLException {
        if (conexion != null) {
            conexion.close();
        }
    }
    
    
}
