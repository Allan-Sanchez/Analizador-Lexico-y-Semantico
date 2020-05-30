/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package scanner;

import java.util.ArrayList;
import java.util.Stack;
import logica.GeneradorASM;
import logica.NodoTS;
import logica.Reg_Cons;
import logica.Reg_If;
import logica.Reg_Var;
import logica.Reg_While;
import logica.RegistroS;
import logica.TipoDato;
import logica.TipoEstructura;
import logica.TipoRegistro;

/**
 *
 * @author Adrian
 */



public class Semantico {
    
private static Stack<RegistroS> pilaSemantica = new Stack(); 
private static ArrayList<NodoTS> TablaSimbolos = new ArrayList<>();
private static boolean banderaGen = true;
private static int contadorLBL =0;
private static GeneradorASM GeneradorASM= new GeneradorASM();
public static String stringerrores = new String();
  
    /*****************    A = 3   Data Object DO    ************************/
    public String ERRORESSEM(){
        return stringerrores;
    }

    public static void Cons(int num, String ele){
        
        Reg_Cons REG_CONS = new Reg_Cons();
        REG_CONS.setToken(ele);
        pilaSemantica.push(REG_CONS);
         
        System.out.println("Cons");
        int i = Integer.parseInt(ele);
        System.out.println(i);
    }
    
    public static void Var(int num, String ele){
        
        Reg_Var REG_VAR = new Reg_Var();
        REG_VAR.setTipo(TipoDato.INT);
        REG_VAR.setToken(ele);
       
        if(existeRegistro(REG_VAR,TipoEstructura.VARIABLE))
            {
                //ya esta definida
                //si lo puede usar
                pilaSemantica.push(REG_VAR);
            }
        else
            {
                //no existe, tengo que crearlo
                System.out.println("ERROR SEMANTICO: variable no declarada");
                stringerrores.concat("ERROR SEMANTICO: variable ".concat(ele).concat("no declarada"));
                //apago bandera de generacion de codigo
                banderaGen = false;
                NodoTS nodoNuevo = new NodoTS();
                nodoNuevo.setId(REG_VAR.getToken());
                nodoNuevo.setTipoDato(REG_VAR.getTipo());
                nodoNuevo.setTipoEstructura(TipoEstructura.VARIABLE);
                TablaSimbolos.add(nodoNuevo);
                //push del ERROR
            }
       
        System.out.println("Var");
        System.out.println(ele);
    }
    
   public static void Assig(){
        //generarBloque que esta Adentro ( lo que agarre de la pila semantica con pops hasta llegar al label de la funcion
       String codigoGenerar = demeStringAssig();
         
        if(banderaGen)
        {
            //generarCodigoASM(codigoGenerar);
            GeneradorASM.traducirAssig(codigoGenerar);
        }
        
        pilaSemantica.pop();  
       System.out.println("Assign");
    }
   
   public static void Ret(){
        //generarBloque que esta Adentro ( lo que agarre de la pila semantica con pops hasta llegar al label de la funcion
       TipoDato comprobarRet = CompRet();
        String cadena = pilaSemantica.pop().getToken();
        String cadena1 = pilaSemantica.pop().getToken();
        String cadena2 = pilaSemantica.pop().getToken();
        cadena = cadena+"¥"+cadena1+"¥"+cadena2;
        
        if(comprobarRet == TipoDato.INT)
        {
            //generarCodigoASM(codigoGenerar);
            GeneradorASM.traducirReturn(cadena);
        }
        else{System.out.println("ERROR SEMANTICO: funcion no tiene valor de retorno");}
        stringerrores.concat("ERROR SEMANTICO: return en una función tipo void");
        //pilaSemantica.pop();  
       //System.out.println("Assign");
    }
    
