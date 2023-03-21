package model;

import html.*;

import java.util.HashMap;
import java.util.LinkedList;

public class CodigoIntermedio {
  private final LinkedList<HashMap<String, String>> arbolSintactico;
  private final LinkedList<ElementoHTML> dom = new LinkedList<>();

  public CodigoIntermedio(LinkedList<HashMap<String, String>> arbolSintactico) {
    this.arbolSintactico = arbolSintactico;
  }

  public HTML crearFormulario(){
    return new HTML(this.dom);
  }

  public void analizar(){
    this.arbolSintactico.forEach((elemento) -> {
      if(elemento.get("etiqueta").matches("(H1|LABEL)")) this.dom.add(new Texto(elemento));
      else if(elemento.get("etiqueta").matches("INPUT")) this.dom.add(new Input(elemento));
      else if(elemento.get("etiqueta").matches("BUTTON")) this.dom.add(new SubmitBtn(elemento));
      else if(elemento.get("etiqueta").matches("SELECT")) this.dom.add(new ListaDesplegable(elemento));
    });
  }
}
