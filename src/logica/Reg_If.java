/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Adrian
 */
public class Reg_If extends RegistroS
{
    private String label;
    
    
    public Reg_If()
    {
        this.setTipoRegistro(TipoRegistro.IF);
    }

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }
    
    public String generarLabel(int contador)
    {
        return "label"+contador;
    }
    
}
