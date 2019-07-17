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
@Table(name = "t_estado_cump_medida")
@XmlRootElement

public class EstadoCumplimientoMedida implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_estad_cump_med_pk", nullable = false)
    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    
    @Size(max = 20)
    @Column(name = "situacion_actual")
    private String situacionActual;
    
    @Column(name = "fecha_report_culmin")
    @Temporal(TemporalType.DATE)
    private Date fechaReporteCulminacion;
    
    @Column(name = "fecha_report_derivac")
    @Temporal(TemporalType.DATE)
    private Date fechaReporteDerivacion;   
    
    @Column(name = "fecha_report_incump")
    @Temporal(TemporalType.DATE)
    private Date fechaReporteIncumplimiento;
    
    @Size(max = 20)
    @Column(name = "estado_incump")
    private String estadoIncumplimiento;
    
    @Column(name = "reanudacion_med")
    private Boolean reanudacionMedida;
    
    @Column(name = "fecha_reanudacion")
    @Temporal(TemporalType.DATE)
    private Date fechaReanudacion;
    
    @JoinColumn(name = "cai_recept_deriva_fk", referencedColumnName = "id_cai_pk")
    @ManyToOne
    private CAI caiReceptoraDerivacion;
    
    @JoinColumn(name = "uzdi_recept_deriva_fk", referencedColumnName = "id_udi_pk")
    @ManyToOne
    private UDI uzdiReceptoraDerivacion;
    
    public EstadoCumplimientoMedida() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public String getSituacionActual() {
        return situacionActual;
    }

    public void setSituacionActual(String situacionActual) {
        this.situacionActual = situacionActual;
    }

    public Date getFechaReporteCulminacion() {
        return fechaReporteCulminacion;
    }

    public void setFechaReporteCulminacion(Date fechaReporteCulminacion) {
        this.fechaReporteCulminacion = fechaReporteCulminacion;
    }

    public Date getFechaReporteDerivacion() {
        return fechaReporteDerivacion;
    }

    public void setFechaReporteDerivacion(Date fechaReporteDerivacion) {
        this.fechaReporteDerivacion = fechaReporteDerivacion;
    }

    public Date getFechaReporteIncumplimiento() {
        return fechaReporteIncumplimiento;
    }

    public void setFechaReporteIncumplimiento(Date fechaReporteIncumplimiento) {
        this.fechaReporteIncumplimiento = fechaReporteIncumplimiento;
    }

    public String getEstadoIncumplimiento() {
        return estadoIncumplimiento;
    }

    public void setEstadoIncumplimiento(String estadoIncumplimiento) {
        this.estadoIncumplimiento = estadoIncumplimiento;
    }

    public Boolean getReanudacionMedida() {
        return reanudacionMedida;
    }

    public void setReanudacionMedida(Boolean reanudacionMedida) {
        this.reanudacionMedida = reanudacionMedida;
    }

    public Date getFechaReanudacion() {
        return fechaReanudacion;
    }

    public void setFechaReanudacion(Date fechaReanudacion) {
        this.fechaReanudacion = fechaReanudacion;
    }

    public CAI getCaiReceptoraDerivacion() {
        return caiReceptoraDerivacion;
    }

    public void setCaiReceptoraDerivacion(CAI caiReceptoraDerivacion) {
        this.caiReceptoraDerivacion = caiReceptoraDerivacion;
    }

    public UDI getUzdiReceptoraDerivacion() {
        return uzdiReceptoraDerivacion;
    }

    public void setUzdiReceptoraDerivacion(UDI uzdiReceptoraDerivacion) {
        this.uzdiReceptoraDerivacion = uzdiReceptoraDerivacion;
    }

    
}
