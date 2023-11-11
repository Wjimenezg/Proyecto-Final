
package jugueteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

public class Inventario {
private int idInventario;
    private int idJuguete;
    private int cantidad;
    private String clasiJuguete;

    public Inventario(int idInventario, int idJuguete, int cantidad, String clasiJuguete) {
        this.idInventario = idInventario;
        this.idJuguete = idJuguete;
        this.cantidad = cantidad;
        this.clasiJuguete = clasiJuguete;
    }

    public int getIdInventario() {
        return idInventario;
    }

    public void setIdInventario(int idInventario) {
        this.idInventario = idInventario;
    }

    public int getIdJuguete() {
        return idJuguete;
    }

    public void setIdJuguete(int idJuguete) {
        this.idJuguete = idJuguete;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getClasiJuguete() {
        return clasiJuguete;
    }

    public void setClasiJuguete(String clasiJuguete) {
        this.clasiJuguete = clasiJuguete;
    }

    // Actualizar la cantidad del inventario en la base de datos
    public void actualizarInventarioEnBaseDeDatos(Connection connection) {
        try {
            String sql = "UPDATE inventario SET cantidad = ? WHERE clasiJuguete = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, this.cantidad);
                statement.setString(2, this.clasiJuguete);
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
