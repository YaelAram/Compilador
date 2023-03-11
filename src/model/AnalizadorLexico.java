package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class AnalizadorLexico {
  private final String inputFile;
  private final String outputFile;
  private String tokens = "";

  public AnalizadorLexico(String inputFile, String outputFile){
    this.inputFile = inputFile;
    this.outputFile = outputFile;
  }

  private String leerArchivo(){
    String data = "";

    try{
      data = new String(Files.readAllBytes(Paths.get(this.inputFile))).replaceAll("[ \t" + System.lineSeparator() + "]+", " ");
    } catch (IOException error) {
      System.out.println(error.getMessage());
      error.printStackTrace();
    }

    return data;
  }

  private void crearArchivo(){
    try{
      FileOutputStream resultado = new FileOutputStream(this.outputFile);
      resultado.write(this.tokens.getBytes());
      resultado.close();
    } catch (IOException error){
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }

  public String analizar(){
    String codigo = leerArchivo();

    if(codigo.isEmpty() || codigo.isBlank()) return "";

    Arrays.stream(codigo.split(" ")).map((palabra) -> {
      if (palabra.matches("(?i)(crear|input|titulo|con|fin|btn)")) return "<" + palabra.toUpperCase() + ">";
      else if (palabra.matches("[a-z]{2,}=[a-z-]+")) return "<PROP," + palabra + ">";
      return "";
    })
            .reduce((palabra1, palabra2) -> palabra1 + palabra2)
            .ifPresent((tokens) -> this.tokens = tokens);

    this.crearArchivo();

    return this.tokens;
  }
}
