package Entorno.Grafico;

import com.sun.jdi.request.ExceptionRequest;

import javax.swing.*;
import java.awt.*;

/***
 * @author Alexander Aviles
 */
public class ventana extends JFrame {
    public ventana() {
        try{
            Image icon = new ImageIcon(
                    "src/Multimedia/ico/icono_app.png").getImage();
            setIconImage(icon);
        }catch (Exception e){
            e.printStackTrace();
        }
    setTitle("Conversor de divisas");
    }

    public void iniciar(){
        setVisible(true);
        setSize(240, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        //setResizable(false);
        pocicionVentana(true);


    }

    public void pocicionVentana(boolean v) {
        /***
         * un true para poner la ventana en el centro
         */
        if (v) {
            Dimension pant = Toolkit.getDefaultToolkit().getScreenSize();
            setLocation(pant.width / 4, pant.height / 4);
        }
    }



}
