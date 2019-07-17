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
@Table(name = "t_representante")
@XmlRootElement

public class Representante implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_representante_pk", nullable = false)
    private AdolescenteInfractor idAdolescenteInfracto;
    
    @Size(max = 100)
    @Column(name = "nacionalidad")
    private String nacionalidad;
    
    @Size(max = 10)
    @Column(name = "cedula")
    private String cedula;
    
    @Size(max = 25)
    @Column(name = "documento")
    private String documento;
    
    @Size(max = 40)
    @Column(name = "nombres")
    private String nombres;
    
    @Size(max = 40)
    @Column(name = "apellidos")
    private String apellidos;
    
    @Size(max = 15)
    @Column(name = "parentesco")
    private String parentesco;
    
    @Size(max = 10)
    @Column(name = "numero_contacto")
    private String numeroContacto;

    public Representante() {
    }

    public AdolescenteInfractor getIdAdolescenteInfracto() {
        return idAdolescenteInfracto;
    }

    public void setIdAdolescenteInfracto(AdolescenteInfractor idAdolescenteInfracto) {
        this.idAdolescenteInfracto = idAdolescenteInfracto;
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

    public String getParentesco() {
        return parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public String getNumeroContacto() {
        return numeroContacto;
    }

    public void setNumeroContacto(String numeroContacto) {
        this.numeroContacto = numeroContacto;
    }

    

    
}
