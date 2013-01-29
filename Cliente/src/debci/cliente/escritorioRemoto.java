/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package debci.cliente;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Andoni
 */
public class escritorioRemoto implements Runnable {

    Socket c;
    Boolean est = false;
    ObjectOutputStream salida;
    ByteArrayOutputStream imgSalida;
    Robot r;
    DataInputStream entrada;
    DataOutputStream salidaCoordenadas;
    Thread t;
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    
    void IniCon(){
        try {
            c = new Socket(HiloComunicador.SERVER_HOSTNAME, 667);
            if (c.isConnected()){
                    est = true;
            }
        } catch (NumberFormatException | IOException e){
            System.out.println(e.toString());
            est = false;
        }
    }
    void enviarImagenes(){
        try {
            salidaCoordenadas = new DataOutputStream(c.getOutputStream());
            salidaCoordenadas.writeInt(screenSize.width);
            salidaCoordenadas.writeInt(screenSize.height);
            
                while(est){
                    try{
                        if (c != null)
                        {
                            //Iniciar el Objeto Stream con el cual se enviaran los datos
                            salida = new ObjectOutputStream(c.getOutputStream());
                            entrada = new DataInputStream(c.getInputStream());

                            //crear el Array de Bytes del para guardar la info anterior
                            imgSalida = new ByteArrayOutputStream();
                            //Crear la Imagen y guardarlo en el objeto anterior
                            ImageIO.write(pantallazo(), "jpeg", imgSalida);
                            //Crear un Array de Bytes de la imagen capturada
                            byte[] bytesImg = imgSalida.toByteArray();
                            //Enviar el array de Bytes
                            salida.writeObject(bytesImg);
                            
                            r.mouseMove(entrada.readInt(),entrada.readInt());
                            //vaciar Buffer
                            imgSalida.flush();
                        }
                    }
                    catch (Exception e){
                        System.out.println(e.getMessage());
                        est = false;
                    }
                }
        } catch (IOException ex) {
            Logger.getLogger(escritorioRemoto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        BufferedImage pantallazo() throws Exception {
        //obtener el tamaño de la pantalla
        
        
        //crear un objeto del tipo Rectangle con el tamaño obtenido
        Rectangle screenRectangle = new Rectangle(screenSize);
        //obtener una captura de pantalla del tamaño de la pantalla
        r = new Robot();
        BufferedImage image = r.createScreenCapture(screenRectangle);
        
        return image;
    }
    void finCon()
    {
        est = false;
        try {
            c.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void run() {
        this.enviarImagenes();
        
    }
    public void startSendImages(){
        this.IniCon();
        
        if (est && t == null){
                        t = new Thread(this, "ClienteImagens");
                        
                        t.start();
        }
    }
    public void stopSendImages(){
        this.finCon();
    }
}
