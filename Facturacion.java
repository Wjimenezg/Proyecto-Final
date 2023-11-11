
package jugueteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class Facturacion {
        private ConexionDB conexionDB;

    public Facturacion() throws SQLException {
        conexionDB = new ConexionDB();
    }

    public void realizarVenta() {
        try {
            Scanner scanner = new Scanner(System.in);

            // Mostrar lista de juguetes disponibles
            System.out.println("Lista de juguetes disponibles:");
            ConsultaDB consultaDB = new ConsultaDB();
            consultaDB.consultarJuguete();

            System.out.print("Ingrese el ID del juguete que desea comprar: ");
            int idJuguete = scanner.nextInt();

            System.out.print("Cantidad a comprar: ");
            int cantidadCompra = scanner.nextInt();

            // Consultar la cantidad disponible en el inventario
            String sqlInventario = "SELECT cantidad FROM inventario WHERE idJuguete = " + idJuguete;
            ResultSet resultadoInventario = conexionDB.consultar(sqlInventario);

            if (resultadoInventario.next()) {
                int cantidadDisponible = resultadoInventario.getInt("cantidad");

                if (cantidadDisponible >= cantidadCompra) {
                    // Consultar el precio unitario del juguete
                    String sqlPrecio = "SELECT precio FROM juguete WHERE idJuguete = " + idJuguete;
                    ResultSet resultadoPrecio = conexionDB.consultar(sqlPrecio);

                    if (resultadoPrecio.next()) {
                        //aca realizamos la actualizacion a la tabla inventario
                        double precioUnitario = resultadoPrecio.getDouble("precio");
                        String nombre = consultaDB.obtenerTipoJuguetePorId(idJuguete);
                        int resta = cantidadDisponible - cantidadCompra;
                        String Actualiza = "UPDATE inventario SET cantidad= "+resta+" WHERE idJuguete="+idJuguete;
                        conexionDB.consultar(Actualiza);
                        double total = cantidadCompra*precioUnitario;
                        String Insertar = "INSERT INTO facturacion (idJuguete, nombre, cantidad, total) "
                        + "VALUES ("+idJuguete+",'"+nombre+"',"+cantidadCompra+","+total+")";
                        conexionDB.consultar(Insertar);
                        System.out.println("|*************************************************|");
                        System.out.println("\nVenta realizada con exito.\n");
                        System.out.println("|*************************************************|");
                    } else {
                        System.out.println("|*************************************************|");
                        System.out.println("\nNo se pudo obtener el precio del juguete.\n");
                        System.out.println("|*************************************************|");
                    }
                } else {
                    System.out.println("|*************************************************|");
                    System.out.println("\nNo hay suficientes unidades en inventario.\n");
                    System.out.println("|*************************************************|");
                }
            } else {
                System.out.println("|*************************************************|");
                System.out.println("\nEl juguete seleccionado no existe en el inventario.\n");
                System.out.println("|*************************************************|");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
