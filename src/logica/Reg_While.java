/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Adrian
 */
public class Reg_While extends RegistroS
{
    private String labelnicio;
    private String labelFin;
    
    public Reg_While()
    {
        this.setTipoRegistro(TipoRegistro.WHILE);
    }

    public String generarLabel(int pNumero)
    {
        return "labelWhile"+pNumero;
    }
    
    /**
     * @return the labelnicio
     */
    public String getLabelnicio() {
        return labelnicio;
    }

    /**
     * @param labelnicio the labelnicio to set
     */
    public void setLabelnicio(String labelnicio) {
        this.labelnicio = labelnicio;
    }

    /**
     * @return the labelFin
     */
    public String getLabelFin() {
        return labelFin;
    }

    /**
     * @param labelFin the labelFin to set
     */
    public void setLabelFin(String labelFin) {
        this.labelFin = labelFin;
    }
}
