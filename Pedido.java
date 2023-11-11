    
package jugueteria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Pedido {
    ConsultaDB consultaDB = new ConsultaDB();
    private ConexionDB conexiondb;
    public  Pedido() throws SQLException{
            conexiondb = new ConexionDB();
    }
    public void realizarPedido(){  
        System.out.println("-------->LISTADO DE JUGUETES<--------");
        //ConsultaDB consultaDB = new ConsultaDB();
        consultaDB.consultarJuguete();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del juguete que desea pedir: ");
        int idJuguete = scanner.nextInt();
        System.out.print("Cantidad a pedir: ");
        int cantidadPedido = scanner.nextInt();
        
        try{
        String consulta = "SELECT cantidad FROM inventario WHERE idJuguete ="+idJuguete;
        ResultSet resultados = conexiondb.consultar(consulta);
        if (resultados.next()) {
            double precio = consultaDB.obtenerPrecioJuguetePorId(idJuguete);
            double total = cantidadPedido*precio;
            String Insertar = "INSERT INTO pedido (idInventario, cantidad, precio) "
                        + "VALUES ("+idJuguete+","+cantidadPedido+","+total+")";
                conexiondb.consultar(Insertar);
                
                int cantidadActual = consultaDB.obtenerCantidadInventarioPorIdJuguete(idJuguete);
                int nuevaCantidad = cantidadActual + cantidadPedido;
                String actualizarInventario = "UPDATE inventario SET cantidad = ? WHERE idJuguete = ?";
                PreparedStatement statementActualizarInventario = conexiondb.getConnection().prepareStatement(actualizarInventario);
                statementActualizarInventario.setInt(1, nuevaCantidad);
                statementActualizarInventario.setInt(2, idJuguete);
                statementActualizarInventario.executeUpdate();
                
                System.out.println("|*************************************************|");
                System.out.println("\nPedido realizado con exito.\n");
                System.out.println("|*************************************************|");
        }else {
                System.out.println("|*************************************************|");
                System.out.println("\nEl juguete seleccionado no existe en el inventario.\n");
                System.out.println("|*************************************************|");
            }
    
        }catch (SQLException e){
        e.printStackTrace();
    }
        
    }
    public void EliminarPedido(){
        System.out.println("-------->LISTADO DE JUGUETES DISPONIBLES<--------");
        consultaDB.consultarPedido();
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del Pedido que desea eliminar: ");
        int idPedido = scanner.nextInt();
        try{
        String consulta = "SELECT * FROM pedido WHERE idPedido ="+idPedido;
        ResultSet resultados = conexiondb.consultar(consulta);
        if (resultados.next()) {
            String eliminar = "DELETE FROM pedido WHERE idPedido="+idPedido+";";
                conexiondb.consultar(eliminar);
                
            int idInventario = resultados.getInt("idInventario");
            int cantidadPedido = resultados.getInt("cantidad");

            // Aumentar la cantidad en el inventario
            int cantidadActual = consultaDB.obtenerCantidadInventarioPorIdJuguete(idInventario);
            int nuevaCantidad = cantidadActual - cantidadPedido;

            String actualizarInventario = "UPDATE inventario SET cantidad = ? WHERE idJuguete = ?";
            PreparedStatement statementActualizarInventario = conexiondb.getConnection().prepareStatement(actualizarInventario);
            statementActualizarInventario.setInt(1, nuevaCantidad);
            statementActualizarInventario.setInt(2, idInventario);
            statementActualizarInventario.executeUpdate();
                
                System.out.println("|*************************************************|");
                System.out.println("\nEliminacion de Pedido realizado con exito.\n");
                System.out.println("|*************************************************|");
        }else {
                System.out.println("|*************************************************|");
                System.out.println("\nLa solicitud de pedido no existe en la tabla Pedido.\n");
                System.out.println("|*************************************************|");
            }
    
        }catch (SQLException e){
        e.printStackTrace();
    }
        
    }
}
