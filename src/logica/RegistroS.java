/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author Adrian
 */
public abstract class RegistroS 
{
    private TipoDato tipo;
    private String token;
    private TipoRegistro tipoRegistro;
    
    
 
    /**
     * @return the tipo
     */
    public TipoDato getTipo() {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(TipoDato tipo) {
        this.tipo = tipo;
    }

    /**
     * @return the token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token the token to set
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * @return the tipoRegistro
     */
    public TipoRegistro getTipoRegistro() {
        return tipoRegistro;
    }

    /**
     * @param tipoRegistro the tipoRegistro to set
     */
    public void setTipoRegistro(TipoRegistro tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
}
