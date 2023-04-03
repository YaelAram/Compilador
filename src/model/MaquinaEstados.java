package model;

import java.util.HashMap;

//Clase para la máquina de estados
public class MaquinaEstados {
  //Genera los elementos a usar para la máquina de estados
  private final String elemento;
  private final HashMap<String, String> producciones = new HashMap<>();
  private final HashMap<String, String> label = new HashMap<>();
  private final HashMap<String, String> etiqueta = new HashMap<>();
  private String estadoActual;
  private String opcion = "<option value=\"";


  public MaquinaEstados(String elemento){
    this.elemento = elemento;
    this.estadoActual = elemento;
    crearProducciones();
  }

  public HashMap<String, String> getLabel() {
    return label;
  }

  public HashMap<String, String> getEtiqueta() {
    return etiqueta;
  }

  /*
  Verifica los tokens para posteriormente utilizarlos en la producción
   */
  public boolean verificar(String token){
    //Divide con clave valor los elementos en los tokens
    String[] claveValor = token.split(",");
    //Genera la producción con el estado actual más su clave valor
    String produccion = this.estadoActual + "-" + claveValor[0];
    //Actualiza el estado actual
    this.estadoActual = this.producciones.get(produccion);
    //Interpreta la opción adecuada para añadirle una etiqueta
    if(!token.matches("(FIN|OPCION|INPUT|BTN|TITULO|SELECT)")) interpretarToken(claveValor[0], claveValor[1]);

    return this.producciones.containsKey(produccion);
  }

  // Convierte los tokens en sus equivalentes HTML
  private void interpretarToken(String clave, String valor){
    String produccion = this.elemento + "-" + clave;
    if(produccion.equals("TITULO-TEXTO")){
      this.etiqueta.put("etiqueta", "H1");
      this.etiqueta.put("texto", valor);
    }
    else if(produccion.equals("BTN-TEXTO")){
      this.etiqueta.put("etiqueta", "BUTTON");
      this.etiqueta.put("texto", valor);
    }
    else if(produccion.matches("(INPUT|SELECT)-TEXTO")){
      this.label.put("etiqueta", "LABEL");
      this.label.put("texto", valor);
      this.etiqueta.put("etiqueta", this.elemento);
    }
    else if(produccion.matches("(TITULO|INPUT|SELECT|BTN)-CLASS")) this.etiqueta.put(clave.toLowerCase(), valor);
    else if(produccion.matches("INPUT-(PLACEHOLDER|TYPE)")) this.etiqueta.put(clave.toLowerCase(), valor);
    else if(produccion.matches("(INPUT|SELECT)-ID")){
      this.label.put("for", valor.replaceAll("id", "for"));
      this.etiqueta.put(clave.toLowerCase(), valor);
    }
    else if(produccion.equals("SELECT-VALOR")) this.opcion += valor + "\"";
    else if(produccion.equals("SELECT-ESTADO")) this.opcion += (valor.equalsIgnoreCase("true")) ? " selected>" : ">";
    else if(produccion.equals("SELECT-CLAVE")){
      this.opcion += valor + "</option>";
      this.etiqueta.put("opciones", this.etiqueta.getOrDefault("opciones", "") + this.opcion);
      this.opcion = "<option value=\"";
    }
  }

  //Crea las producciones pertinentes en cada caso
  private void crearProducciones(){
    switch (this.elemento) {
      case "TITULO" -> crearProduccionesTitulo();
      case "INPUT" -> crearProduccionesInput();
      case "BTN" -> crearProduccionesBTN();
      default -> crearProduccionesSelect();
    }
  }

  private void crearProduccionesTitulo(){
    this.producciones.put("TITULO-TEXTO", "TEXTO");
    this.producciones.put("TEXTO-FIN", "FIN");
  }

  private void crearProduccionesBTN(){
    this.producciones.put("BTN-TEXTO", "TEXTO");
    this.producciones.put("TEXTO-CLASS", "CLASS");
    this.producciones.put("CLASS-FIN", "FIN");
  }

  private void crearProduccionesInput(){
    this.producciones.put("INPUT-TEXTO", "TEXTO");
    this.producciones.put("TEXTO-TYPE", "TYPE");
    this.producciones.put("TYPE-ID", "ID");
    this.producciones.put("ID-PLACEHOLDER", "PLACEHOLDER");
    this.producciones.put("PLACEHOLDER-CLASS", "CLASS");
    this.producciones.put("CLASS-FIN", "FIN");
  }

  private void crearProduccionesSelect(){
    this.producciones.put("SELECT-TEXTO", "TEXTO");
    this.producciones.put("TEXTO-ID", "ID");
    this.producciones.put("ID-CLASS", "CLASS");
    this.producciones.put("CLASS-OPCION", "OPCION");
    this.producciones.put("OPCION-VALOR", "VALOR");
    this.producciones.put("VALOR-ESTADO", "ESTADO");
    this.producciones.put("ESTADO-CLAVE", "CLAVE");
    this.producciones.put("CLAVE-OPCION", "OPCION");
    this.producciones.put("CLAVE-FIN", "FIN");
  }
}
