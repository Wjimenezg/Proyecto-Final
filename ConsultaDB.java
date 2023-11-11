
package jugueteria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
public class ConsultaDB {
    private ConexionDB conexiondb;
    public  ConsultaDB() throws SQLException{
            conexiondb = new ConexionDB();
    }
    public String consultaInventario(){
        String mensaje ="";
        try{
        String inv = "SELECT * FROM inventario";
        String jug = "SELECT * FROM juguete";
        //String p = "UPDATE Inventario SET clasiJuguete = 'Vehiculos' WHERE idInventario = 10";
        ResultSet resultados = conexiondb.consultar(inv);
       // ResultSet P = conexiondb.consultar(p);
        ResultSet ju = conexiondb.consultar(jug);

        
        while (resultados.next()){
            int idJuguete = resultados.getInt("idJuguete");
            int cantidad = resultados.getInt("cantidad");
            String clasiJuguete = resultados.getString("clasiJuguete");            
            
            while (ju.next()){
            int idJugueteJug = ju.getInt("idJuguete");
            if (idJugueteJug == idJuguete) {
            String tipoJuguete = ju.getString("tipoJuguete");
                // Mostrar la información en el orden deseado
                mensaje = "juguete: " + tipoJuguete + " - clasificacion: " 
                        + clasiJuguete + " - cantidad: " + cantidad;
                    System.out.print("juguete: " + tipoJuguete + " - ");
                    System.out.print("clasificacion: " + clasiJuguete + " - ");
                    System.out.println("cantidad: " + cantidad);
                    
                }
                       
            }
            ju.beforeFirst();
        }
    }catch (SQLException e){
        e.printStackTrace();
    } 
        return mensaje;
}
    public void consultarInventario(){
        try{
        String inv = "SELECT * FROM inventario";
        String jug = "SELECT * FROM juguete";
        //String p = "UPDATE Inventario SET clasiJuguete = 'Vehiculos' WHERE idInventario = 10";
        ResultSet resultados = conexiondb.consultar(inv);
       // ResultSet P = conexiondb.consultar(p);
        ResultSet ju = conexiondb.consultar(jug);
        
        while (resultados.next()){
            int idJuguete = resultados.getInt("idJuguete");
            int cantidad = resultados.getInt("cantidad");
            String clasiJuguete = resultados.getString("clasiJuguete");            
            
            while (ju.next()){
            int idJugueteJug = ju.getInt("idJuguete");
            if (idJugueteJug == idJuguete) {
            String tipoJuguete = ju.getString("tipoJuguete");
                // Mostrar la información en el orden deseado
                    System.out.print("juguete: " + tipoJuguete + " - ");
                    System.out.print("clasificacion: " + clasiJuguete + " - ");
                    System.out.println("cantidad: " + cantidad);
                }
                       
            }
            ju.beforeFirst();
                }
            }catch (SQLException e){
        e.printStackTrace();
                } 
        }
    
    public void consultarJuguete(){
        try{
        String jug = "SELECT * FROM juguete";
        ResultSet ju = conexiondb.consultar(jug);
        
        while (ju.next()){
        int idJuguete = ju.getInt("idJuguete");
        String tipoJuguete = ju.getString("tipoJuguete");
        String tamanio = ju.getString("tamanio");
        double precio = ju.getDouble("precio");
        
        System.out.print("id: " + idJuguete + " - ");
        System.out.print("Juguete: " + tipoJuguete + " - ");
        System.out.print("tamanio: " + tamanio + " - ");
        System.out.println("precio: " + precio);
                         
            }
    }catch (SQLException e){
        e.printStackTrace();
    } 
}
        
    public void consultarPedido(){
        try{
        String pedido = "SELECT * FROM pedido";
        ResultSet pedidos = conexiondb.consultar(pedido);
        
        while (pedidos.next()){
        int idPedido = pedidos.getInt("idPedido");
        String idInventario = pedidos.getString("idInventario");
        String cantidad = pedidos.getString("cantidad");
        double precio = pedidos.getDouble("precio");
        
        System.out.print("id: " + idPedido + " - ");
        System.out.print("Juguete: " + idInventario + " - ");
        System.out.print("tamanio: " + cantidad + " - ");
        System.out.println("precio: " + precio);
                         
            }
    }catch (SQLException e){
        e.printStackTrace();
    } 
}
            public String obtenerTipoJuguetePorId(int idJuguete) {
        try {
            String sql = "SELECT tipoJuguete FROM juguete WHERE idJuguete = " + idJuguete;
            ResultSet resultado = conexiondb.consultar(sql);

            if (resultado.next()) {
                String nombre = resultado.getString("tipoJuguete");
                return nombre;
            } else {
                System.out.println("El juguete con ID " + idJuguete + " no existe.");
                return ""; // O un valor apropiado para indicar que el juguete no existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return ""; // O un valor apropiado en caso de error
        }
    }
            
    public int obtenerCantidadInventarioPorIdJuguete(int idJuguete) {
        try {
            String sql = "SELECT cantidad FROM inventario WHERE idJuguete = " + idJuguete;
            ResultSet resultado = conexiondb.consultar(sql);

            if (resultado.next()) {
                return resultado.getInt("cantidad");
            } else {
                System.out.println("El juguete con ID " + idJuguete + " no existe en el inventario.");
                return 0; // O un valor apropiado para indicar que el juguete no existe en el inventario
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; // O un valor apropiado en caso de error
        }
    }
                
    public double obtenerPrecioJuguetePorId(int idJuguete) {
        try {
            String sql = "SELECT precio FROM juguete WHERE idJuguete = " + idJuguete;
            ResultSet resultado = conexiondb.consultar(sql);

            if (resultado.next()) {
                return resultado.getDouble("precio");
            } else {
                System.out.println("El juguete con ID " + idJuguete + " no existe.");
                return 0.0; // O un valor apropiado para indicar que el juguete no existe
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0; // O un valor apropiado en caso de error
        }
    }
        
}

