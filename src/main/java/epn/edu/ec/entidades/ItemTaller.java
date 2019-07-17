/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "t_item_taller")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItemTaller.findAll", query = "SELECT i FROM ItemTaller i")})
public class ItemTaller implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_item_taller_pk")
    private Integer idItemTaller;
    
    @Column(name = "duracion")
    private Integer duracion;
    
    @Size(max = 1000)
    @Column(name = "actividad")
    private String actividad;
    
    @Size(max = 1000)
    @Column(name = "objetivo_especifico")
    private String objetivoEspecifico;
    
    @Size(max = 1000)
    @Column(name = "materiales")
    private String materiales;
    
    @Size(max = 200)
    @Column(name = "responsable")
    private String responsable;
    
    @JoinColumn(name = "id_taller_fk", referencedColumnName = "id_taller_pk")
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Taller idTaller;

    public Integer getIdItemTaller() {
        return idItemTaller;
    }

    public void setIdItemTaller(Integer idItemTaller) {
        this.idItemTaller = idItemTaller;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getObjetivoEspecifico() {
        return objetivoEspecifico;
    }

    public void setObjetivoEspecifico(String objetivoEspecifico) {
        this.objetivoEspecifico = objetivoEspecifico;
    }

    public String getMateriales() {
        return materiales;
    }

    public void setMateriales(String materiales) {
        this.materiales = materiales;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Taller getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Taller idTaller) {
        this.idTaller = idTaller;
    }

    
    
}
