package html;

import java.util.HashMap;
/*
Se generan los elementos necesarios para una lista desplegable a usar posteriormente
En el UI para selección de opciones
 */
public class ListaDesplegable extends ElementoHTML {
  private final String id;
  private final String opciones;
  private final String estilo;

  public ListaDesplegable(HashMap<String, String> elemento){
    super(elemento.get("etiqueta"), false);
    this.id = elemento.get("id");
    this.opciones = elemento.get("opciones");
    this.estilo = elemento.get("class");
  }

  @Override
  public String toString() {
    String[] etiqueta = this.crearEtiqueta();
    return etiqueta[0] + " " + this.id + " " + this.estilo + ">" + this.opciones + etiqueta[1];
  }
}
