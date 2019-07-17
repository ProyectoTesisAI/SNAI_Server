/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "t_eje_educativo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EjeEducativo.findAll", query = "SELECT e FROM EjeEducativo e")})
public class EjeEducativo implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_eje_educativo_pk", nullable = false)
    private AdolescenteInfractor idAdolescenteInfractor;
    
    @Size(max = 10)
    @Column(name = "codigo_unico_electri")
    private String codigoUnicoElectrico;
    
    @Size(max = 7)
    @Column(name = "coor_zon_edu_dom_est")
    private String coordinacionZonalEducacionDomicilioEstudiante;
    
    @Size(max = 20)
    @Column(name = "dist_edu_dom_est")
    private String distritoEducativoDomicilioEstudiante;
    
    @Column(name = "estudia")
    private Boolean estudia;
    
    @Size(max = 100)
    @Column(name = "razon_no_estudia")
    private String razonNoEstudia;
    
    @Size(max = 40)
    @Column(name = "ult_niv_edu_aprobado")
    private String ultimoNivelEducativoAprobado;
    
    @Size(max = 50)
    @Column(name = "nombre_unid_edu_ulti")
    private String nombreUnidadEducativaUltimaAprobado;
    
    @Size(max = 20)
    @Column(name = "sostenimiento_ult_niv")
    private String sostenimientoUltimoNivelAprobado;
    
    @Size(max = 20)
    @Column(name = "anios_rezago_escolar")
    private String aniosRezagoEscolar;
    
    @Size(max = 40)
    @Column(name = "nivel_edu_actual")
    private String nivelEducativoActual;
    
    @Size(max = 15)
    @Column(name = "tipo_oferta")
    private String tipoOferta;
    
    @Size(max = 50)
    @Column(name = "nombre_uni_educa")
    private String nombreUnidadEducativa;
    
    @Size(max = 20)
    @Column(name = "sostenimiento")
    private String sostenimiento;
    
    @Size(max = 10)
    @Column(name = "regimen_educativo")
    private String regimenEducativo;

    public EjeEducativo() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public String getCodigoUnicoElectrico() {
        return codigoUnicoElectrico;
    }

    public void setCodigoUnicoElectrico(String codigoUnicoElectrico) {
        this.codigoUnicoElectrico = codigoUnicoElectrico;
    }

    public String getCoordinacionZonalEducacionDomicilioEstudiante() {
        return coordinacionZonalEducacionDomicilioEstudiante;
    }

    public void setCoordinacionZonalEducacionDomicilioEstudiante(String coordinacionZonalEducacionDomicilioEstudiante) {
        this.coordinacionZonalEducacionDomicilioEstudiante = coordinacionZonalEducacionDomicilioEstudiante;
    }

    public String getDistritoEducativoDomicilioEstudiante() {
        return distritoEducativoDomicilioEstudiante;
    }

    public void setDistritoEducativoDomicilioEstudiante(String distritoEducativoDomicilioEstudiante) {
        this.distritoEducativoDomicilioEstudiante = distritoEducativoDomicilioEstudiante;
    }

    public Boolean getEstudia() {
        return estudia;
    }

    public void setEstudia(Boolean estudia) {
        this.estudia = estudia;
    }

    public String getRazonNoEstudia() {
        return razonNoEstudia;
    }

    public void setRazonNoEstudia(String razonNoEstudia) {
        this.razonNoEstudia = razonNoEstudia;
    }

    public String getUltimoNivelEducativoAprobado() {
        return ultimoNivelEducativoAprobado;
    }

    public void setUltimoNivelEducativoAprobado(String ultimoNivelEducativoAprobado) {
        this.ultimoNivelEducativoAprobado = ultimoNivelEducativoAprobado;
    }

    public String getNombreUnidadEducativaUltimaAprobado() {
        return nombreUnidadEducativaUltimaAprobado;
    }

    public void setNombreUnidadEducativaUltimaAprobado(String nombreUnidadEducativaUltimaAprobado) {
        this.nombreUnidadEducativaUltimaAprobado = nombreUnidadEducativaUltimaAprobado;
    }

    public String getSostenimientoUltimoNivelAprobado() {
        return sostenimientoUltimoNivelAprobado;
    }

    public void setSostenimientoUltimoNivelAprobado(String sostenimientoUltimoNivelAprobado) {
        this.sostenimientoUltimoNivelAprobado = sostenimientoUltimoNivelAprobado;
    }

    public String getAniosRezagoEscolar() {
        return aniosRezagoEscolar;
    }

    public void setAniosRezagoEscolar(String aniosRezagoEscolar) {
        this.aniosRezagoEscolar = aniosRezagoEscolar;
    }

    public String getNivelEducativoActual() {
        return nivelEducativoActual;
    }

    public void setNivelEducativoActual(String nivelEducativoActual) {
        this.nivelEducativoActual = nivelEducativoActual;
    }

    public String getTipoOferta() {
        return tipoOferta;
    }

    public void setTipoOferta(String tipoOferta) {
        this.tipoOferta = tipoOferta;
    }

    public String getNombreUnidadEducativa() {
        return nombreUnidadEducativa;
    }

    public void setNombreUnidadEducativa(String nombreUnidadEducativa) {
        this.nombreUnidadEducativa = nombreUnidadEducativa;
    }

    public String getSostenimiento() {
        return sostenimiento;
    }

    public void setSostenimiento(String sostenimiento) {
        this.sostenimiento = sostenimiento;
    }

    public String getRegimenEducativo() {
        return regimenEducativo;
    }

    public void setRegimenEducativo(String regimenEducativo) {
        this.regimenEducativo = regimenEducativo;
    }

    
    
}
