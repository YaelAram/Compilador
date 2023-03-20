package html;

import java.util.HashMap;

public class Texto extends ElementoHTML {
  private final String texto;
  private final String id;

  public Texto(HashMap<String, String> elemento){
    super(elemento.get("etiqueta"), false);
    this.texto = elemento.get("texto");
    this.id = elemento.get("for");
  }

  @Override
  public String toString() {
    String[] etiqueta = this.crearEtiqueta();
    String atributo = (this.id == null) ? ">" : " " + this.id + ">";
    return etiqueta[0] + atributo + this.texto + etiqueta[1];
  }
}
