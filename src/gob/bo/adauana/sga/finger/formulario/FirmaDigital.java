package gob.bo.adauana.sga.finger.formulario;
/**
 * Created by maticona on 6/02/14.
 */

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class FirmaDigital extends JApplet implements ActionListener {

    private JPanel pnlPrincipal;
    private JButton btnAbrirArch, btnAceptar, btnSalir;
    

    public void init() {
        System.out.println("Inicio init");
    }

    public void start() {

              
        pnlPrincipal = new JPanel();
        btnAbrirArch = new JButton("Abrir Archivo...");
        btnAceptar = new JButton("Aceptar");
        btnSalir = new JButton("Salir");

        setSize(500, 300);
        pnlPrincipal.setLayout(null);

        //Posicionando en la pantalla
        btnAbrirArch.setBounds(250, 50, 150, 25);
        btnAceptar.setBounds(200, 250, 100, 25);
        btnSalir.setBounds(350, 250, 100, 25);

        //Adicionando controles en el Applet
        
        pnlPrincipal.add(btnAbrirArch);
        pnlPrincipal.add(btnAceptar);
        pnlPrincipal.add(btnSalir);
        setContentPane(pnlPrincipal);
        btnAbrirArch.addActionListener(this);
        btnAceptar.addActionListener(this);
        btnSalir.addActionListener(this);
        setVisible(true);
    }

    public void stop() {
        System.out.println("Parando...");
    }

    public void destroy() {
        System.out.println("Destruyendo memoria...");
    }

  
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == btnAbrirArch) {
            System.out.println("boton");
        }
        if (e.getSource() == btnAceptar) {
            //************************************
            
            //*************************************


        }
        if (e.getSource() == btnSalir) {
            //String x= this.cambia();
            System.out.println("asdasd");
            System.exit(0);
        }
    }
}




