/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

import java.util.ArrayList;

/**
 *
 * @author Adrian
 */
public class GeneradorASM 
{

    public GeneradorASM()
    {
        
    }
    
    
    public void traducirEndIf(String pCadena, String pLabel)
    {
        String[] cadSplit = pCadena.split("¥");
        ArrayList<String> arrReverse = new ArrayList<>();
        String resultASM ="";
        for(int i = cadSplit.length-1;i>0 ; i--)
            {
                arrReverse.add(cadSplit[i]);
            }
       for(String str: arrReverse)
        {
            //resultASM = resultASM +" "+traducirStrBloque(str);
            
        }
       resultASM= resultASM.concat("\n").concat("JMP ").concat(pLabel);
    }
    
    public void traducirAssig(String pCadena)
    {
        String[] cadSplit = pCadena.split("¥");
        ArrayList<String> arrReverse = new ArrayList<>();
        String resultASM ="";
        for(int i = cadSplit.length-1;i>0 ; i--)
            {
                arrReverse.add(cadSplit[i]);
            }
       for(String str: arrReverse)
        {
            //resultASM = resultASM +" "+traducirStrBloque(str);
            
        }
       //resultASM= resultASM.concat("\n").concat("JMP ").concat(pLabel);
    }
    
    public void traducirReturn(String pCadena)
    {
        String[] cadSplit = pCadena.split("¥");
        ArrayList<String> arrReverse = new ArrayList<>();
        String resultASM ="";
        for(int i = cadSplit.length-1;i>0 ; i--)
            {
                arrReverse.add(cadSplit[i]);
            }
       for(String str: arrReverse)
        {
            resultASM = resultASM +" "+traducirStrReturn(str);
            
        }
       //resultASM= resultASM.concat("\n").concat("JMP ").concat(pLabel);
        System.out.println("ARO");
    }
    
    
    public String traducirStrReturn(String str)
    {
        String cadenaResult = "";
        switch(str)
        {
            case "return":
                {
                   cadenaResult = "MOV AX,";
                   return cadenaResult;
                }
            default: {break;}
        }
        try
            {
                int numero = Integer.parseInt(str);
                cadenaResult = String.valueOf(numero);
                return cadenaResult;
            }
        catch(NumberFormatException ex)
            {
                //es un id lo que quiere retornar
                cadenaResult = str;
                return cadenaResult;
                
            }
        
    }
    
    
    public static String traducirStrBloque(String str)
    {
        switch(str)
            {
            case ";":
                {
                    //(
                    return "(";
                }
            default:
                {
                    break;
                }
            }
        try
            {
                int numero = Integer.parseInt(str);
                return String.valueOf(numero);
            }
        catch(NumberFormatException ex)
            {
                //es una variable
                return str;
            }
        
    }
    
    
    public void traducirCondicion(String pCadena, String pLabel)
    {
        String[] cadSplit = pCadena.split("¥");
        ArrayList<String> arrReverse = new ArrayList<>();
        String resultASM ="";
        for(int i = cadSplit.length-1;i>0 ; i--)
            {
                arrReverse.add(cadSplit[i]);
            }
       for(String str: arrReverse)
        {
            resultASM = resultASM +" "+traducirStrCond(str);
            
        }
       resultASM= resultASM.concat("\n").concat("JMP ").concat(pLabel);
    }
    
    public static String traducirStrCond(String str)
    {
        switch(str)
            {
            case "(":
                {
                    //(
                    return "(";
                }
            case ")":
                {
                    //)
                    return ")";
                }
            case "&&":
                {
                    //AND
                    return "AND";
                }
            case "||":
                {
                    //OR
                    return "OR";
                }
            case "!":
                {
                    //NOT
                    return "NOT";
                }
            case "==":
                {
                    //EQ
                    return "EQ";
                }
            case "<":
                {
                    //less than
                    return "LT";
                }
            case ">":
                {
                    return "GT";
                }  
            case "<=":
                {
                    return "LE";
                }
            case ">=":
                {
                    return "GE";
                } 
            case "!=":
                {
                    return "NE";
                }
            default:
                {
                    break;
                }
            }
        try
            {
                int numero = Integer.parseInt(str);
                return String.valueOf(numero);
            }
        catch(NumberFormatException ex)
            {
                //es una variable
                return str;
            }
        
    }
    
}
