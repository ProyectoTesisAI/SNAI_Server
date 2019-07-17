package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "t_adolescente_udi")
@XmlRootElement
public class AdolescenteInfractorUDI implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_adolescente_udi_pk", nullable = false)
    private AdolescenteInfractor idAdolescenteInfractor;

    @Size(max = 20)
    @Column(name = "con_quien_vive")
    private String conQuienVive;

    
    public AdolescenteInfractorUDI() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public String getConQuienVive() {
        return conQuienVive;
    }

    public void setConQuienVive(String conQuienVive) {
        this.conQuienVive = conQuienVive;
    }

    

}
