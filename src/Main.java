import model.AnalizadorLexico;
import model.AnalizadorSintactico;
import model.Salida;

import java.util.HashMap;
import java.util.LinkedList;

public class Main {
  public static void main(String[] args) {
    String archivoEntrada = "test.txt";
    String archivoSalidaLexico = "analizadorLexico.out.txt";
    String archivoSalidaSintactico = "analizadorSintactico.out.txt";
    AnalizadorLexico analizadorLexico = new AnalizadorLexico(archivoEntrada);

    String[] tokens = analizadorLexico.analizar();
    Salida.crearArchivo(analizadorLexico.getTokens().replaceAll("/", ""), archivoSalidaLexico);

    AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico(tokens);
    LinkedList<HashMap<String, String>> dom = analizadorSintactico.analizar().getDom();
    Salida.crearArchivo(dom.toString(), archivoSalidaSintactico);

    System.out.println("\n\nAnalizador sintactico....\n");
    System.out.println(dom);
  }
}