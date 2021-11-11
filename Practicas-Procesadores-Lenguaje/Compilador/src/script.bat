::java -cp ../jlex.jar JLex.Main AnalizadorLexicoTiny.l

java -cp ../cup.jar java_cup.Main -parser AnalizadorSintacticoTiny -symbols ClaseLexica -nopositions asint/Tiny.cup
copy AnalizadorSintacticoTiny.java asint/AnalizadorSintacticoTiny.java
del AnalizadorSintacticoTiny.java
copy ClaseLexica.java asint/ClaseLexica.java
del ClaseLexica.java

javac -cp "../cup.jar" alex/*.java asint/*.java ast/*.java errors/*.java
java -cp ".;../cup.jar" asint/Main inputCodigo.txt

wat2wasm codigo.wat
node codigo.js

pause