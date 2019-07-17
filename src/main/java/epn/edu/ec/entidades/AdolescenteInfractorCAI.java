package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "t_adolescente_cai")
@XmlRootElement
public class AdolescenteInfractorCAI implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_adolescente_cai_pk", nullable = false)
    private AdolescenteInfractor idAdolescenteInfractor;

    @Column(name = "fecha_ingreso_proceso")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoProceso;
    
    @Size(max = 200)
    @Column(name = "observacion_ingreso")
    private String observacionIngreso;
    
    
    
    public AdolescenteInfractorCAI() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public Date getFechaIngresoProceso() {
        return fechaIngresoProceso;
    }

    public void setFechaIngresoProceso(Date fechaIngresoProceso) {
        this.fechaIngresoProceso = fechaIngresoProceso;
    }

    public String getObservacionIngreso() {
        return observacionIngreso;
    }

    public void setObservacionIngreso(String observacionIngreso) {
        this.observacionIngreso = observacionIngreso;
    }
    
}
