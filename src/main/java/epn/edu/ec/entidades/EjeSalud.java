package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_eje_salud")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjeSalud.findAll", query = "SELECT e FROM EjeSalud e")})
public class EjeSalud implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_eje_salud_pk", nullable = false)
    private AdolescenteInfractor idAdolescenteInfractor;
    
    @Size(max = 15)
    @Column(name = "situacion_salud")
    private String situacionSalud;
    
    @Size(max = 100)
    @Column(name = "diagnostico_enfermed")
    private String diagnosticoEnfermedad;
    
    @Column(name = "recibe_tratamiento")
    private Boolean recibeTratamiento;
    
    @Column(name = "toma_medicacion")
    private Boolean tomaMedicacion;
    
    @Size(max = 10)
    @Column(name = "num_historia_clinica")
    private String numeroHistoriaClinica;
    
    @Column(name = "embarazo")
    private Boolean embarazo;
    
    @Size(max = 10)
    @Column(name = "tiemp_gestacion_mes")
    private String tiempoGestacionMes;
    
    @Column(name = "consume_sustancias")
    private Boolean consumeSustancias;
    
    @Size(max = 20)
    @Column(name = "tipo_sustancia")
    private String tipoSustancia;
    
    @Column(name = "recibe_tratam_drogas")
    private Boolean recibeTratamientoDrogas;
    
    @Size(max = 100)
    @Column(name = "discapacidad")
    private String discapacidad;
    
    @Size(max = 100)
    @Column(name = "tipo_discapacidad")
    private String tipoDiscapacidad;
    
    @Column(name = "porcentaje_discapa")
    private Integer porcentajeDiscapacidad;
    
    @Column(name = "enfermadades_cr")
    private Boolean enfermadadesCatastroficas;

    public EjeSalud() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public String getSituacionSalud() {
        return situacionSalud;
    }

    public void setSituacionSalud(String situacionSalud) {
        this.situacionSalud = situacionSalud;
    }

    public String getDiagnosticoEnfermedad() {
        return diagnosticoEnfermedad;
    }

    public void setDiagnosticoEnfermedad(String diagnosticoEnfermedad) {
        this.diagnosticoEnfermedad = diagnosticoEnfermedad;
    }

    public Boolean getRecibeTratamiento() {
        return recibeTratamiento;
    }

    public void setRecibeTratamiento(Boolean recibeTratamiento) {
        this.recibeTratamiento = recibeTratamiento;
    }

    public Boolean getTomaMedicacion() {
        return tomaMedicacion;
    }

    public void setTomaMedicacion(Boolean tomaMedicacion) {
        this.tomaMedicacion = tomaMedicacion;
    }

    public String getNumeroHistoriaClinica() {
        return numeroHistoriaClinica;
    }

    public void setNumeroHistoriaClinica(String numeroHistoriaClinica) {
        this.numeroHistoriaClinica = numeroHistoriaClinica;
    }

    public Boolean getEmbarazo() {
        return embarazo;
    }

    public void setEmbarazo(Boolean embarazo) {
        this.embarazo = embarazo;
    }

    public String getTiempoGestacionMes() {
        return tiempoGestacionMes;
    }

    public void setTiempoGestacionMes(String tiempoGestacionMes) {
        this.tiempoGestacionMes = tiempoGestacionMes;
    }

    public Boolean getConsumeSustancias() {
        return consumeSustancias;
    }

    public void setConsumeSustancias(Boolean consumeSustancias) {
        this.consumeSustancias = consumeSustancias;
    }

    public String getTipoSustancia() {
        return tipoSustancia;
    }

    public void setTipoSustancia(String tipoSustancia) {
        this.tipoSustancia = tipoSustancia;
    }

    public Boolean getRecibeTratamientoDrogas() {
        return recibeTratamientoDrogas;
    }

    public void setRecibeTratamientoDrogas(Boolean recibeTratamientoDrogas) {
        this.recibeTratamientoDrogas = recibeTratamientoDrogas;
    }

    public String getDiscapacidad() {
        return discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public String getTipoDiscapacidad() {
        return tipoDiscapacidad;
    }

    public void setTipoDiscapacidad(String tipoDiscapacidad) {
        this.tipoDiscapacidad = tipoDiscapacidad;
    }

    public Integer getPorcentajeDiscapacidad() {
        return porcentajeDiscapacidad;
    }

    public void setPorcentajeDiscapacidad(Integer porcentajeDiscapacidad) {
        this.porcentajeDiscapacidad = porcentajeDiscapacidad;
    }

    public Boolean getEnfermadadesCatastroficas() {
        return enfermadadesCatastroficas;
    }

    public void setEnfermadadesCatastroficas(Boolean enfermadadesCatastroficas) {
        this.enfermadadesCatastroficas = enfermadadesCatastroficas;
    }

    
    
}
