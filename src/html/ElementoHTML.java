package html;
/*
Esta clase es utilizada para derivar los elementos HTML que se usarán a lo largo del proyecto
 */
//Tenemos el constructor de la clase con sus respectivos parámetros
public abstract class ElementoHTML {
  protected final String etiqueta;
  protected final boolean conclusiva;
/*Se utiliza un elemento "conclusiva" para agilizar las constucciones de etiquetas
que no necesitan símbolos de cierre
 */
  public ElementoHTML(String etiqueta, boolean conclusiva) {
    this.etiqueta = etiqueta.toLowerCase();
    this.conclusiva = conclusiva;
  }

  public String[] crearEtiqueta(){
    if(this.conclusiva) return new String[]{"<" + this.etiqueta, "/>"};
    else return new String[]{"<" + this.etiqueta, "</" + this.etiqueta + ">"};
  }
}
