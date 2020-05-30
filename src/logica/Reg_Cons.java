/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Adrian
 */
public class Reg_Cons extends RegistroS 
{
    public Reg_Cons()
        {
            this.setTipoRegistro(TipoRegistro.CONSTANTE);
            this.setTipo(TipoDato.INT);
        }
}
