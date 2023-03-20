package model;

import java.util.HashMap;
import java.util.LinkedList;

public class AnalizadorSemantico {
  private final LinkedList<HashMap<String, String>> dom;
  private final LinkedList<String> errores  = new LinkedList<>();

  public AnalizadorSemantico(LinkedList<HashMap<String, String>> dom) {
    this.dom = dom;
  }

  public void analizar(){
    this.dom.forEach((elementoHTML) -> {
      if(!elementoHTML.get("etiqueta").equalsIgnoreCase("select")) return;

      String opciones = elementoHTML.get("opciones");
      String id = elementoHTML.get("id");
      if(!opciones.matches("(<option [a-zA-Z0-9=\" ]+>[a-zA-Z0-9 ]+</option>){2,}"))
        this.errores.add("Error Semantico, el select con ID: " + id + " solo tiene una opcion");
      if(opciones.split("selected").length > 2)
        this.errores.add("Error Semantico, el select con ID: " + id + " tiene 2 o mas opciones con el atributo selected");
    });
  }

  public boolean error(){
    return !this.errores.isEmpty();
  }

  public boolean imprimirErrores(){
    this.errores.forEach(System.out::println);
    return this.errores.isEmpty();
  }
}
