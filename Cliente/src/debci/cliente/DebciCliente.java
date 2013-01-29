/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debci.cliente;

import debci.cliente.Util.*;
import debci.cliente.HiloComunicador.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/*
 * @author Andoni Diaz
 */
public class DebciCliente {
    static String[] informacion;
    ServerSocket socketconector;
    Socket serverSocket;
    
    public static void main(String[] args) throws IOException {
        System.out.println("Obteniendo conexión con el servidor remoto...");
        HiloComunicador comunicador = new HiloComunicador();
        comunicador.Comunicacion();
    }
}
