# Compilador de txt a html

Como parte del proyecto de la materia de Compiladoresse construyo el
siguiente compilador desde cero, el cual convierte un archivo de
texto (.txt) en un archivo HTML (.html).

## Tecnologias utilizadas

-   Java SE 16
-   Intellij Idea Community Edition 2022.3.3

## Notas sobre el compilador

-   El guion bajo (\_) es interpretado como un espacio
-   El guion intermedio (-) no recibe modificaciones
-   Las clases CSS especificadas en el codigo fuente (archivo de texto) son
    tomadas del archivo CSS con el nombre "estilo.css" ubicado en el mismo
    directorio del archivo HTML resultante
-   Un script puede ser cargado situando un archivo JavaScript llamado "app.js"
    en el mismo directorio del archivo HTML resultante
-   El archivo HTML resultante sera creado en el mismo directorio donde se
    encuentre el archivo de texto fuente y recibira el mismo nombre

## Gramatica

### Crear un H1

Sigue la siguiente estructura:

```
titulo txt=INSERTA_TITULO fin
```

Donde:

-   titulo y fin son palabras clave del lenguaje
-   La propiedad _txt=_ especifica el contenido del titulo, cada
    palabra se debe separar utilizando un gion bajo (\_)

Ejemplo:

```
titulo txt=Inicio_de_Sesion fin //Titulo con mas de dos palabras
titulo txt=Registro fin //Titulo con una sola palabra
```

## Crear un INPUT

Sigue la siguiente estructura:

```
input lbl=TITULO tp=TIPO id=ID ph=PISTA cl=CLASES fin
```

Donde:

-   input y fin son palabras clave del lenguaje
-   La propiedad _label=_ especifica el contenido del elemento LABEL que se
    usara como titulo para el campo
-   La propiedad _tp=_ indica el tipo del input, los valores aceptados son:
    text, email y password
-   La propiedad _id=_ especifica el ID que sera utilizado para enlaza el
    elemento LABEL con el INPUT
-   La propiedad _ph=_ indica el contenido del placeholder, el cual da una pista
    al usuario del contenido que espera el campo
-   La propiedad _cl=_ especifica las clases CSS que seran aplicadas al elemento
    input, el elemento LABEL no recibe ninguna de estas, en caso de poner mas de
    una clase separar cada una con una coma (,)

Ejemplo:

```
input lbl=Password_de_Usuario tp=password id=pass-usu ph=Password_de_Usuario cl=borde,redondeado fin
input lbl=Usuario  tp=text id=nom-usu ph=Nombre_de_Usuario cl=redondeado,borde fin
```

## Crear un SELECT

Sigue la siguiente estructura:

```
select lbl=TITULO id=ID cl=CLASES
	opt=VALOR,ESTADO,CLAVE
	opt=VALOR,ESTADO,CLAVE FIN
```

Donde:

-   select y fin son palabras clave del lenguaje
-   La propiedad _label=_ especifica el contenido del elemento LABEL que se
    usara como titulo para el campo
-   La propiedad _id=_ especifica el ID que sera utilizado para enlaza el
    elemento LABEL con el INPUT
-   La propiedad _cl=_ especifica las clases CSS que seran aplicadas al elemento
    input, el elemento LABEL no recibe ninguna de estas, en caso de poner mas de
    una clase separar cada una con una coma (,)
-   La propiedad _opt=_ especifica cada opcion del select, donde:
    -   VALOR: indica el valor que recibira el codigo JavaScript
    -   Estado: es un boolean que dice si la opcion esta seleccionada (solo se
        permite una opcion en true)
    -   Clave: indica el texto que vera el usuario

Ejemplo:

```
select lbl=Estado id=estado cl=estilo
	opt=edomex,false,Estado_de_Mexico
	opt=cdmx,true,Ciudad_de_Mexico
	opt=puebla,false,Puebla fin
```

### Crear un BUTTON

Sigue la siguiente estructura:

```
btn txt=TEXTO cl=CLASES fin
```

Donde:

-   titulo y fin son palabras clave del lenguaje
-   La propiedad _txt=_ especifica el titulo del boton, cada
    palabra se debe separar utilizando un gion bajo (\_)

Ejemplo:

```
btn txt=Iniciar_Sesion cl=vacio,redondea fin
```
