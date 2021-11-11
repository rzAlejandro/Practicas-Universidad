package alex;

import errors.GestionErroresTiny;

%%
%cup 
%char
%line
%class AnalizadorLexicoTiny
%unicode
%public

%{
  private ALexOperations ops;
  private int charline = 0;
  private GestionErroresTiny errores;
  public String lexema() {return yytext();}
  public int fila() {return yyline+1;}
  public int columna() {return yychar +1 - charline;}
  private void refrescaColumna() {
    charline = yychar + yytext().length();
  }
  public void fijaGestionErrores(GestionErroresTiny errores) {
   this.errores = errores;
  }
%}

%eofval{
  return ops.unidadEof();
%eofval}

%init{
  ops = new ALexOperations(this);
%init}

letra  = ([A-Z]|[a-z])
digitoPositivo = [1-9]
digito = ({digitoPositivo}|0)
separador = [ \t\b]
newline = [\r\n]
comentario = ((//({letra}|{digito}|{separador})*{newline})|/\*({letra}|{digito}|{separador}|{newline})*\*/)

main = Main
while = while
if = if
else = else
end = end 
print = print
switch = switch
case = case
struct = struct
enum = enum
bartolo = bartolo
new = new
return = return 

int = int
void = void
char = char
string = String
bool = bool
true = true
false = false

parentesisApertura = \(
parentesisCierre = \)
llaveApertura = \{
llaveCierre = \}
corcheteApertura = \[
corcheteCierre = \]

igual = \=
punto = \.
coma  = ,
puntoycoma = ;
dosPuntos = :
operadorMasMas = \+\+
operadorMenosMenos = \-\-
operadorSuma = \+
operadorResta = \-
operadorMultiplicacion = \*
operadorDivision = /
operadorModulo = %
operadorAnd = &&
operadorOr = \|\|
operadorDesigual = !\=
operadorNot = !
operadorIgualIgual = \=\=
operadorMayorIgual = >\=
operadorMayor = >
operadorMenorIgual = <\=
operadorMenor = <

caracterAux = ({letra}|{digito}|{simbolo}|{separador})
cadena = \"{caracterAux}*\"
numeroEntero = ({digitoPositivo}{digito}*|[0])
simbolo = (,|\.|;|:|!|\?|¿|\(|\)|\{|\}|\[|\])
identificador = {letra}({letra}|{digito})*
caracter = '{caracterAux}'



%%

{newline}                 {refrescaColumna();}
{separador}               {}
{comentario}              {refrescaColumna();}


{numeroEntero}            {return ops.unidadEnt();}
{caracter}                {return ops.unidadCaracter();}
{cadena}                  {return ops.unidadCadena();}

{main}                    {return ops.unidadMain();}
{while}                   {return ops.unidadWhile();}
{if}                      {return ops.unidadIf();}
{else}                    {return ops.unidadElse();}
{end}                     {return ops.unidadEnd();}
{print}                   {return ops.unidadPrint();}
{switch}                  {return ops.unidadSwitch();}
{case}                    {return ops.unidadCase();}
{struct}                  {return ops.unidadStruct();}
{enum}                    {return ops.unidadEnum();}
{bartolo}                 {return ops.unidadBartolo();}
{return}                  {return ops.unidadReturn();}

{int}                     {return ops.unidadInt();}
{void}                    {return ops.unidadVoid();}
{char}                    {return ops.unidadChar();}
{string}                  {return ops.unidadString();}
{bool}                    {return ops.unidadBool();}
{true}                    {return ops.unidadTrue();}
{false}                   {return ops.unidadFalse();}

{parentesisApertura}      {return ops.unidadParentesisApertura();}
{parentesisCierre}        {return ops.unidadParentesisCierre();}
{llaveApertura}           {return ops.unidadLlaveApertura();}
{llaveCierre}             {return ops.unidadLlaveCierre();}
{corcheteApertura}        {return ops.unidadCorcheteApertura();}
{corcheteCierre}          {return ops.unidadCorcheteCierre();}

{punto}                   {return ops.unidadPunto();}  
{coma}                    {return ops.unidadComa();}     
{puntoycoma}              {return ops.unidadPuntoYComa();}  
{dosPuntos}               {return ops.unidadDosPuntos();}

{operadorMasMas}          {return ops.unidadMasMas();}
{operadorMenosMenos}      {return ops.unidadMenosMenos();}
{operadorSuma}            {return ops.unidadSuma();}
{operadorResta}           {return ops.unidadResta();}
{operadorMultiplicacion}  {return ops.unidadMul();}
{operadorDivision}        {return ops.unidadDiv();}
{operadorModulo}          {return ops.unidadModulo();}
{operadorAnd}             {return ops.unidadAnd();}
{operadorOr}              {return ops.unidadOr();}
{operadorDesigual}        {return ops.unidadDesigual();}
{operadorNot}             {return ops.unidadNot();}
{operadorIgualIgual}      {return ops.unidadIgualIgual();}
{operadorMayorIgual}      {return ops.unidadMayorIgual();}
{operadorMayor}           {return ops.unidadMayor();}
{operadorMenorIgual}      {return ops.unidadMenorIgual();}
{operadorMenor}           {return ops.unidadMenor();}
{igual}                   {return ops.unidadIgual();} 
{identificador}           {return ops.unidadId();}

[^]                       {errores.errorLexico(fila(),lexema());}  
