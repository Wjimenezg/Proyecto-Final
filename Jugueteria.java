package jugueteria;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
public class Jugueteria {
    public static void main(String[] args) throws SQLException {
        Scanner entrada = new Scanner(System.in);
        ConsultaDB consultaDB = new ConsultaDB();
        Pedido pedido = new Pedido();
        Juguete juguete = new Juguete();
        ConexionDB conexiondb= null;
        
        int opc;
        
        
        do{
        System.out.println("\nFavor seleccione la opcion que desee");
        System.out.println("1. Consultar inventario");
        System.out.println("2. Catalogo");
        System.out.println("3. Vender Juguete");
        System.out.println("4. Realizar pedido");
        System.out.println("5. Eliminar Pedido");
        System.out.println("6. Anadir Juguete Nuevo");
        System.out.println("7. Eliminar Juguete");
        System.out.println("0. Salir");
        opc= entrada.nextInt();
        
        
        switch(opc){
            case 1:
                System.out.println("Inventario de Jugueteria: ");
                //consultaDB.consultarInventario();
                consultaDB.consultaInventario();
                break;
            case 2:
                System.out.println(" Catalogo de Juguetes ");
                consultaDB.consultarJuguete();
                break;
            case 3:
                Facturacion facturacion = new Facturacion();
                facturacion.realizarVenta();
                break;
            case 4:
                pedido.realizarPedido();   
                break;
            case 5:
                pedido.EliminarPedido();   
                break;
            case 6:
                juguete.nuevoJuguete();
                break;
            case 7:
                juguete.eliminarJuguete();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Ingrese una opcion valida");
            
        }
        }while(opc!=0);
        }
}
