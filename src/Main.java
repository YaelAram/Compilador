import model.AnalizadorLexico;

public class Main {
  public static void main(String[] args) {
    String archivoEntrada = "test.txt";
    String archivoSalidaLexico = "analizadorLexico.out.txt";
    AnalizadorLexico analizadorLexico = new AnalizadorLexico(archivoEntrada, archivoSalidaLexico);

    String[] tokens = analizadorLexico.analizar();
    analizadorLexico.crearArchivo();

    for(String token : tokens){
      if (token.equals("<FIN>")) System.out.println(token);
      else if (token.equals("<OPCION>")) System.out.print(System.lineSeparator() + "\t" + token);
      else System.out.print(token);
    }
  }
}