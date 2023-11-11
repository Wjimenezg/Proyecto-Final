package jugueteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Juguete {
        ConsultaDB consultaDB = new ConsultaDB();
    private ConexionDB conexiondb;

    public Juguete() throws SQLException {
        conexiondb = new ConexionDB();
    }

    public void nuevoJuguete() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del Juguete:");
        String tipoJuguete = scanner.nextLine();
        System.out.println("Ingrese el tamaño del nuevo Juguete:");
        String tamanio = scanner.nextLine();
        System.out.println("Ingrese el precio:");
        double precio = scanner.nextDouble();

        // Insertar el nuevo juguete en la tabla 'juguete'
        try {
            String sqlInsercionJuguete = "INSERT INTO juguete (tipoJuguete, tamanio, precio) VALUES (?, ?, ?)";
            PreparedStatement statementJuguete = conexiondb.getConnection().prepareStatement(sqlInsercionJuguete, PreparedStatement.RETURN_GENERATED_KEYS);
            statementJuguete.setString(1, tipoJuguete);
            statementJuguete.setString(2, tamanio);
            statementJuguete.setDouble(3, precio);
            statementJuguete.executeUpdate();

            // Obtener el ID del nuevo juguete automáticamente
            int idDelNuevoJuguete = -1;
            ResultSet generatedKeys = statementJuguete.getGeneratedKeys();
            if (generatedKeys.next()) {
                idDelNuevoJuguete = generatedKeys.getInt(1);
            }

            if (idDelNuevoJuguete != -1) {
                // El usuario ingresa la clasificación del juguete
                System.out.println("Ingrese la clasificación del Juguete:");
                String clasiJuguete = scanner.next();

                // Insertar el nuevo juguete en la tabla 'inventario' con una cantidad inicial
                int cantidadInicial = 0; // Puedes establecer la cantidad inicial deseada
                String sqlInsercionInventario = "INSERT INTO inventario (idJuguete, cantidad, clasiJuguete) VALUES (?, ?, ?)";
                PreparedStatement statementInventario = conexiondb.getConnection().prepareStatement(sqlInsercionInventario);
                statementInventario.setInt(1, idDelNuevoJuguete);
                statementInventario.setInt(2, cantidadInicial);
                statementInventario.setString(3, clasiJuguete);
                statementInventario.executeUpdate();

                System.out.println("Nuevo juguete agregado con éxito.");
            } else {
                System.out.println("Error al obtener el ID del nuevo juguete.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void eliminarJuguete() {
    System.out.println("--------> LISTADO DE JUGUETES DISPONIBLES <--------");
    consultaDB.consultarJuguete();
    Scanner scanner = new Scanner(System.in);
    System.out.print("Ingrese el ID del Juguete que desea eliminar: ");
    int idJuguete = scanner.nextInt();
    try {
        // Verificar si el juguete existe en la tabla 'juguete'
        String consultaExistencia = "SELECT idJuguete FROM juguete WHERE idJuguete = ?";
        PreparedStatement statementExistencia = conexiondb.getConnection().prepareStatement(consultaExistencia);
        statementExistencia.setInt(1, idJuguete);

        if (statementExistencia.executeQuery().next()) {
            // Eliminar el juguete de la tabla 'inventario'
            String sqlEliminarInventario = "DELETE FROM inventario WHERE idJuguete = ?";
            PreparedStatement statementEliminarInventario = conexiondb.getConnection().prepareStatement(sqlEliminarInventario);
            statementEliminarInventario.setInt(1, idJuguete);
            statementEliminarInventario.executeUpdate();

            // Eliminar el juguete de la tabla 'juguete'
            String sqlEliminarJuguete = "DELETE FROM juguete WHERE idJuguete = ?";
            PreparedStatement statementEliminarJuguete = conexiondb.getConnection().prepareStatement(sqlEliminarJuguete);
            statementEliminarJuguete.setInt(1, idJuguete);
            statementEliminarJuguete.executeUpdate();

            System.out.println("|*************************************************|");
            System.out.println("\nEliminación del juguete realizada con exito.\n");
            System.out.println("|*************************************************|");
        } else {
            System.out.println("|*************************************************|");
            System.out.println("\nEl juguete no existe en la tabla 'Juguete'.\n");
            System.out.println("|*************************************************|");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
        