import model.Compilador;

public class Main {
  public static void main(String[] args) {
    String archivoEntrada = "test.txt";
    String archivoSalidaLexico = "analizadorLexico.out.txt";
    String archivoSalidaSintactico = "analizadorSintactico.out.txt";
    String archivoSalidaHTML = "index.html";

    Compilador compilador = new Compilador(archivoEntrada, archivoSalidaLexico, archivoSalidaSintactico, archivoSalidaHTML);
    System.out.println((compilador.compilar()) ? "Compilacion exitosa" : "Compilacion Fallida");
  }
}