package app;

import javax.swing.*;
import java.awt.event.*;
import java.io.File;
import java.awt.*;

/**
 * MenuDirectorio
 */
public class MenuDirectorio extends JFrame implements ActionListener {
    private JButton seleccion;
    private JButton info;
    private JButton crear;
    private JButton salir;
    private JLabel peticion;
    private JTextField ruta;
    private JButton ok;
    private File archivo;
    private JLabel respuesta;
    private JLabel dir;
    private JTextField nombreDir;
    private File nuevoDir;
    private JLabel infoDir;
    private boolean creado;
    private String informacion;

    public MenuDirectorio() {
        super("Menú de directorios");
        this.setLayout(null);

        seleccion = new JButton("Seleccionar");
        seleccion.setSize(seleccion.getPreferredSize());
        seleccion.setLocation(20, 10);
        seleccion.addActionListener(this);
        add(seleccion);

        info = new JButton("Información");
        info.setSize(info.getPreferredSize());
        info.setLocation(150, 10);
        info.setEnabled(false);
        info.addActionListener(this);
        add(info);

        crear = new JButton("Crear directorio");
        crear.setSize(crear.getPreferredSize());
        crear.setLocation(280, 10);
        crear.setEnabled(false);
        crear.addActionListener(this);
        add(crear);

        salir = new JButton("Salir");
        salir.setSize(salir.getPreferredSize());
        salir.setLocation(440, 10);
        salir.addActionListener(this);
        add(salir);

        peticion = new JLabel("Introduce una ruta");
        peticion.setSize(peticion.getPreferredSize());
        peticion.setLocation(20, 60);
        peticion.setVisible(false);
        add(peticion);

        ruta = new JTextField(30);
        ruta.setSize(ruta.getPreferredSize());
        ruta.setLocation(100, 90);
        ruta.setVisible(false);
        ruta.addActionListener(this);
        add(ruta);

        ok = new JButton("OK");
        ok.setSize(ok.getPreferredSize());
        ok.setLocation(450, 85);
        ok.setVisible(false);
        ok.addActionListener(this);
        add(ok);

        respuesta = new JLabel();
        respuesta.setSize(500,500);
        respuesta.setLocation(20, 115);
        respuesta.setVisible(false);
        add(respuesta);

        dir = new JLabel("Introduce el nombre de la nueva carpeta");
        dir.setSize(dir.getPreferredSize());
        dir.setLocation(20, 150);
        dir.setVisible(false);
        add(dir);

        nombreDir = new JTextField(20);
        nombreDir.setSize(nombreDir.getPreferredSize());
        nombreDir.setLocation(100, 170);
        nombreDir.setVisible(false);
        nombreDir.addActionListener(this);
        add(nombreDir);

        infoDir = new JLabel("El directorio ha sido creado con éxito");
        infoDir.setSize(infoDir.getPreferredSize());
        infoDir.setLocation(20,210);
        infoDir.setVisible(false);
        add(infoDir);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == seleccion) {
            ruta.setText("");
            peticion.setVisible(true);
            ruta.setVisible(true);
            ok.setVisible(true);
            
            respuesta.setVisible(false);
            dir.setVisible(false);
            nombreDir.setVisible(false);
            infoDir.setVisible(false);
        }
        if (e.getSource() == ok || e.getSource() == ruta) {
            archivo = new File(ruta.getText());
            if (!archivo.exists() || !archivo.canWrite()) {
                respuesta.setText("La ruta no es válida. Introducela de nuevo");
                respuesta.setVisible(true);
                respuesta.setForeground(new Color(255, 0, 0));
                ruta.setText("");
                info.setEnabled(false);
                crear.setEnabled(false);
            } else {
                respuesta.setForeground(new Color(0,255,0));
                respuesta.setText("Ruta válida");
                respuesta.setVisible(true);
                info.setEnabled(true);
                crear.setEnabled(true);
            }
        }
        if (e.getSource() == info) {
            respuesta.setForeground(new Color(0,0,0));
            respuesta.setVisible(true);
            dir.setVisible(false);
            nombreDir.setVisible(false);
            infoDir.setVisible(false);

            if (archivo.isFile()) {
                respuesta.setText("<html><body>Nombre: "+archivo.getName()+"<br>Directorio: "+archivo.getPath()+"<br></body></html>");
                respuesta.setSize(respuesta.getPreferredSize());
            }
            if (archivo.isDirectory()) {
                informacion="<html><body>";
                for (File lista : archivo.listFiles()) {
                    if (lista.isFile()) {
                    informacion=informacion.concat("Nombre: " + lista.getName() + "  -----   Tamaño: " + (lista.length()/1024) + "KB<br>");
                    }
                }
                informacion=informacion.concat("</body></html>");
                respuesta.setText(informacion);
                respuesta.setSize(respuesta.getPreferredSize());
            }
        }
        if (e.getSource() == crear) {
            respuesta.setVisible(false);
            dir.setVisible(true);
            nombreDir.setVisible(true);
            nombreDir.setText("");
        }
        if(e.getSource() == nombreDir){
            nuevoDir = new File("/" + ruta.getText() + "/" + nombreDir.getText());
            creado = nuevoDir.mkdir();
            infoDir.setVisible(true);
            if (creado) {
                infoDir.setForeground(new Color(0,255,0));
                infoDir.setText("El directorio ha sido creado con éxito");
            } else {
                infoDir.setForeground(new Color(255,0,0));
                infoDir.setText("Fallo al crear el directorio");
            }
        }
        if (e.getSource() == salir) {
            System.exit(0);
        }

    }
}