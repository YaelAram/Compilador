package model;

import ui.Mensaje;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
//Se generará el analizador léxico...
public class AnalizadorLexico {
  //Se declaran las variables involucradas, código, tokens
  private final String codigo;
  private String tokens;
  //Aquí se iran leyendo las propiedades previamente vistas en el HTML como type, id, etc.
  private final HashMap<String, String> propNombres = new HashMap<>();
  //Se hará una lista para los errores capturados
  private final LinkedList<String> errores = new LinkedList<>();
// Se leen los elementos HTML para la sección de código
  public AnalizadorLexico(String codigo){
    this.codigo = codigo;

    propNombres.put("ph", "placeholder");
    propNombres.put("tp", "type");
    propNombres.put("id", "id");
    propNombres.put("cl", "class");
  }
//Se genera un get para los tokens usados en siguientes etapas para regresar un string de tokens
  public String getTokens() {
    return tokens;
  }


  //Genera las option (elementos) que se usa en la lista desplegable
  private String crearOpcion(String prop){
    String[] opcionValores = prop.split("=")[1].split(",");

    return "<OPCION>/" + "<VALOR," + opcionValores[0] + ">/" + "<ESTADO," + opcionValores[1] +
            ">/" + "<CLAVE," + opcionValores[2].replaceAll("_", " ") + ">";
  }

  /*
  Genera el formato de tokens (<token, valor>) para los elementos HTML lables y txt (de ahí auxiliares)
   */
  private String crearPropAuxiliar(String prop){
    return "<TEXTO," + prop.split("=")[1].replaceAll("_", " ") + ">";
  }

  // Lee las etiquetas restantes
  private String crearProp(String prop){
    String[] propValores = prop.split("=");
    String propNombre = this.propNombres.get(propValores[0]);
    //Da una respuesta con el formato deseado
    return "<" + propNombre.toUpperCase() + "," + propNombre + "=\"" + propValores[1].replaceAll("[_,]+", " ") + "\"" + ">";
  }

  //Verifica un String vacío, y el blank verifica si hay espacios o tabulaciones y agrega un error de ser vacío
  public String[] analizar(){
    if(codigo.isEmpty() || codigo.isBlank()){
      this.errores.add("Error Lexico, archivo vacío");
      return new String[]{};
    }

    // Divide los tokens encontrados y regresa una respuesta dependiendo el contenido
    // Regresa un error en caso de no ser reconocido en el lenguaje
    this.tokens = Arrays.stream(codigo.split(" ")).map((palabra) -> {
      if (palabra.matches("(?i)(input|titulo|fin|btn|select)")) return "<" + palabra.toUpperCase() + ">";
      else if (palabra.matches("(txt|lbl)=[a-zA-Z0-_]+")) return crearPropAuxiliar(palabra);
      else if (palabra.matches("opt=[a-zA-Z-_,]+")) return crearOpcion(palabra);
      else if (palabra.matches("[a-z]+=[a-zA-Z0-9-_,]+")) return crearProp(palabra);
      else{
        //Se añade el error encontrado a la lista de errores
        this.errores.add("Error lexico, cadena no reconocida por el lenguaje: " + palabra);
        return "";
      }
      //Convierte un arreglo de strings a un solo string
    }).reduce((token1, token2) -> token1 + "/" + token2).orElse("");

    // Busca un < o un > o incluso los dos, se reemplaza con un vacío
    return this.tokens.replaceAll("[<>]+", "").split("/");
  }

  public boolean error(){
    return !this.errores.isEmpty();
  }


  // Se imprimen los errores encontrados
  public boolean imprimirErrores(){
    StringBuilder erroresStr = new StringBuilder("Se encontraron los siguientes errores: \n\n");
    this.errores.forEach((error) -> erroresStr.append(error).append("\n"));
    Mensaje.mostrarMensajeError(erroresStr.toString());
    return this.errores.isEmpty();
  }
}
