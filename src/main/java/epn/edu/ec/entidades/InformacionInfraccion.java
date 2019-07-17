package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_info_infraccion")
@XmlRootElement

public class InformacionInfraccion implements Serializable {


    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_info_infrac_pk", nullable = false)  
    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    
    @Size(max = 50)
    @Column(name = "provincia_detencion")
    private String provinciaDetencion;
    
    @Size(max = 50)
    @Column(name = "canton_detencion")
    private String cantonDetencion;
    
    @Size(max = 200)
    @Column(name = "tipo_infraccion")
    private String tipoInfraccion;
    
    public InformacionInfraccion() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public String getProvinciaDetencion() {
        return provinciaDetencion;
    }

    public void setProvinciaDetencion(String provinciaDetencion) {
        this.provinciaDetencion = provinciaDetencion;
    }

    public String getCantonDetencion() {
        return cantonDetencion;
    }

    public void setCantonDetencion(String cantonDetencion) {
        this.cantonDetencion = cantonDetencion;
    }

    public String getTipoInfraccion() {
        return tipoInfraccion;
    }

    public void setTipoInfraccion(String tipoInfraccion) {
        this.tipoInfraccion = tipoInfraccion;
    }

    @Override
    public String toString() {
        return "InformacionInfraccion{" + "idInformacionInfraccion=" + idAdolescenteInfractorUDI + ", provinciaDetencion=" + provinciaDetencion + ", cantonDetencion=" + cantonDetencion + ", tipoInfraccion=" + tipoInfraccion + '}';
    }
   
}
