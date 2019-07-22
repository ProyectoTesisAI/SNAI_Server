package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_motivo_egreso_cai")
@XmlRootElement

public class MotivoEgresoCAI implements Serializable {


    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_motivo_egreso_cai_pk", nullable = false)    
    private EjecucionMedidaCAI idEjecucionMedidaCAI;
    
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

    public EjecucionMedidaCAI getIdEjecucionMedidaCAI() {
        return idEjecucionMedidaCAI;
    }

    public void setIdEjecucionMedidaCAI(EjecucionMedidaCAI idEjecucionMedidaCAI) {
        this.idEjecucionMedidaCAI = idEjecucionMedidaCAI;
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

    
}
