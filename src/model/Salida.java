package model;

import java.io.FileOutputStream;
import java.io.IOException;

public class Salida {
  public static void crearArchivo(String salida, String archivoSalida){
    try{
      FileOutputStream resultado = new FileOutputStream(archivoSalida);
      resultado.write(salida.getBytes());
      resultado.close();
    } catch (IOException error){
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }
}
