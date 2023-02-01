package procesos;

import java.io.File;
import java.util.*;

public class lecturaDatos {
    private String url = "src/monedas/datos/MonedasBaseDatos.csv";
    private Map<String, Float> divisas;
    private List<String> Nombres=new ArrayList<>();

    public lecturaDatos() {
        divisas = new HashMap<>();
        textoProcesado(lectura());
    }

    public Map<String, Float> getDivisas() {
        return divisas;
    }

    public void textoProcesado(List datos) {
        /**
         * procesa los datos y guarda en el vector map
         */

        String CapturadorAux = "";
        int contador = 0;
        String[] vaux = new String[2];

        for (int i = 1; i < datos.size(); i++) {
            String Capturador = (String) datos.get(i);
            for (int j = 0; j < Capturador.length(); j++) {
                /*filtrador de datos para moneda.csv xd*/
                if (Capturador.charAt(j) != ',') {
                    CapturadorAux += Capturador.charAt(j);
                }
                if (Capturador.charAt(j) == ',' || j == Capturador.length() - 1) {
                    contador++;
                }
                if (contador == 3) {
                    CapturadorAux = "";
                    contador++;
                }
                if (contador == 1) {
                    vaux[0] = CapturadorAux;
                    CapturadorAux = "";
                    contador++;
                } else if (contador == 5) {
                    vaux[1] = CapturadorAux;
                    contador = 0;
                }
            }
            CapturadorAux = "";
            divisas.put(vaux[0], Float.valueOf(vaux[1]));
            Nombres.add(vaux[0]);
        }


    }

    public List lectura() {
        List<String> st = new ArrayList<>();
        try {
            File file = new File(url);
            Scanner salidaDatos = new Scanner(file);
            while (salidaDatos.hasNext()) {
                st.add(salidaDatos.nextLine());
            }
            file.exists();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return st;
    }

    public List<String> getNombres() {
        return Nombres;
    }
}
