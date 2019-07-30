package epn.edu.ec.entidades;

public class Reporte7 {
    private Integer numero;
    private String cai_uzdi;
    private String nombres;
    private String apellidos;
    private String tipoDelto;
    private String lugarResidencia;

    public Reporte7() {
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public String getCai_uzdi() {
        return cai_uzdi;
    }

    public void setCai_uzdi(String cai_uzdi) {
        this.cai_uzdi = cai_uzdi;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTipoDelto() {
        return tipoDelto;
    }

    public void setTipoDelto(String tipoDelto) {
        this.tipoDelto = tipoDelto;
    }

    public String getLugarResidencia() {
        return lugarResidencia;
    }

    public void setLugarResidencia(String lugarResidencia) {
        this.lugarResidencia = lugarResidencia;
    }

    @Override
    public String toString() {
        return "Reporte7{" + "numero=" + numero + ", cai_uzdi=" + cai_uzdi + ", nombres=" + nombres + ", apellidos=" + apellidos + ", tipoDelto=" + tipoDelto + ", lugarResidencia=" + lugarResidencia + '}';
    }
    
    
}
