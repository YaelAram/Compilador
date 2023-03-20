package html;

import java.util.HashMap;

public class Input extends ElementoHTML {
  private final String id;
  private final String pista;
  private final String tipo;
  private final String estilo;

  public Input(HashMap<String, String> elemento){
    super(elemento.get("etiqueta"), true);
    this.id = elemento.get("id");
    this.pista = elemento.get("placeholder");
    this.tipo = elemento.get("type");
    this.estilo = elemento.get("class");
  }

  @Override
  public String toString() {
    String[] etiqueta = this.crearEtiqueta();
    return etiqueta[0] + " " + this.tipo + " " + this.pista + " " + this.id + " " + this.estilo + " autocomplete=\"off\"" + etiqueta[1];
  }
}
