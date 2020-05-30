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
import java.util.Stack;
import java_cup.runtime.Symbol;

/**
 *
 * @author Tuco
 */
public class Scanner {
 
    String errores = new String();
 public static void generarLexer(String path)
    {
        File file = new File(path);
        JFlex.Main.generate(file);
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
        String error = new String();
        
        FileWriter archivo = new FileWriter("error.txt");
        BufferedWriter ex = new BufferedWriter(archivo);
        ex.write("Errores sintacticos: \n");
        ex.close();
        //"C:/Users/Tuco/Documents/NetBeansProjects/Scanner/
        try {
            parser p = new parser(new Yylex(reader));
            p.parse();
            errores = p.ERRORESSEM();
            System.out.println("hola");
            
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            if (e.getMessage().equals("65533")){
            BufferedWriter out = new BufferedWriter(archivo);
            out.write("CORRIJA LOS SIGUIENTES ERRORES LEXICOS: \n");
            out.close();}
        }
        
    }

public String ERRORESSEM(){
            return errores;
        }
        
    
}

