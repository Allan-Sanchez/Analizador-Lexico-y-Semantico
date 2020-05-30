/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Adrian
 */
public class Reg_Var extends RegistroS
    {
        private int valor;

        public Reg_Var()
        {
            this.setTipoRegistro(TipoRegistro.VARIABLE);
        }
    
    /**
     * @return the valor
     */
    public int getValor() {
        return valor;
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(int valor) {
        this.valor = valor;
    }
}