package servidor;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

public class ServidorImagenes extends JFrame implements Runnable {
    ServerSocket s;
    Socket con;
    ImageIcon img;
    Image imgRedimensionada;
    JLabel lblImg, lblEst;
    Boolean est = false;
    ObjectInputStream entrada;
    DataOutputStream salida;
    DataInputStream entradaCoordenadas;
    ByteArrayInputStream bufferImg;
    BufferedImage imagen;
    JButton btnIni;
    JTextField txtPuerto;
    Thread t;
    int x,y;
    int coordenadasX, coordenadasY;
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    
    public ServidorImagenes(){
        ini();
    }
    
    final void ini(){
       
        
        JPanel p = new JPanel();
        
        this.getContentPane().add(p, BorderLayout.NORTH);
        
        p.setLayout(new FlowLayout());
        
        
        lblImg = new JLabel();
        

        this.getContentPane().add(lblImg, BorderLayout.CENTER);
        
        this.setTitle("Servidor");
        this.setSize(400, 100);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);    
        
    }    
    
    void iniServ(){
        try {
            
            s = new ServerSocket(667);
            System.out.println("Esperando conexion cliente escritorio...");
            System.out.println("Configurando eventos del ratón...");
            
                //Obtenemos la posición del ratón en el jLabel de la representación del escritorio
                lblImg.addMouseMotionListener(new MouseMotionListener() {
                    public void mouseMoved(MouseEvent evt) {
                        x = evt.getX();
                        y = evt.getY();
                        
                    }
                    public void mouseDragged(MouseEvent evt) {
                
                    }
                    });
            con = s.accept();
            
            if (con.isConnected()){
                
                this.setBounds(0, 200, 800, 600);
                this.setLocationRelativeTo(null);
                
                est = true;
                if (t == null){
                        t = new Thread(this, "ServidorImagenes");
                        t.start();
                }
            }
        } catch (NumberFormatException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    void finServ()
    {
        est = false;
        try {
            con.close();
            s.close();
            this.dispose();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    void procImagenes()
    {
        try {
            entradaCoordenadas = new DataInputStream(con.getInputStream());
            coordenadasX = entradaCoordenadas.readInt();
            coordenadasY = entradaCoordenadas.readInt();
            
            while(est){
                try{
                    
                    entrada = new ObjectInputStream(con.getInputStream());
                    salida = new DataOutputStream(con.getOutputStream());
                    byte[] bytesImg = (byte[]) entrada.readObject();
                    bufferImg = new ByteArrayInputStream(bytesImg);
                    imagen = ImageIO.read(bufferImg);
                    salida.writeInt((x*coordenadasX)/screenSize.width);
                    salida.writeInt((y*coordenadasY)/screenSize.height);
                    img = new ImageIcon(imagen);
                    imgRedimensionada = img.getImage().getScaledInstance(800, 600, Image.SCALE_FAST);
                    img.setImage(imgRedimensionada);
                    lblImg.setIcon(img);
                }
                catch (IOException | ClassNotFoundException e){
                    System.err.println(e.getMessage());
                    est = false;
               
                    this.setSize(400, 100);
                    this.setLocationRelativeTo(null);
                    this.remove(lblImg);
                }   
            }
        } catch (IOException ex) {
            Logger.getLogger(ServidorImagenes.class.getName()).log(Level.SEVERE, null, ex);   
        }
    }

   

    @Override
    public void run() {
        this.procImagenes();
    }

    
}