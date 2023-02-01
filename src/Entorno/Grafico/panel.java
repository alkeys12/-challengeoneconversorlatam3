package Entorno.Grafico;


import procesos.cambioDivisa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.List;
import java.util.Map;

/***
 * @author Alexander Aviles
 */
public class panel extends JPanel {

    private JTextField cant1;
    private JLabel cant2;
    private JTextPane tipos;
    private String tipo1, tipo2;
    private JButton ver;
    private JPanel listaMenu;
    private JMenuBar menu;
    private JMenuBar menu2;
    private JLabel picture;
    private ImageIcon iconosAux;
    private ImageIcon iconoLista[];
    private int x, y;//tamaño de imagens

    public panel() {
        try {
            setLayout(new GridBagLayout());
            crearElementos();
            agregarEntorno();
            cargarImagenes();
            Colorear(true);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void crearElementos() {
        cant1 = new JTextField("Cantidad", SwingConstants.CENTER);
        cant2 = new JLabel("", SwingConstants.CENTER);
        listaMenu = new JPanel();
        tipos = new JTextPane();
        tipos.setAutoscrolls(false);
        iconoLista = new ImageIcon[6];
        ver = new JButton();
        picture = new JLabel("");
        menu2 = new JMenuBar();
        menu = new JMenuBar();
        x = 80;
        y = 80;
        listaMenu.setLayout(new BorderLayout());
        listaMenu.add(picture, BorderLayout.NORTH);
        listaMenu.add(menu, BorderLayout.CENTER);
        listaMenu.add(menu2, BorderLayout.SOUTH);
    }

    public void cargarImagenes() throws Exception {
        File l = new File("src/Multimedia");
        File[] archivos = l.listFiles();
        int i = 0;
        for (File arc : archivos) {
            iconosAux = new ImageIcon("src/Multimedia/" + arc.getName());
            iconoLista[i] = new ImageIcon(iconosAux.getImage().getScaledInstance(
                    x, y, Image.SCALE_DEFAULT));
            i++;
        }
    }


    public void agregarEntorno() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(listaMenu, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        add(tipos, gbc);
        //entrada de datos
        gbc.gridx = 0;
        gbc.gridy = 25;
        add(cant1, gbc);
        //salida de datos
        gbc.gridx = 1;
        gbc.gridy = 25;
        add(cant2, gbc);
        //boton para ver el resultado
        gbc.gridx = 0;
        gbc.gridy = 35;
        add(ver, gbc);

    }

    public void Colorear(boolean v) {
        if (v) {
            setBackground(new Color(37, 152, 250));
            cant1.setBackground(new Color(30, 169, 248));
            cant1.setForeground(Color.white);
            cant2.setForeground(Color.white);
            cant2.setBackground(new Color(30, 169, 248));
            tipos.setForeground(Color.white);
            tipos.setBackground(new Color(30, 169, 248));
            // tipos.setFocusPainted(false);
            // tipos.setBorderPainted(false);
            // tipos.setContentAreaFilled(false);
            ver.setBackground(new Color(0, 75, 217));
            ver.setForeground(Color.white);
            ver.setFocusPainted(false);
            ver.setBorderPainted(false);
            ver.setContentAreaFilled(false);
            ver.setIcon(iconoLista[3]);
            picture.setIcon(iconoLista[2]);
        }
    }


    public String getCant1() {
        return cant1.getText();
    }

    public void setCant1(String cant1) {
        this.cant1.setText(cant1);
    }


    public void setCant2(String cant2) {
        this.cant2.setText(cant2);
    }


    public void setTipos() {
        this.tipos.setText(tipo1 + "/" + tipo2);
    }

    public void setListaAction(List lista) {
        escuchas ev;
        JMenu monedas = new JMenu("Divisa");
        JMenu monedas1 = new JMenu("a divisa");
        monedas.setForeground(new Color(30, 169, 248));
        monedas1.setForeground(new Color(30, 169, 248));
        for (int i = 0; i < lista.size(); i++) {
            //se agrega los nombres mediante casting de lista
            monedas.add(new JMenuItem(new escuchas((String) lista.get(i))));
            //se agrego el punto para diferencia el evento porque se tratara con el nombre
            //las diferencia de los eventos
            monedas1.add(new JMenuItem(new escuchas((String) lista.get(i) + ".")));
        }
        monedas.add(new JMenuItem(new escuchas("Dolar")));
        monedas1.add(new JMenuItem(new escuchas("Dolar" + ".")));
        menu2.add(monedas1);
        menu.add(monedas);
    }

    public void botonAction() {
        /**
         * se trata tosdas los action del botones
         */
        //boton ver se trata del boton que realiza el proceso de cambio de divisas
        ver.addActionListener(e -> {
            cambioDivisa camv = new cambioDivisa();
            System.out.println(tipo1);
            System.out.println(tipo2.replace(".", ""));
            System.out.println(cant1.getText());
            float cambioDivisas = camv.getCambio(Float.valueOf(cant1.getText()), tipo1, tipo2.replace(".", ""));
            setCant2(String.valueOf(cambioDivisas));
        });
    }


    public void mouseEscucha() {
        /**
         * se asigna los escuchas de mouse a los botones para personalizar y
         *
         */
        MouseListener mouseEventos=new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
            cant1.setText("");
            }
        };
        cant1.addMouseListener(mouseEventos);
        ver.setToolTipText("Convertir Divisa");
        cant1.setToolTipText("Ingresar Divisa");
        menu.setToolTipText("selecionar Divisa ");
        menu2.setToolTipText("selecionar Divisa ");
    }


    private class escuchas extends AbstractAction {

        public escuchas(String nombre) {
            putValue(javax.swing.Action.NAME, nombre);
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            AsignacionDetipos(e.getActionCommand());
        }

        private void AsignacionDetipos(String e) {
            /***
             * se asigna datos de monedas al texto y se comprueva si son iguales para evitar cualquier erro
             * e = a getActionCommand() donde se compara el texto del Action
             */
            //el . significa el cambio donde se dirige la moneda por asi desir xd
            if (e.contains(".")) {
                tipo2 = e;
            } else {
                tipo1 = e;
            }
            try {
                if (tipo1 != null && tipo2 != null && tipo2.contains(tipo1)) {
                    JOptionPane.showMessageDialog(null, "LAS DIVISAS SON IGUALES");
                } else {
                    setTipos();
                }
            } catch (Exception error) {
                System.out.println("Error " + error.getMessage());
            }
        }

    }


}

