package ui;

import model.Compilador;
import model.Salida;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
//Se genera la ventana
public class Ventana extends JFrame implements ActionListener {
  //Se definen los campos necesarios como el área para el texto, el botón para las acciones y un string
  //De archivo externo
  private final Estilo estilo = new Estilo(this);
  private JTextArea codigo;
  private JButton compilar, cargar, guardar;
  private String archivoExterno = null;


  public Ventana(){
    // Se crea la ventana, habilitando el boton de cerrar de la ventana y contruyendo la interfaz
    estilo.frameEstilo("Compilador", WindowConstants.EXIT_ON_CLOSE, new int[]{0, 0, 1100, 800});
    construirUI();
    this.setVisible(true);
  }

  private void construirUI() {
    //Se manda a crear la interfaz
    //Se crea el botton compilar, dandole su acción y estilo
    compilar = new JButton();
    compilar.addActionListener(this);
    estilo.buttonEstilo(compilar, new int[]{20, 20, 150, 30}, "Compilar");

    //Se crea el botton cargar, dandole su acción y estilo
    cargar = new JButton();
    cargar.addActionListener(this);
    estilo.buttonEstilo(cargar, new int[]{250, 20, 150, 30}, "Cargar");

    //Se crea el botton guardar, dandole su acción y estilo
    guardar = new JButton();
    guardar.addActionListener(this);
    estilo.buttonEstilo(guardar, new int[]{480, 20, 150, 30}, "Guardar");

    //Se crea el area de texto
    codigo = new JTextArea(50, 200);
    estilo.textComponentEstilo(codigo, new int[] {20, 70, 1050, 650});
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    //Define el evento al presionar el boton compilar
    if(evt.getSource() == compilar){
      String codigoStr = this.codigo.getText().replaceAll("[ \t" + System.lineSeparator() + "]+", " ");
      //Muestra una ventana antes de guardar
      if(this.archivoExterno == null) Mensaje.mostrarMensajeError("Guarda el archivo antes");
      else{
        //Crea el compiladora con el archivo externo y regresa un mensaje informando que se creo exitosamente
        Compilador compilador = new Compilador(this.archivoExterno + ".html", codigoStr);
        if(compilador.compilar()) Mensaje.mostrarMensajeOK("Compilacion Exitosa");
      }
    }
    //Define el evento al presionar el boton cargar
    else if(evt.getSource() == cargar){
      //Se elige el archivo para cargar
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setFileFilter(new FileNameExtensionFilter("Archivo de texto", "txt"));
      jFileChooser.showOpenDialog(this);
      File archivo = jFileChooser.getSelectedFile();

      //Si el archivo es vacío, se le manda un archivo vacío
      if(archivo != null) {
        this.archivoExterno = archivo.getAbsolutePath().replace(".txt", "");
        codigo.setText(Compilador.leerArchivo(this.archivoExterno + ".txt"));
      }
    }
    //Define el evento al presionar el boton guardar
    else if(evt.getSource() == guardar){
      //Se elige donde guardar el archivo
      if(this.archivoExterno == null){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.showOpenDialog(this);
        File archivo = jFileChooser.getSelectedFile();

        //Si el archivo es vacío se guardará en la ruta
        if(archivo != null) this.archivoExterno = archivo.getAbsolutePath();
      }
      //Crea el archivo con el archivoExterno
      Salida.crearArchivo(this.codigo.getText(), this.archivoExterno + ".txt");
    }
  }
}
