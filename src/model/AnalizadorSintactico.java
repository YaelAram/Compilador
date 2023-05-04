package model;

import ui.Mensaje;

import java.util.HashMap;
import java.util.LinkedList;

//Generamos la clase analizador Sintáctico
public class AnalizadorSintactico {
/*Se generan todos los elementos necesarios como los tokens en un arreglo de strings,
nuestra máquina de estados, nuestro árbol sintáctico como un hash map con linked list
y una lista de errores; además de ciertos imports a clases externas a usar
 */
  private final String[] tokens;
  private MaquinaEstados maquinaEstados;
  private final LinkedList<HashMap<String, String>> arbolSintactico = new LinkedList<>();
  private final LinkedList<String> errores = new LinkedList<>();

  //Se inicia un constructor para los tokens
  public AnalizadorSintactico(String[] tokens){
    this.tokens = tokens;
  }
  //Método Get para el árbol sintáctico
  public LinkedList<HashMap<String, String>> getArbolSintactico() {
    return arbolSintactico;
  }

  //Se genera la funcionalidad del analizador
  public AnalizadorSintactico analizar(){
    for(String token : this.tokens){
      //Por cada token se verifica si está en una máquina de estados, en caso de error, este se enlista
      if(this.maquinaEstados == null) this.maquinaEstados = new MaquinaEstados(token);
      else if(!this.maquinaEstados.verificar(token)) errores.add("Error Sintactico, token incorrecto: " + token);

      //Se prepara para el reinicio la máquina de estados
      if(token.equals("FIN")) {
        if(!maquinaEstados.getLabel().isEmpty()) this.arbolSintactico.add(maquinaEstados.getLabel());
        if(!maquinaEstados.getEtiqueta().isEmpty()) this.arbolSintactico.add(maquinaEstados.getEtiqueta());
        this.maquinaEstados = null;
      }
    }

    return this;
  }


  //Verifica la existencia de errores
  public boolean error(){
    return !this.errores.isEmpty();
  }


  //Se imprimen los errores enlister
  public boolean imprimirErrores(){
    //Se genera un formato para regresar la respuesta de errores encontrados
    StringBuilder erroresStr = new StringBuilder("Se encontraron los siguientes errores: \n\n");
    //Se agregan los errores encontrados
    this.errores.forEach((error) -> erroresStr.append(error).append("\n"));
    //Se muestra los mensajes de error
    Mensaje.mostrarMensajeError(erroresStr.toString());
    return this.errores.isEmpty();
  }
}
