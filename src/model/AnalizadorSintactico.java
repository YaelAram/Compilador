package model;

import java.util.HashMap;
import java.util.LinkedList;

public class AnalizadorSintactico {
  private final String[] tokens;
  private MaquinaEstados maquinaEstados;
  private final LinkedList<HashMap<String, String>> dom = new LinkedList<>();

  public AnalizadorSintactico(String[] tokens){
    this.tokens = tokens;
  }

  public LinkedList<HashMap<String, String>> getDom() {
    return dom;
  }

  public AnalizadorSintactico analizar(){
    for(String token : this.tokens){
      if(this.maquinaEstados == null) this.maquinaEstados = new MaquinaEstados(token);
      else this.maquinaEstados.verificar(token);

      if(token.equals("FIN")) {
        if(!maquinaEstados.getLabel().isEmpty()) this.dom.add(maquinaEstados.getLabel());
        if(!maquinaEstados.getEtiqueta().isEmpty()) this.dom.add(maquinaEstados.getEtiqueta());
        this.maquinaEstados = null;
      }
    }

    return this;
  }
}