    public static void Dec_Var(int num, String ele, String tipo){
        
        Reg_Var REG_VAR = new Reg_Var();
        REG_VAR.setTipo(TipoDato.INT);
        REG_VAR.setToken(ele);
       
        if(existeRegistro(REG_VAR,TipoEstructura.VARIABLE))
            {
                //no existe, tengo que crearlo
                System.out.println("ERROR SEMANTICO: variable ya existe");
                stringerrores.concat("ERROR SEMANTICO: variable ".concat(ele).concat("ya declarada"));
                //apago bandera de generacion de codigo
                banderaGen = false;
                //push del ERROR
            }
        else
            {
                NodoTS nodoNuevo = new NodoTS();
                nodoNuevo.setId(REG_VAR.getToken());
                nodoNuevo.setTipoDato(REG_VAR.getTipo());
                nodoNuevo.setTipoEstructura(TipoEstructura.VARIABLE);
                TablaSimbolos.add(nodoNuevo);
                pilaSemantica.push(REG_VAR);   
            }
       
        System.out.println("Var");
        System.out.println(ele);
        
    }
    
    //Es cuando ya esta declarada
    public static void Fun(int num, String ele, String tipo){
        
        Reg_Var REG_FUN = new Reg_Var();
        REG_FUN.setTipo(getTipoDato(tipo));
        REG_FUN.setToken(ele);
       
        if(existeRegistro(REG_FUN,TipoEstructura.METODO))
            {
                
                System.out.println("ERROR SEMANTICO: funcion ya existe");
                stringerrores.concat("ERROR SEMANTICO: funcion ".concat(ele).concat("ya declarada"));
                //apago bandera de generacion de codigo
                banderaGen = false;
                //push del ERROR
            }
        else
            {
                NodoTS nodoNuevo = new NodoTS();
                nodoNuevo.setId(REG_FUN.getToken());
                nodoNuevo.setTipoDato(REG_FUN.getTipo());
                nodoNuevo.setTipoEstructura(TipoEstructura.METODO);
                TablaSimbolos.add(nodoNuevo);
                pilaSemantica.push(REG_FUN); 
            }
       
        System.out.println("Fun");
        System.out.println(tipo);
        System.out.println(ele);
    }
    
    public static void FinFun(){
        //generarBloque que esta Adentro ( lo que agarre de la pila semantica con pops hasta llegar al label de la funcion
        System.out.println("FinFun");
    }
    
    /*****************    Operaciones   ************************/
    public static void Oper(int num, String ele){
        
        Reg_Cons REG_OP = new Reg_Cons();
        REG_OP.setToken(ele);
        pilaSemantica.push(REG_OP);
        
        System.out.println("Oper");
        System.out.println(ele);
    }
  
    public static void eval_unary(String Op1, String Oper){
        
        pilaSemantica.pop();
        pilaSemantica.pop();
        
        try
            {
                int numero = Integer.parseInt(Op1);
                if(Oper.equals("++"))
                    {
                        numero++;
                        Reg_Cons REG_CONS = new Reg_Cons();
                        REG_CONS.setToken(String.valueOf(numero));
                        pilaSemantica.push(REG_CONS);
                    }
               else
                    {
                        numero--;
                        Reg_Cons REG_CONS = new Reg_Cons();
                        REG_CONS.setToken(String.valueOf(numero));
                        pilaSemantica.push(REG_CONS);
                    }
            }
        catch(NumberFormatException nfe)
            {
                //generarCodigo de una variable ++
            }
        
        
        /*
         POP Op1, Oper, Op2
         check tipos (Op1, Oper, Op2)
         generar codigo (operacion -> T)
         Crear DO=T
         Push DO
         */
        System.out.println("eval_unary");
        System.out.println(Op1);
        System.out.println(Oper);
    }
    
    
    public static void eval_binary(String Op1, String Oper, String Op2){
        /*
         POP Op1, Oper, Op2
         check tipos (Op1, Oper, Op2)
         generar codigo (operacion -> T)
         Crear DO=T
         Push DO
         */
        
        pilaSemantica.pop();
        pilaSemantica.pop();
        RegistroS REG_BINARY = pilaSemantica.pop();
         try
            {
                int numero1 = Integer.parseInt(REG_BINARY.getToken());
                int numero2 = Integer.parseInt(Op2);
                if(Oper.equals("+"))
                    {
                        numero1+=numero2;
                    }
               if(Oper.equals("-"))
                    {
                        numero1-=numero2;
                    }
               if(Oper.equals("*"))
                    {
                        numero1*=numero2;
                    }
               if(Oper.equals("/"))
                    {
                        numero1/=numero2;
                    }
               if(Oper.equals("%"))
                    {
                        numero1%=numero2;
                    }
                Reg_Cons REG_CONS = new Reg_Cons();
                REG_CONS.setToken(String.valueOf(numero1));
                pilaSemantica.push(REG_CONS);
            }
        catch(NumberFormatException nfe)
            {
                String cadena = REG_BINARY.getToken().concat(Oper.concat(Op2));
                REG_BINARY.setToken(cadena);
                pilaSemantica.push(REG_BINARY);
            }
        System.out.println("eval_binary");
        System.out.println(Op1);
        System.out.println(Oper);
        System.out.println(Op2);
    }
    
