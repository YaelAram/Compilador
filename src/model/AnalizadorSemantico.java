package model;

import ui.Mensaje;

import java.util.HashMap;
import java.util.LinkedList;
//Clase que fungirá como analizador semántico
public class AnalizadorSemantico {

  /*
  Dom será una lista ligada para ligar los maps y generar una estructura más
  legible y además que conserve el orden de los elementos
   */
  private final LinkedList<HashMap<String, String>> dom;
  //Strings de errores que irá guardando los errores
  private final LinkedList<String> errores  = new LinkedList<>();

  public AnalizadorSemantico(LinkedList<HashMap<String, String>> dom) {
    this.dom = dom;
  }
/*
método para analizar siguiendo el analizador semántico
 */
  public void analizar(){
    this.dom.forEach((elementoHTML) -> {
      if(!elementoHTML.get("etiqueta").equalsIgnoreCase("select")) return;

      /*
      valida las opciones posibles para cada elemento y almacena en la lista los errores encontrados
       */
      String opciones = elementoHTML.get("opciones");
      String id = elementoHTML.get("id");
      if(!opciones.matches("(<option [a-zA-Z0-9=\" ]+>[a-zA-Z0-9 ]+</option>){2,}"))
        this.errores.add("Error Semantico, el select con ID: " + id + " solo tiene una opcion");
      if(opciones.split("selected").length > 2)
        this.errores.add("Error Semantico, el select con ID: " + id + " tiene 2 o mas opciones con el atributo selected");
    });
  }
//valida la existencia de errores en lista
  public boolean error(){
    return !this.errores.isEmpty();
  }
//Imprime los errores en la anterior lista de errores
  public boolean imprimirErrores(){
    StringBuilder erroresStr = new StringBuilder("Se encontraron los siguientes errores: \n\n");
    this.errores.forEach((error) -> erroresStr.append(error).append("\n"));
    Mensaje.mostrarMensajeError(erroresStr.toString());
    return this.errores.isEmpty();
  }
}
