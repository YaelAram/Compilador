package model;

import java.util.HashMap;
import java.util.LinkedList;

public class AnalizadorSintactico {
  private final String[] tokens;
  private MaquinaEstados maquinaEstados;
  private final LinkedList<HashMap<String, String>> arbolSintactico = new LinkedList<>();
  private final LinkedList<String> errores = new LinkedList<>();

  public AnalizadorSintactico(String[] tokens){
    this.tokens = tokens;
  }

  public LinkedList<HashMap<String, String>> getArbolSintactico() {
    return arbolSintactico;
  }

  public AnalizadorSintactico analizar(){
    for(String token : this.tokens){
      if(this.maquinaEstados == null) this.maquinaEstados = new MaquinaEstados(token);
      else if(!this.maquinaEstados.verificar(token)) errores.add("Error Sintactico, token incorrecto: " + token);

      if(token.equals("FIN")) {
        if(!maquinaEstados.getLabel().isEmpty()) this.arbolSintactico.add(maquinaEstados.getLabel());
        if(!maquinaEstados.getEtiqueta().isEmpty()) this.arbolSintactico.add(maquinaEstados.getEtiqueta());
        this.maquinaEstados = null;
      }
    }

    return this;
  }

  public boolean error(){
    return !this.errores.isEmpty();
  }

  public boolean imprimirErrores(){
    this.errores.forEach(System.out::println);
    return this.errores.isEmpty();
  }
}
