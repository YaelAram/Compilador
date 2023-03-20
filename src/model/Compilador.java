package model;

import html.HTML;

import java.util.HashMap;
import java.util.LinkedList;

public class Compilador {
  private final String archivoEntrada;
  private final String archivoSalidaLexico;
  private final String archivoSalidaSintactico;
  private final String archivoSalidaHTML;

  public Compilador(String archivoEntrada, String archivoSalidaLexico, String archivoSalidaSintactico, String archivoSalidaHTML) {
    this.archivoEntrada = archivoEntrada;
    this.archivoSalidaLexico = archivoSalidaLexico;
    this.archivoSalidaSintactico = archivoSalidaSintactico;
    this.archivoSalidaHTML = archivoSalidaHTML;
  }

  public boolean compilar(){
    AnalizadorLexico analizadorLexico = new AnalizadorLexico(archivoEntrada);
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

    if(codigoIntermedio.error()) return codigoIntermedio.imprimirErrores();
    HTML html = codigoIntermedio.crearFormulario();
    Salida.crearArchivo(html.crearHTML(), this.archivoSalidaHTML);

    return true;
  }
}
