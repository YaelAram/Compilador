package html;

import java.util.HashMap;
/*
Se genera los elementos para construir la funcionalidad de un botón de subida de datos
 */
public class SubmitBtn extends ElementoHTML {
  private final String texto;
  private final String estilo;
//Se heredan elementos de nuestra clase ElementoHTML
  public SubmitBtn(HashMap<String, String> elemento){
    super(elemento.get("etiqueta"), false);
    this.texto = elemento.get("texto");
    this.estilo = elemento.get("class");
  }
//Método para generar un HTML
  @Override
  public String toString() {
    String[] etiqueta = this.crearEtiqueta();
    return etiqueta[0] + " type=\"submit\" " + this.estilo + ">" + this.texto + etiqueta[1];
  }
}
