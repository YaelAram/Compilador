package model;

import java.io.FileOutputStream;
import java.io.IOException;
// Genera archivos de salida
public class Salida {
  //Función para la creación del archivo
  public static void crearArchivo(String salida, String archivoSalida){
    try{
      //Se declara la avariable de resultado y su escritura
      FileOutputStream resultado = new FileOutputStream(archivoSalida);
      resultado.write(salida.getBytes());
      resultado.close();
    } catch (IOException error){
      //Se imprime de ser el caso, un error encontrado
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }
}
