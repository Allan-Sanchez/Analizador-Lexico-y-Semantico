/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.util.ArrayList;

/**
 *
 * @author Tuco
 */
public class Scanner2 {
    
 private ArrayList<NodoListaTokens>  _ListaPalabrasReservadas = new ArrayList<>();   
 private ArrayList<NodoListaTokens>  _ListaOperadores = new ArrayList<>(); 
 private ArrayList<NodoListaTokens>  _ListaIdentificadores = new ArrayList<>(); 
 private ArrayList<NodoListaTokens>  _ListaLiterales = new ArrayList<>(); 
 private ArrayList<NodoListaTokens>  _ListaErrores = new ArrayList<>();  
 private String CadenaFinalDeTokens = "";
 
 public static void generarLexer(String path)
    {
        //File file = new File(path);
        //JFlex.Main.generate(file);
    }
 
public void ejecutar(String path)
{
        //generarLexer(path);
        try
            {
                probarLexerFile(path);
                System.out.println("TERMINO");
            }
        catch(IOException ex)
            {
            System.out.println(ex.getMessage());
            } 
}


    
public void probarLexerFile(String path) throws IOException
    {
      
        Reader reader = new BufferedReader(new FileReader(path));
        //"C:/Users/Tuco/Documents/NetBeansProjects/Scanner/
        Lexer lexer =  new Lexer(reader);
        while(true)
            {
            Token token = lexer.yylex();
            if(token==null)
                {
                    System.out.println("EOF"+"\n");
                    System.out.println("End of file");
                    return;
                }
            switch(token)
            {       
              case ERROR:
                  System.out.println("Encontro el TOKEN:" + token+" "+lexer.lexeme+" En la linea: "+lexer.demeLineaToken());
                  agregarToken(lexer.lexeme,token,"Error",lexer.demeLineaToken());
                    break;
                  
                default:
                   System.out.println("Encontro el TOKEN:" + token+" "+lexer.lexeme+" En la linea: "+lexer.demeLineaToken());
            }
                
            }
    }


public int member(String pLexema,ArrayList<NodoListaTokens> pLista)
{
    for(NodoListaTokens nod: pLista )
    {
        if(pLexema.endsWith(nod.getLexema()))
            //devolver la posicion de ese nodo
            return pLista.indexOf(nod);
    }
    return -1;   
}


public void agregarAparicion(String pLexema,Token pToken,String pTipoToken, int pLinea,ArrayList<NodoListaTokens> pLista)
{
    int indice = 0;
    indice = member(pLexema,pLista);
    if(indice == -1)
        {  
          //No existe aun, creo un nuevo NodoListaTokens y lo inserto en la lista
          pLista.add(new NodoListaTokens(pLexema,pToken,pTipoToken,pLinea)); 
        }
    else
        {
           //Busco la posicion de ese NodoListaTokens en la lista y le agrego una linea
           pLista.get(indice).agregarLinea(pLinea);
        } 
}


public void agregarToken(String pLexema,Token token ,String pTipoToken, int pLinea)
{
    switch(pTipoToken)
        {
         case "Error":
            {
              agregarAparicion(pLexema,token,"Error",pLinea,_ListaErrores);  
              break;
            }
        default: break;   
        }
    }
    

    public String conviertaListaEnCadena(ArrayList<NodoListaTokens> pListaEntrada)
    {
        String cadena = "";
                        for(NodoListaTokens nodo: pListaEntrada)
                            {
                                cadena+= nodo.getLexema() + " - ";
                                //imprimo el token
                                cadena+= nodo.getToken() + " - ";
                                //imprimo el tipoToken
                                cadena+= nodo.getTipoToken() + " - ";
                                //imprimo toda la lista de lineas
                                
                                for(Integer entero: nodo.getListaLineas())
                                {
                                cadena+= entero + ",";    
                                }
                                
                                cadena+= "\r\n"; 
                            }
                        return cadena;
    }
    
    
    public void escribirCadenasEnarchivo(String cad5)
    {
            File f;
	    FileWriter escritorArchivo;
	    
	        try{
	            	f= new File("tokens.txt"); //pruta
	            	escritorArchivo = new FileWriter("tokens.txt");
	            	BufferedWriter bw = new BufferedWriter(escritorArchivo);
	            	PrintWriter salida = new PrintWriter(bw);
                        CadenaFinalDeTokens = "\nErrores léxicos encontrados: \r\n" +cad5;  
                        salida.write(CadenaFinalDeTokens);
	            	salida.close();
	        	}
	        catch(IOException e){System.out.println("Error:"+e.getMessage());}  
    }
    
    
    public ArrayList<NodoListaTokens> getListaOperadores()
    {
        return _ListaOperadores;
        
    }
    
    
    public ArrayList<NodoListaTokens> getListaLiterales()
    {
        return _ListaLiterales;
        
    }
    
    
    public ArrayList<NodoListaTokens> getListaReservadas()
    {
        return _ListaPalabrasReservadas;
        
    }
    
    
    public ArrayList<NodoListaTokens> getListaIdentificadores()
    {
        return _ListaIdentificadores;
        
    }
    
    public ArrayList<NodoListaTokens> getListaErrores()
    {
        return _ListaErrores;
        
    }
    
    public String getCadenaFinalTokens()
    {
        return this.CadenaFinalDeTokens;
    }
    
}

