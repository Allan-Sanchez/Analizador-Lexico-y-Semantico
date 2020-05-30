/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.util.ArrayList;

/**
 *
 * @author Tuco
 */
public class NodoListaTokens {
    
    private String lexema;
    private Token token;
    private String tipoToken;
    private ArrayList<Integer> lineasToken;
    
    public NodoListaTokens(String pLexema,Token pToken, String pTipoToken,int pLineaToken)
    {
        lexema = pLexema;
        token = pToken;
        tipoToken = pTipoToken;
        lineasToken   = new ArrayList<Integer>();
        lineasToken.add(pLineaToken);
    }
 
    public Token getToken()
    {
        return token;
    }
    
    public String getTipoToken()
    {
        return tipoToken;
    }
    
    //Agrega un nuevo int de numero de Linea en el arreglo de Lineas del nodo que se busco por el nombre del token.
    public void agregarLinea(int pLinea)
    {
        lineasToken.add(pLinea);
    }
    
    public ArrayList<Integer> getListaLineas()
    {
        return lineasToken;
    }
    
    public String getLexema()
    {
        return lexema;
    }
}
