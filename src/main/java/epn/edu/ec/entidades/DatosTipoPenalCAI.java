package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_datos_tipo_penal_cai")
@XmlRootElement

public class DatosTipoPenalCAI implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_tipo_penal_cai_pk")
    private Integer idDatosTipoPenal;

    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "tipo_penal")
    private String tipoPenal;

    public DatosTipoPenalCAI() {
    }

    public DatosTipoPenalCAI(Integer idDatosTipoPenal) {
        this.idDatosTipoPenal = idDatosTipoPenal;
    }

    public DatosTipoPenalCAI(Integer idDatosTipoPenalCai, String tipoPenal) {
        this.idDatosTipoPenal = idDatosTipoPenalCai;
        this.tipoPenal = tipoPenal;
    }

    public Integer getIdDatosTipoPenal() {
        return idDatosTipoPenal;
    }

    public void setIdDatosTipoPenal(Integer idDatosTipoPenal) {
        this.idDatosTipoPenal = idDatosTipoPenal;
    }

    public String getTipoPenal() {
        return tipoPenal;
    }

    public void setTipoPenal(String tipoPenal) {
        this.tipoPenal = tipoPenal;
    }

    @Override
    public String toString() {
        return "DatosTipoPenalCAI{" + "idDatosTipoPenal=" + idDatosTipoPenal + ", tipoPenal=" + tipoPenal + '}';
    }
    
}
