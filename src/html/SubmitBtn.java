package html;

import java.util.HashMap;

public class SubmitBtn extends ElementoHTML {
  private final String texto;
  private final String estilo;

  public SubmitBtn(HashMap<String, String> elemento){
    super(elemento.get("etiqueta"), false);
    this.texto = elemento.get("texto");
    this.estilo = elemento.get("class");
  }

  @Override
  public String toString() {
    String[] etiqueta = this.crearEtiqueta();
    return etiqueta[0] + " type=\"submit\" " + this.estilo + ">" + this.texto + etiqueta[1];
  }
}
