/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Adrian
 */
public class NodoTS 
{
    private TipoEstructura _TipoEstructura;
    private TipoDato _TipoDato;
    private String _id;

    /**
     * @return the _TipoEstructura
     */
    public TipoEstructura getTipoEstructura() {
        return _TipoEstructura;
    }

    /**
     * @param TipoEstructura the _TipoEstructura to set
     */
    public void setTipoEstructura(TipoEstructura TipoEstructura) {
        this._TipoEstructura = TipoEstructura;
    }

    /**
     * @return the _TipoDato
     */
    public TipoDato getTipoDato() {
        return _TipoDato;
    }

    /**
     * @param TipoDato the _TipoDato to set
     */
    public void setTipoDato(TipoDato TipoDato) {
        this._TipoDato = TipoDato;
    }

    /**
     * @return the _id
     */
    public String getId() {
        return _id;
    }

    /**
     * @param id the _id to set
     */
    public void setId(String id) {
        this._id = id;
    }
    
}
