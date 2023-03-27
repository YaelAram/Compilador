package ui;

import javax.swing.*;

//Se define la clase para mensajes emergentes
public class Mensaje {
  // Se muestra un error
  public static void mostrarMensajeError(String mensaje){
    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
  }

  // Se muestra un mensaje de una acción realizada exitosamente
  public static void mostrarMensajeOK(String mensaje){
    JOptionPane.showMessageDialog(null, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
  }
}
