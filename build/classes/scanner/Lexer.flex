package scanner;
import java_cup.runtime.Symbol;

%%
%cup
%full
%line
L = [a-zA-Z_]
D = [0-9]
HEX=[a-fA-F0-9]
WHITE=[ \t\r\n]
%{
public String lexeme;
%}
%%
{WHITE} {/*Ignore*/}
"//".* {/*Ignore*/}
"/""*"(.|{WHITE})*"*""/" {/*Ignore*/}
/*Palabras clave*/
"void"	{ return new Symbol(sym.VOID, yychar, yyline, yytext());}
"if"	{ return new Symbol(sym.IF, yychar, yyline, yytext());}		
"else"  { return new Symbol(sym.ELSE, yychar, yyline, yytext());}
"while" { return new Symbol(sym.WHILE, yychar, yyline, yytext());}
"return" { return new Symbol(sym.RETURN, yychar, yyline, yytext());}                   
"return" { return new Symbol(sym.RETURN, yychar, yyline, yytext());}
"int" { return new Symbol(sym.INT, yychar, yyline, yytext());}
"string" { return new Symbol(sym.STRING, yychar, yyline, yytext());}
"read" { return new Symbol(sym.READ, yychar, yyline, yytext());}
"write" { return new Symbol(sym.WRITE, yychar, yyline, yytext());}

/*OPERADORES*/
"," { return new Symbol(sym.COMMA, yychar, yyline, yytext());}
";" { return new Symbol(sym.SEMICOLON, yychar, yyline, yytext());}
"++" { return new Symbol(sym.INCREMENT, yychar, yyline, yytext());}
"--" { return new Symbol(sym.DECREMENT, yychar, yyline, yytext());}
"==" { return new Symbol(sym.EQUALS, yychar, yyline, yytext());}
">=" { return new Symbol(sym.GREATEREQUAL, yychar, yyline, yytext());}
">" { return new Symbol(sym.GREATER, yychar, yyline, yytext());}
"?" { return new Symbol(sym.TERNARYCONDITIONAL, yychar, yyline, yytext());}
"<=" { return new Symbol(sym.LESSEQUAL, yychar, yyline, yytext());}
"<" { return new Symbol(sym.LESS, yychar, yyline, yytext());}
"!=" { return new Symbol(sym.NOTEQUAL, yychar, yyline, yytext());}
"||" { return new Symbol(sym.OR, yychar, yyline, yytext());}
"&&" { return new Symbol(sym.AND, yychar, yyline, yytext());}
"!" { return new Symbol(sym.NOT, yychar, yyline, yytext());}
"=" { return new Symbol(sym.ASSIGN, yychar, yyline, yytext());}
"+" { return new Symbol(sym.ADDITION, yychar, yyline, yytext());}
"-" { return new Symbol(sym.SUBTRACTION, yychar, yyline, yytext());}
"*" { return new Symbol(sym.MULTIPLICATION, yychar, yyline, yytext());}
"/" { return new Symbol(sym.DIVISION, yychar, yyline, yytext());}
"%" { return new Symbol(sym.MODULO, yychar, yyline, yytext());}
"(" { return new Symbol(sym.LEFTPAR, yychar, yyline, yytext());}
")" { return new Symbol(sym.RIGHTPAR, yychar, yyline, yytext());}
"[" { return new Symbol(sym.LEFTSQUARE, yychar, yyline, yytext());}
"]" { return new Symbol(sym.RIGHTSQUARE, yychar, yyline, yytext());}
"{" { return new Symbol(sym.LEFTBRACKET, yychar, yyline, yytext());}
"}" { return new Symbol(sym.RIGHTBRACKET, yychar, yyline, yytext());}
":" { return new Symbol(sym.COLON, yychar, yyline, yytext());}
"." { return new Symbol(sym.STRUCTUREREFERENCE, yychar, yyline, yytext());}
"+=" { return new Symbol(sym.ADDITIONASSIGN, yychar, yyline, yytext());}
"-=" { return new Symbol(sym.SUBTRACTIONASSIGN, yychar, yyline, yytext());}
"*=" { return new Symbol(sym.MULTIPLICATIONASSIGN, yychar, yyline, yytext());}
"/=" { return new Symbol(sym.DIVISIONASSIGN, yychar, yyline, yytext());}
"&" { return new Symbol(sym.BITWISEAND, yychar, yyline, yytext());}
"^" { return new Symbol(sym.BITWISEXOR, yychar, yyline, yytext());}
"|" { return new Symbol(sym.BITWISEOR, yychar, yyline, yytext());}
">>" { return new Symbol(sym.BITWISERIGHT, yychar, yyline, yytext());}
"<<" { return new Symbol(sym.BITWISELEFT, yychar, yyline, yytext());}
"~" { return new Symbol(sym.BITWISENOT, yychar, yyline, yytext());}
"%=" { return new Symbol(sym.MODULOASSIGN, yychar, yyline, yytext());}
"&=" { return new Symbol(sym.BITWISEANDASSIGN, yychar, yyline, yytext());}
"^=" { return new Symbol(sym.BITWISEXORASSIGN, yychar, yyline, yytext());}
"|=" { return new Symbol(sym.BITWISEORASSIGN, yychar, yyline, yytext());}
"<<=" { return new Symbol(sym.BITWISELEFTASSIGN, yychar, yyline, yytext());}
">>=" { return new Symbol(sym.BITWISERIGHTASSIGN, yychar, yyline, yytext());}
"->" { return new Symbol(sym.STRUCTUREDEREFERENCE, yychar, yyline, yytext());}

\" .* \"	{ return new Symbol(sym.LISTSTRING, yychar, yyline, yytext());}
\' .* \'	{ return new Symbol(sym.LISTCHAR, yychar, yyline, yytext());}
[-+]?{D}+ { return new Symbol(sym.ENTERO, yychar, yyline, yytext());}
[-+]?(({D}+)(".")({D}+)) { return new Symbol(sym.FLOTANTE, yychar, yyline, yytext());}
{L} ({L}|{D})* { return new Symbol(sym.ID, yychar, yyline, yytext());}
. { return new Symbol(sym.ERROR, yychar, yyline, yytext());}



