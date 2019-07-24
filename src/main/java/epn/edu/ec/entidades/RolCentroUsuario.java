/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.entidades;

import epn.edu.ec.entidades.CAI;
import epn.edu.ec.entidades.Rol;
import epn.edu.ec.entidades.UDI;
import epn.edu.ec.entidades.Usuario;
import java.io.Serializable;
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

/**
 *
 * @author User
 */
@Entity
@Table(name = "t_rol_centro_usuario")

public class RolCentroUsuario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_rcu_pk")
    private Integer idRolUsuarioCentro;
    
    @JoinColumn(name = "id_cai_fk", referencedColumnName = "id_cai_pk")
    @ManyToOne
    private CAI idCai;
    
    @JoinColumn(name = "id_udi_fk", referencedColumnName = "id_udi_pk")
    @ManyToOne
    private UDI idUdi;
    
    @JoinColumn(name = "id_rol_fk", referencedColumnName = "id_rol_pk")
    @ManyToOne
    private Rol idRol;
    
    

    public RolCentroUsuario() {
    }

    public Integer getIdRolUsuarioCentro() {
        return idRolUsuarioCentro;
    }

    public void setIdRolUsuarioCentro(Integer idRolUsuarioCentro) {
        this.idRolUsuarioCentro = idRolUsuarioCentro;
    }

    public CAI getIdCai() {
        return idCai;
    }

    public void setIdCai(CAI idCai) {
        this.idCai = idCai;
    }

    public UDI getIdUdi() {
        return idUdi;
    }

    public void setIdUdi(UDI idUdi) {
        this.idUdi = idUdi;
    }

    public Rol getIdRol() {
        return idRol;
    }

    public void setIdRol(Rol idRol) {
        this.idRol = idRol;
    }

    
    
}
