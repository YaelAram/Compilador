package model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class AnalizadorLexico {
  private final String archivoEntrada;
  private final String archivoSalida;
  private String tokensString;
  private final HashMap<String, String> propNombres = new HashMap<>();

  public AnalizadorLexico(String archivoEntrada, String archivoSalida){
    this.archivoEntrada = archivoEntrada;
    this.archivoSalida = archivoSalida;

    propNombres.put("ph", "placeholder=");
    propNombres.put("tp", "type=");
    propNombres.put("id", "id=");
    propNombres.put("cl", "class=");
  }

  private String leerArchivo(){
    String data = "";

    try{
      data = new String(Files.readAllBytes(Paths.get(this.archivoEntrada))).replaceAll("[ \t" + System.lineSeparator() + "]+", " ");
    } catch (IOException error) {
      System.out.println(error.getMessage());
      error.printStackTrace();
    }

    return data;
  }

  public void crearArchivo(){
    try{
      FileOutputStream resultado = new FileOutputStream(this.archivoSalida);
      resultado.write(this.tokensString.replaceAll("/", "").getBytes());
      resultado.close();
    } catch (IOException error){
      System.out.println(error.getMessage());
      error.printStackTrace();
    }
  }

  private String crearOpcion(String prop){
    String[] opcionValores = prop.split("=")[1].split(",");

    return "<OPCION>/" + "<CLAVE," + opcionValores[0].replaceAll("_", " ") + ">/" + "<VALOR," + opcionValores[1] +
            ">/" + "<ESTADO," + opcionValores[2] + ">";
  }

  private String crearPropAuxiliar(String prop){
    return "<TEXTO," + prop.split("=")[1].replaceAll("_", " ") + ">";
  }

  private String crearProp(String prop){
    String[] propValores = prop.split("=");

    return this.propNombres.get(propValores[0]) + "\"" + propValores[1].replaceAll("[_,]+", " ") + "\"";
  }

  public String[] analizar(){
    String codigo = leerArchivo();

    if(codigo.isEmpty() || codigo.isBlank()) return new String[]{};

    this.tokensString = Arrays.stream(codigo.split(" ")).map((palabra) -> {
      if (palabra.matches("(?i)(crear|input|titulo|con|fin|btn|radio|select)")) return "<" + palabra.toUpperCase() + ">";
      else if (palabra.matches("(txt|lbl)=[a-zA-Z0-_]+")) return crearPropAuxiliar(palabra);
      else if (palabra.matches("opt=[a-zA-Z-_,]+")) return crearOpcion(palabra);
      else if (palabra.matches("[a-z]+=[a-zA-Z0-9-_,]+")) return "<PROP," + crearProp(palabra) + ">";
      return "";
    }).reduce((token1, token2) -> token1 + "/" + token2).orElse("");

    return this.tokensString.split("/");
  }
}
