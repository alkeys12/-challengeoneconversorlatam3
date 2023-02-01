import Entorno.Grafico.*;
import procesos.*;

/*by aviles Moran*/
public class Main {
    public static void main(String[] args) {
        ventana iniciar = new ventana();
        lecturaDatos leer=new lecturaDatos();
        panel panel1 = new panel();
        panel1.setListaAction(leer.getNombres());
        panel1.botonAction();
        panel1.mouseEscucha();
        iniciar.iniciar();
        iniciar.add(panel1);
        //iniciar.pack();
        // cambioDivisa iniciarCambios = new cambioDivisa();



    }
}
