package model;

import html.HTML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

public class Compilador {
  private final String codigo;
  private final String archivoSalidaHTML;

  public Compilador(String archivoSalidaHTML, String codigo) {
    this.archivoSalidaHTML = archivoSalidaHTML;
    this.codigo = codigo;
  }

  public static String leerArchivo(String archivoEntrada){
    String codigoStr = "";

    try{
      codigoStr = new String(Files.readAllBytes(Paths.get(archivoEntrada)));
    } catch (IOException error) {
      System.out.println(error.getMessage());
      error.printStackTrace();
    }

    return codigoStr;
  }

  public boolean compilar(){
    String archivoSalidaLexico = "analizadorLexico.out.txt";
    String archivoSalidaSintactico = "analizadorSintactico.out.txt";

    AnalizadorLexico analizadorLexico = new AnalizadorLexico(this.codigo);
    String[] tokens = analizadorLexico.analizar();

    if(analizadorLexico.error()) return analizadorLexico.imprimirErrores();
    Salida.crearArchivo(analizadorLexico.getTokens().replaceAll("/", ""), archivoSalidaLexico);

    AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico(tokens);
    LinkedList<HashMap<String, String>> arbolSintactico = analizadorSintactico.analizar().getArbolSintactico();

    if(analizadorSintactico.error()) return analizadorSintactico.imprimirErrores();
    Salida.crearArchivo(arbolSintactico.toString(), archivoSalidaSintactico);

    AnalizadorSemantico analizadorSemantico = new AnalizadorSemantico(arbolSintactico);
    analizadorSemantico.analizar();

    if(analizadorSemantico.error()) return analizadorSemantico.imprimirErrores();

    CodigoIntermedio codigoIntermedio = new CodigoIntermedio(arbolSintactico);
    codigoIntermedio.analizar();

    HTML html = codigoIntermedio.crearFormulario();
    Salida.crearArchivo(html.crearHTML(), this.archivoSalidaHTML);

    return true;
  }
}
