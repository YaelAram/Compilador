import model.AnalizadorLexico;

import java.util.Arrays;

public class Main {
  public static void main(String[] args) {
    String archivoEntrada = "test.txt";
    String archivoSalidaLexico = "analizadorLexico.out.txt";
    AnalizadorLexico analizadorLexico = new AnalizadorLexico(archivoEntrada, archivoSalidaLexico);

    String[] tokens = analizadorLexico.analizar();
    analizadorLexico.crearArchivo();

    System.out.println(Arrays.toString(tokens));
  }
}