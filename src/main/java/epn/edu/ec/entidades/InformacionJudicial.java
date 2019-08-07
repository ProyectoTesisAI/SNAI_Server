package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_info_judicial")
@XmlRootElement

public class InformacionJudicial implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_info_judicial_pk", nullable = false)
    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    
    @Column(name = "reincidencia")
    private Boolean reincidencia;
    
    @Size(max = 40)
    @Column(name = "nombre_fiscal")
    private String nombreFiscal;
    
    @Size(max = 25)
    @Column(name = "unidad_judicial")
    private String unidadJudicial;
    
    @Size(max = 100)
    @Column(name = "nombre_unidad_judicial")
    private String nombreUnidadJudicial;

    @Size(max = 40)
    @Column(name = "nombre_juez")
    private String nombreJuez;
    
    @Size(max = 10)
    @Column(name = "defensor")
    private String defensor;
    
    @Size(max = 40)
    @Column(name = "nombre_defensor")
    private String nombreDefensor;
    
    @Size(max = 10)
    @Column(name = "numero_causa")
    private String numeroCausa;
    
    @Column(name = "fecha_impos_med")
    @Temporal(TemporalType.DATE)
    private Date fechaImposicionMedida;
    
    @Column(name = "fecha_repec_zon_reso")
    @Temporal(TemporalType.DATE)
    private Date fechaRecepcionZonalResolucion;
    
    @Column(name = "fecha_ini_cump_med")
    @Temporal(TemporalType.DATE)
    private Date fechaInicioCumplimientoMedida;
    
    @Size(max = 40)
    @Column(name = "forma_impos_med")
    private String formaImposicionMedida;
    
    @Size(max = 40)
    @Column(name = "tipo_term_anti_med")
    private String tipoTerminacionAnticipadaMedida;
    
    @Column(name = "amonestacion_verbal")
    private Boolean amonestacionVerbal;
    
    @Column(name = "impos_reglas_cond")
    private Boolean imposicionReglasConducta;
    
    @Column(name = "orient_apo_soc_fami")
    private Boolean orientacionApoyoSocioFamiliar;
    
    @Column(name = "servicio_comunidad")
    private Boolean servicioComunidad;
    
    @Column(name = "libertad_asistida")
    private Boolean libertadAsistida;
    
    @Column(name = "numero_medidas")
    private Integer numeroMedidas;
    
    public InformacionJudicial() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public Boolean getReincidencia() {
        return reincidencia;
    }

    public void setReincidencia(Boolean reincidencia) {
        this.reincidencia = reincidencia;
    }

    public String getNombreFiscal() {
        return nombreFiscal;
    }

    public void setNombreFiscal(String nombreFiscal) {
        this.nombreFiscal = nombreFiscal;
    }

    public String getUnidadJudicial() {
        return unidadJudicial;
    }

    public void setUnidadJudicial(String unidadJudicial) {
        this.unidadJudicial = unidadJudicial;
    }

    public String getNombreUnidadJudicial() {
        return nombreUnidadJudicial;
    }

    public void setNombreUnidadJudicial(String nombreUnidadJudicial) {
        this.nombreUnidadJudicial = nombreUnidadJudicial;
    }

    public String getNombreJuez() {
        return nombreJuez;
    }

    public void setNombreJuez(String nombreJuez) {
        this.nombreJuez = nombreJuez;
    }

    public String getDefensor() {
        return defensor;
    }

    public void setDefensor(String defensor) {
        this.defensor = defensor;
    }

    public String getNombreDefensor() {
        return nombreDefensor;
    }

    public void setNombreDefensor(String nombreDefensor) {
        this.nombreDefensor = nombreDefensor;
    }

    public String getNumeroCausa() {
        return numeroCausa;
    }

    public void setNumeroCausa(String numeroCausa) {
        this.numeroCausa = numeroCausa;
    }

    public Date getFechaImposicionMedida() {
        return fechaImposicionMedida;
    }

    public void setFechaImposicionMedida(Date fechaImposicionMedida) {
        this.fechaImposicionMedida = fechaImposicionMedida;
    }

    public Date getFechaRecepcionZonalResolucion() {
        return fechaRecepcionZonalResolucion;
    }

    public void setFechaRecepcionZonalResolucion(Date fechaRecepcionZonalResolucion) {
        this.fechaRecepcionZonalResolucion = fechaRecepcionZonalResolucion;
    }

    public Date getFechaInicioCumplimientoMedida() {
        return fechaInicioCumplimientoMedida;
    }

    public void setFechaInicioCumplimientoMedida(Date fechaInicioCumplimientoMedida) {
        this.fechaInicioCumplimientoMedida = fechaInicioCumplimientoMedida;
    }

    public String getFormaImposicionMedida() {
        return formaImposicionMedida;
    }

    public void setFormaImposicionMedida(String formaImposicionMedida) {
        this.formaImposicionMedida = formaImposicionMedida;
    }

    public String getTipoTerminacionAnticipadaMedida() {
        return tipoTerminacionAnticipadaMedida;
    }

    public void setTipoTerminacionAnticipadaMedida(String tipoTerminacionAnticipadaMedida) {
        this.tipoTerminacionAnticipadaMedida = tipoTerminacionAnticipadaMedida;
    }

    public Boolean getAmonestacionVerbal() {
        return amonestacionVerbal;
    }

    public void setAmonestacionVerbal(Boolean amonestacionVerbal) {
        this.amonestacionVerbal = amonestacionVerbal;
    }

    public Boolean getImposicionReglasConducta() {
        return imposicionReglasConducta;
    }

    public void setImposicionReglasConducta(Boolean imposicionReglasConducta) {
        this.imposicionReglasConducta = imposicionReglasConducta;
    }

    public Boolean getOrientacionApoyoSocioFamiliar() {
        return orientacionApoyoSocioFamiliar;
    }

    public void setOrientacionApoyoSocioFamiliar(Boolean orientacionApoyoSocioFamiliar) {
        this.orientacionApoyoSocioFamiliar = orientacionApoyoSocioFamiliar;
    }
        
    public Boolean getServicioComunidad() {
        return servicioComunidad;
    }

    public void setServicioComunidad(Boolean servicioComunidad) {
        this.servicioComunidad = servicioComunidad;
    }

    public Boolean getLibertadAsistida() {
        return libertadAsistida;
    }

    public void setLibertadAsistida(Boolean libertadAsistida) {
        this.libertadAsistida = libertadAsistida;
    }

    public Integer getNumeroMedidas() {
        return numeroMedidas;
    }

    public void setNumeroMedidas(Integer numeroMedidas) {
        this.numeroMedidas = numeroMedidas;
    }

    @Override
    public String toString() {
        return "InformacionJudicial{" + "idInformacionJudicial=" + idAdolescenteInfractorUDI + ", reincidencia=" + reincidencia + ", nombreFiscal=" + nombreFiscal + ", unidadJudicial=" + unidadJudicial + ", nombreJuez=" + nombreJuez + ", defensor=" + defensor + ", nombreDefensor=" + nombreDefensor + ", numeroCausa=" + numeroCausa + ", fechaImposicionMedida=" + fechaImposicionMedida + ", fechaRecepcionZonalResolucion=" + fechaRecepcionZonalResolucion + ", fechaInicioCumplimientoMedida=" + fechaInicioCumplimientoMedida + ", formaImposicionMedida=" + formaImposicionMedida + ", tipoTerminacionAnticipadaMedida=" + tipoTerminacionAnticipadaMedida + ", amonestacionVerbal=" + amonestacionVerbal + ", imposicionReglasConducta=" + imposicionReglasConducta + ", orientacionApoyoSocioFamiliar=" + orientacionApoyoSocioFamiliar + ", servicioComunidad=" + servicioComunidad + ", libertadAsistida=" + libertadAsistida + ", numeroMedidas=" + numeroMedidas + '}';
    }
    
}
