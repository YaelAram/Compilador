package html;

public abstract class ElementoHTML {
  protected final String etiqueta;
  protected final boolean conclusiva;

  public ElementoHTML(String etiqueta, boolean conclusiva) {
    this.etiqueta = etiqueta.toLowerCase();
    this.conclusiva = conclusiva;
  }

  public String[] crearEtiqueta(){
    if(this.conclusiva) return new String[]{"<" + this.etiqueta, "/>"};
    else return new String[]{"<" + this.etiqueta, "</" + this.etiqueta + ">"};
  }
}
