package model;

import html.HTML;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;

// Se genera la clase que combinará cada herramienta previa para el compilador
public class Compilador {
  private final String codigo;
  private final String archivoSalidaHTML;

//Se genera el constructor del compilador con sus archivos de salida y su código
  public Compilador(String archivoSalidaHTML, String codigo) {
    this.archivoSalidaHTML = archivoSalidaHTML;
    this.codigo = codigo;
  }

  //Lee el archivo
  public static String leerArchivo(String archivoEntrada){
    String codigoStr = "";

    try{
      codigoStr = new String(Files.readAllBytes(Paths.get(archivoEntrada)));
    } catch (IOException error) {
      //Imprime errores encontrados
      System.out.println(error.getMessage());
      error.printStackTrace();
    }

    return codigoStr;
  }



  public boolean compilar(){
    // nombra los archivos de salida respectivamente
    String archivoSalidaLexico = "analizadorLexico.out.txt";
    String archivoSalidaSintactico = "analizadorSintactico.out.txt";

    //Se llama al analizador léxico
    AnalizadorLexico analizadorLexico = new AnalizadorLexico(this.codigo);
    String[] tokens = analizadorLexico.analizar();

    //Se imprimen los errores capturados por el analizador léxico
    if(analizadorLexico.error()) return analizadorLexico.imprimirErrores();
    Salida.crearArchivo(analizadorLexico.getTokens().replaceAll("/", ""), archivoSalidaLexico);

    //Se llama al analizador sintáctico
    AnalizadorSintactico analizadorSintactico = new AnalizadorSintactico(tokens);
    LinkedList<HashMap<String, String>> arbolSintactico = analizadorSintactico.analizar().getArbolSintactico();

    //Se imprimen los errores capturados por el analizador sintáctico
    if(analizadorSintactico.error()) return analizadorSintactico.imprimirErrores();
    Salida.crearArchivo(arbolSintactico.toString(), archivoSalidaSintactico);

    //Se llama al analizador semántico
    AnalizadorSemantico analizadorSemantico = new AnalizadorSemantico(arbolSintactico);
    analizadorSemantico.analizar();

    //Se imprimen los errores capturados por el analizador semántico
    if(analizadorSemantico.error()) return analizadorSemantico.imprimirErrores();

    //Genera el código intermedio
    CodigoIntermedio codigoIntermedio = new CodigoIntermedio(arbolSintactico);
    codigoIntermedio.analizar();

    //Crea un formulario HTML usando el código intermedio
    HTML html = codigoIntermedio.crearFormulario();
    Salida.crearArchivo(html.crearHTML(), this.archivoSalidaHTML);

    return true;
  }
}
