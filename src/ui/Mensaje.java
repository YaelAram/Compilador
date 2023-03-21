package ui;

import javax.swing.*;

public class Mensaje {
  public static void mostrarMensajeError(String mensaje){
    JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
  }

  public static void mostrarMensajeOK(String mensaje){
    JOptionPane.showMessageDialog(null, mensaje, "Exito", JOptionPane.INFORMATION_MESSAGE);
  }
}
