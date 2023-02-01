package procesos;

import javax.swing.*;

public class cambioDivisa {
    private float cambio;


    public float getCambio(float cantMoneda, String monedaCambio, String monedaDestino) {
        lecturaDatos leer = new lecturaDatos();
        if (!monedaCambio.equals("Dolar") && !monedaDestino.equals("Dolar")) {
            float dolarcambio = leer.getDivisas().get(monedaCambio) * cantMoneda;
            float dolarDestino = leer.getDivisas().get(monedaDestino);
            return dolarcambio / dolarDestino;
        } else if (monedaCambio.equals("Dolar")) {
            return cantMoneda/leer.getDivisas().get(monedaDestino);
        } else {
            return leer.getDivisas().get(monedaCambio) * cantMoneda;
        }


    }


}
