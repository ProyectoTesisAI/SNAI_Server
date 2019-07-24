/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.entidades;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "t_usuario")
@XmlRootElement

public class Usuario implements Serializable, Principal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_usuario_pk")
    private Integer idUsuario;
    
    @Size(max = 50)
    @Column(name = "nombres")
    private String nombres;
    
    @Size(max = 50)
    @Column(name = "apellidos")
    private String apellidos;
    
    @Size(max = 10)
    @Column(name = "cedula")
    private String cedula;
    
    @Size(max = 10)
    @Column(name = "telefono")
    private String telefono;
    
    @Size(max = 25)
    @Column(name = "usuario")
    private String usuario;
    
    @Size(max = 25)
    @Column(name = "contrasenia")
    private String contraseña;
    
    @Column(name = "activo")
    private Boolean activo;
    
    @JoinColumn(name = "id_rcu_fk", referencedColumnName = "id_rcu_pk")
    @ManyToOne
    private RolCentroUsuario idRolUsuarioCentro;

    public Usuario() {
    }

    public Usuario(Integer idUsuarioPk) {
        this.idUsuario = idUsuarioPk;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
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

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public RolCentroUsuario getIdRolUsuarioCentro() {
        return idRolUsuarioCentro;
    }

    public void setIdRolUsuarioCentro(RolCentroUsuario idRolUsuarioCentro) {
        this.idRolUsuarioCentro = idRolUsuarioCentro;
    }
    
    @Override
    public String getName() {
        return usuario;
    }

       
}