    public static void Par(String Paren){
        /*
         generar cod_evaluacion
         jmp Reg_If.label
         */
        Reg_Cons REG_PAR = new Reg_Cons();
        REG_PAR.setToken(Paren);
        pilaSemantica.push(REG_PAR);
    }
    
    public static void Test(ArrayList<String> lista){
        /*
         generar cod_evaluacion
         jmp Reg_If.label
         */
        String codigoGenerar = demeStringCondicion();
         
        if(banderaGen)
        {
           
            //generarCodigoASM(codigoGenerar);
            GeneradorASM.traducirCondicion(codigoGenerar,"label".concat(String.valueOf(contadorLBL)));
            contadorLBL--;
        }
       
    }
    
    
    public static String demeStringCondicion()
    {
        String cadenaResultado ="";
        while(true)
            {
                RegistroS temp;
                temp = pilaSemantica.pop();
                if(temp.getTipoRegistro() == TipoRegistro.IF)
                    {
                        return cadenaResultado;
                    }
                if(temp.getTipoRegistro() == TipoRegistro.WHILE)
                    {
                        return cadenaResultado;
                    }
                else
                    {
                        cadenaResultado = cadenaResultado+"¥"+temp.getToken();
                    }
            }
        
    }
    
    
    /******************         IF -> if^StartIf (exp)^TestIf {Bloque}^EndIf         **************************/
    public void StartIf(int num, String ele){
        /*
         Crear Reg_If
         Reg_If.label => generate();
         Push Reg_If
         */
        Reg_If REG_IF = new Reg_If();
        contadorLBL++;
        REG_IF.setLabel(REG_IF.generarLabel(contadorLBL));
        pilaSemantica.push(REG_IF);

        System.out.println("StartIf");
        System.out.println(ele);
    }
    
    public static void EndIf(){ 
        /*
         generar codigo(Reg_If.label + ":")
         Pop Reg_Do
         Pop Reg_If
         */
       String codigoGenerar = demeStringBloque();
         
        if(banderaGen)
        {
            //generarCodigoASM(codigoGenerar);
            GeneradorASM.traducirEndIf(codigoGenerar,"label".concat(String.valueOf(contadorLBL)));
            contadorLBL--;
        }
        
        pilaSemantica.pop(); 
        System.out.println("EndIf");
    }
    
    public static String demeStringBloque()
    {
        String cadenaResultado ="";
        while(true)
            {
                RegistroS temp;
                temp = pilaSemantica.pop();
                if(temp.getToken().equals("{"))
                    {
                        return cadenaResultado;
                    }   
                else
                    {
                        cadenaResultado = cadenaResultado+"¥"+temp.getToken();
                    }
            }
    }
    
