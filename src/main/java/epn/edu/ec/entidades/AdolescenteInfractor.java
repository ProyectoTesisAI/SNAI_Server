/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "t_adolescente")
@XmlRootElement
public class AdolescenteInfractor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_adolescente_pk")
    private Integer idAdolescenteInfractor;
    
    @Size(max = 20)
    @Column(name = "nombres")
    private String nombres;
    
    @Size(max = 30)
    @Column(name = "apellidos")
    private String apellidos;
    
    @Size(max = 100)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    
    @Size(max = 10)
    @Column(name = "cedula")
    private String cedula;
    
    @Size(max = 25)
    @Column(name = "documento")
    private String documento;
    
    @Size(max = 15)
    @Column(name = "genero")
    private String genero;
    
    @Size(max = 20)
    @Column(name = "etnia")
    private String etnia;
    
    @Column(name = "registro_social")
    private Boolean registroSocial;
    
    @Size(max = 20)
    @Column(name = "estado_civil")
    private String estadoCivil;
    
    @Column(name = "fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    private Date fechaNacimiento;
    
    @Column(name = "numero_hijos")
    private Integer numeroHijos;
    
    @Size(max = 4)
    @Column(name = "tipo")
    private String tipo;
    
    
    public AdolescenteInfractor() {
    }

    public Integer getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(Integer idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }


    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEtnia() {
        return etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public Boolean getRegistroSocial() {
        return registroSocial;
    }

    public void setRegistroSocial(Boolean registroSocial) {
        this.registroSocial = registroSocial;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getNumeroHijos() {
        return numeroHijos;
    }

    public void setNumeroHijos(Integer numeroHijos) {
        this.numeroHijos = numeroHijos;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
}
