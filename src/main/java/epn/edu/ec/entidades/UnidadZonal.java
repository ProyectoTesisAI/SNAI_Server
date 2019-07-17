package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_unidad_zonal")
@XmlRootElement

public class UnidadZonal implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_unidad_zonal_pk",nullable = false)  
    private AdolescenteInfractorUDI idUnidadZonal;
    
    @Size(max = 10)
    @Column(name = "num_expediente")
    private String numeroExpediente;
    
    @JoinColumn(name = "id_udi_fk")
    @ManyToOne
    private UDI idUdi;

    public UnidadZonal() {
    }

    public AdolescenteInfractorUDI getIdUnidadZonal() {
        return idUnidadZonal;
    }

    public void setIdUnidadZonal(AdolescenteInfractorUDI idUnidadZonal) {
        this.idUnidadZonal = idUnidadZonal;
    }


    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public UDI getIdUdi() {
        return idUdi;
    }

    public void setIdUdi(UDI idUdi) {
        this.idUdi = idUdi;
    }

    @Override
    public String toString() {
        return "UnidadZonal{" + "idUnidadZonal=" + idUnidadZonal + ", numeroExpediente=" + numeroExpediente + ", idUdi=" + idUdi + '}';
    }

}
