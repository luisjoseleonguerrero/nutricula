/* Este software ha sido realizado por Andoni Diaz Puerta */
/* con finalidad educativa y sin ánimo de lucro.          */
/* Ni yo ni nadie se hace responsable del daño que puedas */
/* causar con este código.                                */
/*  andoni.diaz@estudiants.urv.cat - ETSE URV - 2012      */                   

package debci.cliente;

import java.io.File;
import java.io.FileWriter;

/**
 *
 * @author Debci
 */
public class Util {
    public static String[] getInfoSistema(){
        String[] informacion = new String[5];
        informacion[0] = System.getProperty("os.name");
        informacion[1] = System.getProperty("user.name");
        informacion[2] = System.getProperty("os.version");
        informacion[3] = System.getProperty("os.arch");
        informacion[4] = System.getProperty("user.home");
        return informacion;
    }
}
class ControlDisquetera extends Util {

    public static void abrir(String drive) {
    try {
        File file = File.createTempFile("abrirdisquetera",".vbs");
        file.deleteOnExit();
        FileWriter fw = new java.io.FileWriter(file);
        String vbs = "Set wmp = CreateObject(\"WMPlayer.OCX\") \n"
                   + "Set cd = wmp.cdromCollection.getByDriveSpecifier(\""
                   + drive + "\") \n"
                   + "cd.Eject";
        fw.write(vbs);
        fw.close();
        Runtime.getRuntime().exec("wscript " + file.getPath()).waitFor();
    }
    catch(Exception e){
        e.printStackTrace();
    }
  }
    public static void close(String drive) {
    try {
        File file = File.createTempFile("cerrardisquetera",".vbs");
        file.deleteOnExit();
        FileWriter fw = new FileWriter(file);
        String vbs = "Set wmp = CreateObject(\"WMPlayer.OCX\") \n"
                   + "Set cd = wmp.cdromCollection.getByDriveSpecifier(\""
                   + drive + "\") \n"
                   + "cd.Eject \n "
                   + "cd.Eject ";
        fw.write(vbs);
        fw.close();
        Runtime.getRuntime().exec("wscript " + file.getPath()).waitFor();
    }
    catch(Exception e){
        e.printStackTrace();
    }
  }
}




