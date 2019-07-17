/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "t_informe")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Informe.findAll", query = "SELECT i FROM Informe i")})
public class Informe implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_informe_pk")
    private Integer idInforme;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    
    @Column(name = "num_adolescentes")
    private Integer numeroAdolescentes;
    
    @Size(max = 1000)
    @Column(name = "adolescentes_justificacion")
    private String adolescentesJustificacion;
    
    @Size(max = 1000)
    @Column(name = "objetivo_general")
    private String objetivoGeneral;
    
    @Size(max = 1000)
    @Column(name = "socializacion_desarrollo")
    private String socializacionDesarrollo;
    
    @Size(max = 1000)
    @Column(name = "socializacion_objetivos")
    private String socializacionObjetivos;
    
    @Size(max = 1000)
    @Column(name = "cierre_evaluacion")
    private String cierreEvaluacion;
    
    @Size(max = 1000)
    @Column(name = "conclusiones")
    private String conclusiones;
    
    @Size(max = 1000)
    @Column(name = "recomendaciones")
    private String recomendaciones;
    
    @Size(max = 1000)
    @Column(name = "observaciones")
    private String observaciones;
    
    @Size(max = 50)
    @Column(name = "lugar_seccion")
    private String lugarSeccion;
    
    @JoinColumn(name = "id_taller_fk")
    @ManyToOne
    private Taller idTaller;

    public Integer getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(Integer idInforme) {
        this.idInforme = idInforme;
    }
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getNumeroAdolescentes() {
        return numeroAdolescentes;
    }

    public void setNumeroAdolescentes(Integer numeroAdolescentes) {
        this.numeroAdolescentes = numeroAdolescentes;
    }

    public String getAdolescentesJustificacion() {
        return adolescentesJustificacion;
    }

    public void setAdolescentesJustificacion(String adolescentesJustificacion) {
        this.adolescentesJustificacion = adolescentesJustificacion;
    }

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
    }

    public String getSocializacionDesarrollo() {
        return socializacionDesarrollo;
    }

    public void setSocializacionDesarrollo(String socializacionDesarrollo) {
        this.socializacionDesarrollo = socializacionDesarrollo;
    }

    public String getSocializacionObjetivos() {
        return socializacionObjetivos;
    }

    public void setSocializacionObjetivos(String socializacionObjetivos) {
        this.socializacionObjetivos = socializacionObjetivos;
    }

    public String getCierreEvaluacion() {
        return cierreEvaluacion;
    }

    public void setCierreEvaluacion(String cierreEvaluacion) {
        this.cierreEvaluacion = cierreEvaluacion;
    }

    public String getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(String conclusiones) {
        this.conclusiones = conclusiones;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getLugarSeccion() {
        return lugarSeccion;
    }

    public void setLugarSeccion(String lugarSeccion) {
        this.lugarSeccion = lugarSeccion;
    }

    public Taller getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Taller idTaller) {
        this.idTaller = idTaller;
    }

    
}
