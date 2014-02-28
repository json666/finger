package gob.bo.adauana.sga.finger.formulario;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.digitalpersona.onetouch.DPFPDataPurpose;
import com.digitalpersona.onetouch.DPFPFeatureSet;
import com.digitalpersona.onetouch.DPFPGlobal;
import com.digitalpersona.onetouch.DPFPSample;
import com.digitalpersona.onetouch.DPFPTemplate;
import com.digitalpersona.onetouch.capture.DPFPCapture;
import com.digitalpersona.onetouch.capture.event.DPFPDataAdapter;
import com.digitalpersona.onetouch.capture.event.DPFPDataEvent;
import com.digitalpersona.onetouch.processing.DPFPEnrollment;
import com.digitalpersona.onetouch.processing.DPFPFeatureExtraction;
import com.digitalpersona.onetouch.processing.DPFPImageQualityException;

public class HuellaApplet extends JApplet implements ActionListener{

	
	private DPFPCapture Lector = DPFPGlobal.getCaptureFactory().createCapture();
    private DPFPEnrollment Reclutador = DPFPGlobal.getEnrollmentFactory().createEnrollment();
    private DPFPTemplate template;
    public static String TEMPLATE_PROPERTY = "template";
    public DPFPFeatureSet featuresinscripcion;
    public DPFPFeatureSet featuresverificacion;
    Image image;
    int j = 0;
    
    private JPanel pnlPrincipal;
    private JButton btnAbrirArch, btnAceptar, btnSalir;

   
    @Override
    public void init() {
         setSize(200, 200);
         setBackground(Color.gray);
         Iniciar();
         starts();
                
         
    }

    class ButtonListener implements ActionListener {
		  // The "action listener" for the button is defined
		  // by this nested anonymous class.
	  public void actionPerformed(ActionEvent evt) {
			  // This method is called to respond when the user
			  // presses the button.  It displays a message in 
			  // a dialog box, along with an "OK" button which
			  // the user can click to dismiss the dialog box.
		  String message = "Another hello from Swing.";
		  JOptionPane.showMessageDialog(null, message);
	  } // end actionPerformed()
} // end class ButtonListener
    @Override
    public void paint(Graphics g) {
         g.drawImage(image, 0, 0, 200, 200, this);
    }

    @Override
    public void stop() {
         Lector.stopCapture();
    }

    @Override
    public void update(Graphics g) {
         paint(g);
    }

    public void Iniciar() {
         Lector.addDataListener(new DPFPDataAdapter() {
              @Override
              public void dataAcquired(final DPFPDataEvent e) {
                   j++;
                   if (j <= 4) {
                        System.out.println("Grabo" + j);
                        ProcesarCaptura(e.getSample());
                        repaint();
                   }
              }
         });
    }

    public void ProcesarCaptura(DPFPSample sample) {
         featuresinscripcion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_ENROLLMENT);
         featuresverificacion = extraerCaracteristicas(sample, DPFPDataPurpose.DATA_PURPOSE_VERIFICATION);

         if (featuresinscripcion != null) {
              try {
                   Reclutador.addFeatures(featuresinscripcion);
                   image = CrearImagenHuella(sample);
                   Reclutador.clear();
              } catch (DPFPImageQualityException ex) {
                   System.out.println(ex);
              }

         }
    }

    public DPFPFeatureSet extraerCaracteristicas(DPFPSample sample, DPFPDataPurpose purpose) {
         try {
              DPFPFeatureExtraction extractor = DPFPGlobal.getFeatureExtractionFactory().createFeatureExtraction();

              return extractor.createFeatureSet(sample, purpose);
         } catch (DPFPImageQualityException ex) {
              System.out.println(ex);
              return null;
         }
    }

    public Image CrearImagenHuella(DPFPSample sample) {
         return DPFPGlobal.getSampleConversionFactory().createImage(sample);
    }

    public void starts() {
         pnlPrincipal = new JPanel();
         btnSalir = new JButton("Salir");

         setSize(500, 300);
         pnlPrincipal.setLayout(null);

         //Posicionando en la pantalla
         btnSalir.setBounds(350, 250, 100, 25);

         //Adicionando controles en el Applet
         
         pnlPrincipal.add(btnSalir);
         setContentPane(pnlPrincipal);
         btnSalir.addActionListener(this);
         setVisible(true);
    }

    public void stops() {
         Lector.stopCapture();
    }

    public DPFPTemplate getTemplate() {
         return template;
    }

    public void setTemplate(DPFPTemplate template) {
         DPFPTemplate old = this.template;
         this.template = template;
         firePropertyChange(TEMPLATE_PROPERTY, old, template);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == btnSalir) {
            //String x= this.cambia();
            Lector.startCapture();
        }
	}
}

