package ui;

import model.Compilador;
import model.Salida;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Ventana extends JFrame implements ActionListener {
  private final Estilo estilo = new Estilo(this);
  private JTextArea codigo;
  private JButton compilar, cargar, guardar;
  private String archivoExterno = null;

  public Ventana(){
    estilo.frameEstilo("Compilador", WindowConstants.EXIT_ON_CLOSE, new int[]{0, 0, 1100, 800});
    construirUI();
    this.setVisible(true);
  }

  private void construirUI(){
    compilar = new JButton();
    compilar.addActionListener(this);
    estilo.buttonEstilo(compilar, new int[]{20, 20, 150, 30}, "Compilar");

    cargar = new JButton();
    cargar.addActionListener(this);
    estilo.buttonEstilo(cargar, new int[]{250, 20, 150, 30}, "Cargar");

    guardar = new JButton();
    guardar.addActionListener(this);
    estilo.buttonEstilo(guardar, new int[]{480, 20, 150, 30}, "Guardar");

    codigo = new JTextArea(50, 200);
    estilo.textComponentEstilo(codigo, new int[] {20, 70, 1050, 650});
  }

  @Override
  public void actionPerformed(ActionEvent evt) {
    if(evt.getSource() == compilar){
      String codigoStr = this.codigo.getText().replaceAll("[ \t" + System.lineSeparator() + "]+", " ");

      if(this.archivoExterno == null) Mensaje.mostrarMensajeError("Guarda el archivo antes");
      else{
        Compilador compilador = new Compilador(this.archivoExterno + ".html", codigoStr);
        if(compilador.compilar()) Mensaje.mostrarMensajeOK("Compilacion Exitosa");
      }
    }
    else if(evt.getSource() == cargar){
      JFileChooser jFileChooser = new JFileChooser();
      jFileChooser.setFileFilter(new FileNameExtensionFilter("Archivo de texto", "txt"));
      jFileChooser.showOpenDialog(this);
      File archivo = jFileChooser.getSelectedFile();

      if(archivo != null) {
        this.archivoExterno = archivo.getAbsolutePath().replace(".txt", "");
        codigo.setText(Compilador.leerArchivo(this.archivoExterno + ".txt"));
      }
    }
    else if(evt.getSource() == guardar){
      if(this.archivoExterno == null){
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.showOpenDialog(this);
        File archivo = jFileChooser.getSelectedFile();

        if(archivo != null) this.archivoExterno = archivo.getAbsolutePath();
      }
      Salida.crearArchivo(this.codigo.getText(), this.archivoExterno + ".txt");
    }
  }
}
