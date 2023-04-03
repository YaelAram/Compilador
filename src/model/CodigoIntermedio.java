package model;

import html.*;

import java.util.HashMap;
import java.util.LinkedList;

//Se genera la clase que funcine como código intermedio
public class CodigoIntermedio {
  //Se agregan los correspondientes elementos para su funcionamiento
  private final LinkedList<HashMap<String, String>> arbolSintactico;
  private final LinkedList<ElementoHTML> dom = new LinkedList<>();

  public CodigoIntermedio(LinkedList<HashMap<String, String>> arbolSintactico) {
    this.arbolSintactico = arbolSintactico;
  }

  //Crea un formulario HTML con las etiquetas de la lista ligada dom
  public HTML crearFormulario(){
    return new HTML(this.dom);
  }


  //Se generará la funcionalidad de la clase
  public void analizar(){
    this.arbolSintactico.forEach((elemento) -> {
      //Valida cada etiqueta y agrega elementos a la lista ligada dom con su correspondiente elemento HTML
      if(elemento.get("etiqueta").matches("(H1|LABEL)")) this.dom.add(new Texto(elemento));
      else if(elemento.get("etiqueta").matches("INPUT")) this.dom.add(new Input(elemento));
      else if(elemento.get("etiqueta").matches("BUTTON")) this.dom.add(new SubmitBtn(elemento));
      else if(elemento.get("etiqueta").matches("SELECT")) this.dom.add(new ListaDesplegable(elemento));
    });
  }
}
