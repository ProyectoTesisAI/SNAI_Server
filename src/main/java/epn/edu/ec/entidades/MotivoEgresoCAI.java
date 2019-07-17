package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_motivo_egreso_cai")
@XmlRootElement

public class MotivoEgresoCAI implements Serializable {


    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_motivo_egreso_cai_pk", nullable = false)    
    private AdolescenteInfractorCAI idAdolescenteInfractorCAI;
    
    @Size(max = 50)
    @Column(name = "motivo_salida")
    private String motivoSalida;
    
    @Column(name = "fecha_salida_centro")
    @Temporal(TemporalType.DATE)
    private Date fechaSalidaCentro;
    
    @JoinColumn(name = "cai_traslado_fk")
    @ManyToOne
    private CAI idCaiTraslado;

    public MotivoEgresoCAI() {
    }

    public AdolescenteInfractorCAI getIdAdolescenteInfractorCAI() {
        return idAdolescenteInfractorCAI;
    }

    public void setIdAdolescenteInfractorCAI(AdolescenteInfractorCAI idAdolescenteInfractorCAI) {
        this.idAdolescenteInfractorCAI = idAdolescenteInfractorCAI;
    }

    public String getMotivoSalida() {
        return motivoSalida;
    }

    public void setMotivoSalida(String motivoSalida) {
        this.motivoSalida = motivoSalida;
    }

    public Date getFechaSalidaCentro() {
        return fechaSalidaCentro;
    }

    public void setFechaSalidaCentro(Date fechaSalidaCentro) {
        this.fechaSalidaCentro = fechaSalidaCentro;
    }

    public CAI getIdCaiTraslado() {
        return idCaiTraslado;
    }

    public void setIdCaiTraslado(CAI idCaiTraslado) {
        this.idCaiTraslado = idCaiTraslado;
    }

    @Override
    public String toString() {
        return "MotivoEgresoCAI{" + "idMotivoEgreso=" + idAdolescenteInfractorCAI + ", motivoSalida=" + motivoSalida + ", fechaSalidaCentro=" + fechaSalidaCentro + ", caiTrasladoFk=" + idCaiTraslado + '}';
    }
    
}
