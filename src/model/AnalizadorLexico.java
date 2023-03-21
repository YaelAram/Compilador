package model;

import ui.Mensaje;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class AnalizadorLexico {
  private final String codigo;
  private String tokens;
  private final HashMap<String, String> propNombres = new HashMap<>();
  private final LinkedList<String> errores = new LinkedList<>();

  public AnalizadorLexico(String codigo){
    this.codigo = codigo;

    propNombres.put("ph", "placeholder");
    propNombres.put("tp", "type");
    propNombres.put("id", "id");
    propNombres.put("cl", "class");
  }

  public String getTokens() {
    return tokens;
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
    if(codigo.isEmpty() || codigo.isBlank()){
      this.errores.add("Error Lexico, archivo vacio");
      return new String[]{};
    }

    this.tokens = Arrays.stream(codigo.split(" ")).map((palabra) -> {
      if (palabra.matches("(?i)(input|titulo|fin|btn|select)")) return "<" + palabra.toUpperCase() + ">";
      else if (palabra.matches("(txt|lbl)=[a-zA-Z0-_]+")) return crearPropAuxiliar(palabra);
      else if (palabra.matches("opt=[a-zA-Z-_,]+")) return crearOpcion(palabra);
      else if (palabra.matches("[a-z]+=[a-zA-Z0-9-_,]+")) return crearProp(palabra);
      else{
        this.errores.add("Error lexico, cadena no reconocida por el lenguaje: " + palabra);
        return "";
      }
    }).reduce((token1, token2) -> token1 + "/" + token2).orElse("");

    return this.tokens.replaceAll("[<>]+", "").split("/");
  }

  public boolean error(){
    return !this.errores.isEmpty();
  }

  public boolean imprimirErrores(){
    StringBuilder erroresStr = new StringBuilder("Se encontraron los siguientes errores: \n\n");
    this.errores.forEach((error) -> erroresStr.append(error).append("\n"));
    Mensaje.mostrarMensajeError(erroresStr.toString());
    return this.errores.isEmpty();
  }
}
