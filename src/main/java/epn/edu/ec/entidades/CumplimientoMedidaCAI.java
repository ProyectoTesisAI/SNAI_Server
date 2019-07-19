package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_cumpl_medida_cai")
@XmlRootElement

public class CumplimientoMedidaCAI implements Serializable {

    
    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_cumpl_medida_cai_pk")
    private EjecucionMedidaCAI idEjecucionMedidaCAI;
    
    @Column(name = "fecha_cumpl_100")
    @Temporal(TemporalType.DATE)
    private Date fechaCumplimiento100;
    
    @Column(name = "aler_camb_medida")
    @Temporal(TemporalType.DATE)
    private Date alertaCambioMedida;

    public CumplimientoMedidaCAI() {
    }

    public EjecucionMedidaCAI getIdEjecucionMedidaCAI() {
        return idEjecucionMedidaCAI;
    }

    public void setIdEjecucionMedidaCAI(EjecucionMedidaCAI idEjecucionMedidaCAI) {
        this.idEjecucionMedidaCAI = idEjecucionMedidaCAI;
    }

    public Date getFechaCumplimiento100() {
        return fechaCumplimiento100;
    }

    public void setFechaCumplimiento100(Date fechaCumplimiento100) {
        this.fechaCumplimiento100 = fechaCumplimiento100;
    }

    public Date getAlertaCambioMedida() {
        return alertaCambioMedida;
    }

    public void setAlertaCambioMedida(Date alertaCambioMedida) {
        this.alertaCambioMedida = alertaCambioMedida;
    }

}
