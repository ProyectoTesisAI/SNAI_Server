package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_registro_fotografico")
@XmlRootElement

public class RegistroFotografico implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reg_fotografico_pk")
    private Integer idRegistroFotografico;
    
    @Lob
    @Column(name = "imagen")
    private byte[] imagen;
   
    @JoinColumn(name = "id_informe_fk")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Informe idInforme;
    
    public RegistroFotografico() {
    }

    public Integer getIdRegistroFotografico() {
        return idRegistroFotografico;
    }

    public void setIdRegistroFotografico(Integer idRegistroFotografico) {
        this.idRegistroFotografico = idRegistroFotografico;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Informe getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(Informe idInforme) {
        this.idInforme = idInforme;
    }

    
}
