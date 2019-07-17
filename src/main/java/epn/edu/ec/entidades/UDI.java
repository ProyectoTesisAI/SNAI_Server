package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "t_udi")
@XmlRootElement

public class UDI implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_udi_pk")
    private Integer idUdi;
    
    @Size(max = 50)
    @Column(name = "udi")
    private String udi;
    
    
    public UDI() {
    }

    public Integer getIdUdi() {
        return idUdi;
    }

    public void setIdUdi(Integer idUdi) {
        this.idUdi = idUdi;
    }

    public String getUdi() {
        return udi;
    }

    public void setUdi(String udi) {
        this.udi = udi;
    }

    @Override
    public String toString() {
        return "UDI{" + "idUdi=" + idUdi + ", udi=" + udi + '}';
    }    
}
