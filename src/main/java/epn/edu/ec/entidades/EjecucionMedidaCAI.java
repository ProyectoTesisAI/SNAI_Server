package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "t_ejecucion_medida_cai")
@XmlRootElement

public class EjecucionMedidaCAI implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_ejecucion_medida_cai_pk")
    private Integer idEjecucionMedidaCai;
    
    @JoinColumn(name = "id_deta_infrac_cai_fk", referencedColumnName = "id_deta_infrac_cai_pk")
    @ManyToOne
    private DetalleInfraccionCAI idDetalleInfraccionCAI;
    
    @Column(name = "fecha_aprhension")
    @Temporal(TemporalType.DATE)
    private Date fechaAprehension;

    @Column(name = "fecha_resolucion")
    @Temporal(TemporalType.DATE)
    private Date fechaResolucion;

    @Column(name = "fecha_ingreso_cai")
    @Temporal(TemporalType.DATE)
    private Date fechaIngresoCai;

    @Size(max = 50)
    @Column(name = "tipo_medida")
    private String tipoMedida;

    @Size(max = 500)
    @Column(name = "med_cautelar")
    private String medidaCautelar;

    @Column(name = "alert_salid_inter_preven")
    @Temporal(TemporalType.DATE)
    private Date alertaSalidaIntermediaPreventiva;

    @Column(name = "anios")
    private Integer anios;

    @Column(name = "meses")
    private Integer meses;

    @Column(name = "dias")
    private Integer dias;

    @Column(name = "tiempo_senten_dias")
    private Integer tiempoSentenDias;

    @Column(name = "tiempo_privacion_liber")
    private Integer tiempoPrivacionLibertad;

    @Size(max = 500)
    @Column(name = "obsr_proce_judicial")
    private String observacionesProcesoJudicial;
    
    @JoinColumn(name = "id_cai_fk")
    @ManyToOne
    private CAI idCai;

    public EjecucionMedidaCAI() {
    }

    public Integer getIdEjecucionMedidaCai() {
        return idEjecucionMedidaCai;
    }

    public void setIdEjecucionMedidaCai(Integer idEjecucionMedidaCai) {
        this.idEjecucionMedidaCai = idEjecucionMedidaCai;
    }

    
    public Date getFechaAprehension() {
        return fechaAprehension;
    }

    public void setFechaAprehension(Date fechaAprehension) {
        this.fechaAprehension = fechaAprehension;
    }

    public Date getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(Date fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    public Date getFechaIngresoCai() {
        return fechaIngresoCai;
    }

    public void setFechaIngresoCai(Date fechaIngresoCai) {
        this.fechaIngresoCai = fechaIngresoCai;
    }

    public String getTipoMedida() {
        return tipoMedida;
    }

    public void setTipoMedida(String tipoMedida) {
        this.tipoMedida = tipoMedida;
    }

    public String getMedidaCautelar() {
        return medidaCautelar;
    }

    public void setMedidaCautelar(String medidaCautelar) {
        this.medidaCautelar = medidaCautelar;
    }

    public Date getAlertaSalidaIntermediaPreventiva() {
        return alertaSalidaIntermediaPreventiva;
    }

    public void setAlertaSalidaIntermediaPreventiva(Date alertaSalidaIntermediaPreventiva) {
        this.alertaSalidaIntermediaPreventiva = alertaSalidaIntermediaPreventiva;
    }

    public Integer getAnios() {
        return anios;
    }

    public void setAnios(Integer anios) {
        this.anios = anios;
    }

    public Integer getMeses() {
        return meses;
    }

    public void setMeses(Integer meses) {
        this.meses = meses;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getTiempoSentenDias() {
        return tiempoSentenDias;
    }

    public void setTiempoSentenDias(Integer tiempoSentenDias) {
        this.tiempoSentenDias = tiempoSentenDias;
    }

    public Integer getTiempoPrivacionLibertad() {
        return tiempoPrivacionLibertad;
    }

    public void setTiempoPrivacionLibertad(Integer tiempoPrivacionLibertad) {
        this.tiempoPrivacionLibertad = tiempoPrivacionLibertad;
    }

    public String getObservacionesProcesoJudicial() {
        return observacionesProcesoJudicial;
    }

    public void setObservacionesProcesoJudicial(String observacionesProcesoJudicial) {
        this.observacionesProcesoJudicial = observacionesProcesoJudicial;
    }

    public CAI getIdCai() {
        return idCai;
    }

    public void setIdCai(CAI idCai) {
        this.idCai = idCai;
    }

    public DetalleInfraccionCAI getIdDetalleInfraccionCAI() {
        return idDetalleInfraccionCAI;
    }

    public void setIdDetalleInfraccionCAI(DetalleInfraccionCAI idDetalleInfraccionCAI) {
        this.idDetalleInfraccionCAI = idDetalleInfraccionCAI;
    }

 }
