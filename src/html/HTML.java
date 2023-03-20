package html;

import java.util.LinkedList;

public class HTML {
  private final LinkedList<ElementoHTML> dom;

  public HTML(LinkedList<ElementoHTML> dom) {
    this.dom = dom;
  }

  public String crearHTML(){
    StringBuilder html = new StringBuilder("<!DOCTYPE html><html lang=\"es\"><head><meta charset=\"UTF-8\"/>" +
            "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" />" +
            "<title>Formulario</title>" +
            "<link rel=\"preconnect\" href=\"https://fonts.googleapis.com\" />" +
            "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin />" +
            "<link href=\"https://fonts.googleapis.com/css2?family=Poppins&display=swap\" rel=\"stylesheet\"/>" +
            "<link rel=\"stylesheet\" href=\"./estilo.css\" />" +
            "<script src=\"./app.js\" defer></script>" +
            "</head><body><main class=\"redondeado\">");
    html.append(this.dom.removeFirst());
    html.append("<form>");
    this.dom.forEach(html::append);
    html.append("</form></main></body></html>");
    return html.toString();
  }
}
