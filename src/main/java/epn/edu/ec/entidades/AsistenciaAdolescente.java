/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "t_asistencia_adolescente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AsistenciaAdolescente.findAll", query = "SELECT a FROM AsistenciaAdolescente a")})
public class AsistenciaAdolescente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_asistencia_adolescente_pk")
    private Integer idAsistenciaAdolescente;
    
    @JoinColumn(name = "id_adolescente_fk", referencedColumnName = "id_adolescente_pk")
    @ManyToOne
    private AdolescenteInfractor idAdolescenteInfractor;
    
    @JoinColumn(name = "id_reg_asistencia_fk", referencedColumnName = "id_reg_asistencia_pk")
    @ManyToOne
    private RegistroAsistencia idRegistroAsistencia;

    @Column(name = "asistio")
    private Boolean asistio;
    
    public AsistenciaAdolescente() {
    }

    public AsistenciaAdolescente(Integer idAsistenciaAdolescentePk) {
        this.idAsistenciaAdolescente = idAsistenciaAdolescentePk;
    }

    public Integer getIdAsistenciaAdolescente() {
        return idAsistenciaAdolescente;
    }

    public void setIdAsistenciaAdolescente(Integer idAsistenciaAdolescente) {
        this.idAsistenciaAdolescente = idAsistenciaAdolescente;
    }

    public Boolean getAsistio() {
        return asistio;
    }

    public void setAsistio(Boolean asistio) {
        this.asistio = asistio;
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public RegistroAsistencia getIdRegistroAsistencia() {
        return idRegistroAsistencia;
    }

    public void setIdRegistroAsistencia(RegistroAsistencia idRegistroAsistencia) {
        this.idRegistroAsistencia = idRegistroAsistencia;
    }

    @Override
    public String toString() {
        return "epn.edu.ec.entidades.AsistenciaAdolescente[ idAsistenciaAdolescentePk=" + idAsistenciaAdolescente + " ]";
    }
    
}
