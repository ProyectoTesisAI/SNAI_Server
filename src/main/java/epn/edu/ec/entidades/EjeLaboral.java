package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_eje_laboral")
@XmlRootElement

public class EjeLaboral implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_eje_laboral_pk", nullable = false)
    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    
    @Column(name = "trabaja")
    private Boolean trabaja;
    
    @Size(max = 30)
    @Column(name = "ocupacion_adolescent")
    private String ocupacionAdolescente;
    
    @Size(max = 20)
    @Column(name = "rama_activ_econ")
    private String ramaActividadEconomica;
    
    @Size(max = 100)
    @Column(name = "descripcion_activ")
    private String descripcionActividad;
    
    @Column(name = "num_horas_trabajo")
    private Integer numeroHorasTrabajo;
    
    @Size(max = 20)
    @Column(name = "ingreso_mensual")
    private String ingresoMensual;
    
    @Column(name = "afiliacion_iess")
    private Boolean afiliacionIess;
    
    @Size(max = 80)
    @Column(name = "inclinacion_vocacion")
    private String inclinacionVocacion;

    public EjeLaboral() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    public Boolean getTrabaja() {
        return trabaja;
    }

    public void setTrabaja(Boolean trabaja) {
        this.trabaja = trabaja;
    }

    public String getOcupacionAdolescente() {
        return ocupacionAdolescente;
    }

    public void setOcupacionAdolescente(String ocupacionAdolescente) {
        this.ocupacionAdolescente = ocupacionAdolescente;
    }

    public String getRamaActividadEconomica() {
        return ramaActividadEconomica;
    }

    public void setRamaActividadEconomica(String ramaActividadEconomica) {
        this.ramaActividadEconomica = ramaActividadEconomica;
    }

    public String getDescripcionActividad() {
        return descripcionActividad;
    }

    public void setDescripcionActividad(String descripcionActividad) {
        this.descripcionActividad = descripcionActividad;
    }

    public Integer getNumeroHorasTrabajo() {
        return numeroHorasTrabajo;
    }

    public void setNumeroHorasTrabajo(Integer numeroHorasTrabajo) {
        this.numeroHorasTrabajo = numeroHorasTrabajo;
    }

    public String getIngresoMensual() {
        return ingresoMensual;
    }

    public void setIngresoMensual(String ingresoMensual) {
        this.ingresoMensual = ingresoMensual;
    }

    public Boolean getAfiliacionIess() {
        return afiliacionIess;
    }

    public void setAfiliacionIess(Boolean afiliacionIess) {
        this.afiliacionIess = afiliacionIess;
    }

    public String getInclinacionVocacion() {
        return inclinacionVocacion;
    }

    public void setInclinacionVocacion(String inclinacionVocacion) {
        this.inclinacionVocacion = inclinacionVocacion;
    }

    @Override
    public String toString() {
        return "EjeLaboral{" + "idEjeLaboral=" + idAdolescenteInfractorUDI + ", trabaja=" + trabaja + ", ocupacionAdolescente=" + ocupacionAdolescente + ", ramaActividadEconomica=" + ramaActividadEconomica + ", descripcionActividad=" + descripcionActividad + ", numeroHorasTrabajo=" + numeroHorasTrabajo + ", ingresoMensual=" + ingresoMensual + ", afiliacionIess=" + afiliacionIess + ", inclinacionVocacion=" + inclinacionVocacion + '}';
    }
    
}
