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
@Table(name = "t_cai")
@XmlRootElement

public class CAI implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cai_pk")
    private Integer idCai;
    
    @Size(max = 50)
    @Column(name = "cai")
    private String cai;
    
    public CAI() {
    }

    public Integer getIdCai() {
        return idCai;
    }

    public void setIdCai(Integer idCai) {
        this.idCai = idCai;
    }

    public String getCai() {
        return cai;
    }

    public void setCai(String cai) {
        this.cai = cai;
    }

    @Override
    public String toString() {
        return "CAI{" + "idCai=" + idCai + ", cai=" + cai + '}';
    }
}
