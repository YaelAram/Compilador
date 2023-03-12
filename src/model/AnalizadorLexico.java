package model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;

public class AnalizadorLexico {
  private final String archivoEntrada;
  private String tokens;
  private final HashMap<String, String> propNombres = new HashMap<>();

  public AnalizadorLexico(String archivoEntrada){
    this.archivoEntrada = archivoEntrada;

    propNombres.put("ph", "placeholder");
    propNombres.put("tp", "type");
    propNombres.put("id", "id");
    propNombres.put("cl", "class");
  }

  public String getTokens() {
    return tokens;
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

  private String crearOpcion(String prop){
    String[] opcionValores = prop.split("=")[1].split(",");

    return "<OPCION>/" + "<VALOR," + opcionValores[0] + ">/" + "<ESTADO," + opcionValores[1] +
            ">/" + "<CLAVE," + opcionValores[2].replaceAll("_", " ") + ">";
  }

  private String crearPropAuxiliar(String prop){
    return "<TEXTO," + prop.split("=")[1].replaceAll("_", " ") + ">";
  }

  private String crearProp(String prop){
    String[] propValores = prop.split("=");
    String propNombre = this.propNombres.get(propValores[0]);

    return "<" + propNombre.toUpperCase() + "," + propNombre + "=\"" + propValores[1].replaceAll("[_,]+", " ") + "\"" + ">";
  }

  public String[] analizar(){
    String codigo = leerArchivo();

    if(codigo.isEmpty() || codigo.isBlank()) return new String[]{};

    this.tokens = Arrays.stream(codigo.split(" ")).map((palabra) -> {
      if (palabra.matches("(?i)(input|titulo|fin|btn|radio|select)")) return "<" + palabra.toUpperCase() + ">";
      else if (palabra.matches("(txt|lbl)=[a-zA-Z0-_]+")) return crearPropAuxiliar(palabra);
      else if (palabra.matches("opt=[a-zA-Z-_,]+")) return crearOpcion(palabra);
      else if (palabra.matches("[a-z]+=[a-zA-Z0-9-_,]+")) return crearProp(palabra);
      return "";
    }).reduce((token1, token2) -> token1 + "/" + token2).orElse("");

    return this.tokens.replaceAll("[<>]+", "").split("/");
  }
}