    public static String demeStringAssig()
    {
        String cadenaResultado ="";
        while(true)
            {
                RegistroS temp;
                temp = pilaSemantica.pop();
                if(temp.getToken().equals("="))
                    {
                        cadenaResultado = cadenaResultado+"¥"+temp.getToken();
                        temp = pilaSemantica.pop();
                        cadenaResultado = cadenaResultado+"¥"+temp.getToken();
                        return cadenaResultado;
                    }   
                else
                    {
                        cadenaResultado = cadenaResultado+"¥"+temp.getToken();
                    }
            }
    }
    
    public static TipoDato CompRet()
    {
        String cadenaResultado ="";
        int ind = pilaSemantica.size()-1;
        while(true)
            {
                 RegistroS temp;
                temp = pilaSemantica.get(ind);
                Reg_Var REG_FUN = new Reg_Var();
                REG_FUN.setTipo(temp.getTipo());
                REG_FUN.setToken(temp.getToken());
                ind--;
       
        if(existeRegistro(REG_FUN,TipoEstructura.METODO))
            {
                
                return REG_FUN.getTipo();
                //push del ERROR
            }
            }
    }
    
    /****************     IF-ELSE -> if^StartIfElse (exp)^TestIfElse {Bloque} else^Else Bloque^EndIfElse  ******/
    public static void StartIfElse(int num, String ele){
        /*
         Crear Reg_If
         Reg_If.label => generate();
         Push Reg_If
         */
        Reg_If REG_IF = new Reg_If();
        contadorLBL++;
        REG_IF.setLabel(REG_IF.generarLabel(contadorLBL));
        pilaSemantica.push(REG_IF);
        System.out.println("StartIfElse");
        System.out.println(ele);
    }
    
    public static void StartElse(int num, String ele){
        
        

        System.out.println("StartElse");
        System.out.println(ele);
    }
    
    public static void EndIfElse(){
        /*
         generar codigo(Reg_If.label + ":")
         Pop Reg_Do
         Pop Reg_If
         */
        System.out.println("EndElse");
    }
    
    /****************           WHILE -> while^StartWhile (exp)^TestWhile {Bloque} ^EndWhile        ************/
    public static void StartWhile(int num, String ele){
        /*
         crear registro_while
         registro_while.L1 <- generate()
         registro_while.Exit_Label <- generate()
         push registro_while
         Generar(registro_while.L1 + ":")
         */
        Reg_While REG_W = new Reg_While();
        contadorLBL++;
        REG_W.setLabelnicio(REG_W.generarLabel(contadorLBL));
        REG_W.setLabelFin(REG_W.generarLabel(contadorLBL));
        pilaSemantica.push(REG_W);
        //generarCodigoStartWhile();
        
        System.out.println("StartWhile");
        System.out.println(ele);
    }
    
    public static void EndWhile(){
        /*
         generar("jmp" + registro_while.L1)
         generar(registro_while.exit_label+":")
         pop registro_while
         */
        pilaSemantica.pop();
        System.out.println("EndWhile");
    }
    
    
    public static boolean existeRegistro(RegistroS pRegistro, TipoEstructura pTipoEstructura)
    {
        NodoTS nodoNuevo = new NodoTS();
        nodoNuevo.setTipoDato(pRegistro.getTipo());
        nodoNuevo.setId(pRegistro.getToken());
        nodoNuevo.setTipoEstructura(pTipoEstructura);
        
        if(member(nodoNuevo))
            {
                //ya esta definido en la tabla Simbolos
                return true;
            }
        else
            {
                //no esta definido en la tabla simbolos
                return false;
            }
    }
    
    
    public static TipoDato getTipoDato(String pTipo)
    {
        if(pTipo.equals("int"))
            {
                return TipoDato.INT;
            }
        else
            {
                return TipoDato.VOID;
            }
    }
    
    
    public static boolean member(NodoTS pNodo)
    {
        for(NodoTS nodo: TablaSimbolos)
            {
                if(     (nodo.getTipoDato() == pNodo.getTipoDato()) &&
                        (nodo.getTipoEstructura() == pNodo.getTipoEstructura()) &&
                        (nodo.getId().equals(pNodo.getId())))
                    {
                        return true;
                    }   
            }
        return false;
    }
    
    
    
}

