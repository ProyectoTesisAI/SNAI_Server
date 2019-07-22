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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_info_cambio_med_cai")
@XmlRootElement

public class InformacionCambioMedidaCAI implements Serializable {


    @Id  
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_info_camb_medid_pk_cai", nullable = false)
    private EjecucionMedidaCAI idEjecucionMedidaCAI;
    
    @Column(name = "acept_juez_camb_medi")
    private Boolean aceptacionJuezCambioMedida;
    
    @Size(max = 1000)
    @Column(name = "observaciones")
    private String observaciones;
    
    @Size(max = 50)
    @Column(name = "cambio_med_socioedu")
    private String cambioMedidaSocioeducativa;
    
    @Column(name = "cumpl_60_80_tiem_priv_liber")
    private Integer cumplimieno6080TiempoPrivacionLibertad;
    
    @Column(name = "fecha_cumpl_60_80")
    @Temporal(TemporalType.DATE)
    private Date fechaCumplimiento6080;
    
    @Column(name = "aler_camb_medi")
    @Temporal(TemporalType.DATE)
    private Date alertaCambioMedida;
    
    @Size(max = 50)
    @Column(name = "especif_nuev_medid")
    private String especificacionNuevaMedida;

    public InformacionCambioMedidaCAI() {
    }

    public Boolean getAceptacionJuezCambioMedida() {
        return aceptacionJuezCambioMedida;
    }

    public void setAceptacionJuezCambioMedida(Boolean aceptacionJuezCambioMedida) {
        this.aceptacionJuezCambioMedida = aceptacionJuezCambioMedida;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getCambioMedidaSocioeducativa() {
        return cambioMedidaSocioeducativa;
    }

    public void setCambioMedidaSocioeducativa(String cambioMedidaSocioeducativa) {
        this.cambioMedidaSocioeducativa = cambioMedidaSocioeducativa;
    }

    public Integer getCumplimieno6080TiempoPrivacionLibertad() {
        return cumplimieno6080TiempoPrivacionLibertad;
    }

    public void setCumplimieno6080TiempoPrivacionLibertad(Integer cumplimieno6080TiempoPrivacionLibertad) {
        this.cumplimieno6080TiempoPrivacionLibertad = cumplimieno6080TiempoPrivacionLibertad;
    }

    public Date getFechaCumplimiento6080() {
        return fechaCumplimiento6080;
    }

    public void setFechaCumplimiento6080(Date fechaCumplimiento6080) {
        this.fechaCumplimiento6080 = fechaCumplimiento6080;
    }

    public Date getAlertaCambioMedida() {
        return alertaCambioMedida;
    }

    public EjecucionMedidaCAI getIdEjecucionMedidaCAI() {
        return idEjecucionMedidaCAI;
    }

    public void setIdEjecucionMedidaCAI(EjecucionMedidaCAI idEjecucionMedidaCAI) {
        this.idEjecucionMedidaCAI = idEjecucionMedidaCAI;
    }

    public void setAlertaCambioMedida(Date alertaCambioMedida) {
        this.alertaCambioMedida = alertaCambioMedida;
    }

    public String getEspecificacionNuevaMedida() {
        return especificacionNuevaMedida;
    }

    public void setEspecificacionNuevaMedida(String especificacionNuevaMedida) {
        this.especificacionNuevaMedida = especificacionNuevaMedida;
    }
}
