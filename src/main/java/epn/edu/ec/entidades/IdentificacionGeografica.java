package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_id_geografica")
@XmlRootElement

public class IdentificacionGeografica implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_geografica_pk", nullable = false)
    private AdolescenteInfractor idAdolescenteInfractor;
    
    @Size(max = 20)
    @Column(name = "pais_nacimiento")
    private String paisNacimiento;
    
    @Size(max = 50)
    @Column(name = "est_o_prov_nacimiento")
    private String estadoOProvinciaNacimiento;
    
    @Size(max = 50)
    @Column(name = "ciudad_o_canton_naci")
    private String ciudadOCantonNacimiento;
    
    @Size(max = 50)
    @Column(name = "provincia_residencia")
    private String provinciaResidencia;
    
    @Size(max = 50)
    @Column(name = "canton_residencia")
    private String cantonResidencia;
    
    @Size(max = 50)
    @Column(name = "parroquia_residencia")
    private String parroquiaResidencia;
    
    @Column(name = "direccion_domicilio")
    private String direccionDomicilio;
    
    @Size(max = 125)
    @Column(name = "referencia_domicilio")
    private String referenciaDomicilio;
    
    @Size(max = 10)
    @Column(name = "telefono")
    private String telefono;
    
    public IdentificacionGeografica() {
    }

    public AdolescenteInfractor getIdAdolescenteInfractor() {
        return idAdolescenteInfractor;
    }

    public void setIdAdolescenteInfractor(AdolescenteInfractor idAdolescenteInfractor) {
        this.idAdolescenteInfractor = idAdolescenteInfractor;
    }

    public String getPaisNacimiento() {
        return paisNacimiento;
    }

    public void setPaisNacimiento(String paisNacimiento) {
        this.paisNacimiento = paisNacimiento;
    }

    public String getEstadoOProvinciaNacimiento() {
        return estadoOProvinciaNacimiento;
    }

    public void setEstadoOProvinciaNacimiento(String estadoOProvinciaNacimiento) {
        this.estadoOProvinciaNacimiento = estadoOProvinciaNacimiento;
    }

    public String getCiudadOCantonNacimiento() {
        return ciudadOCantonNacimiento;
    }

    public void setCiudadOCantonNacimiento(String ciudadOCantonNacimiento) {
        this.ciudadOCantonNacimiento = ciudadOCantonNacimiento;
    }

    public String getProvinciaResidencia() {
        return provinciaResidencia;
    }

    public void setProvinciaResidencia(String provinciaResidencia) {
        this.provinciaResidencia = provinciaResidencia;
    }

    public String getCantonResidencia() {
        return cantonResidencia;
    }

    public void setCantonResidencia(String cantonResidencia) {
        this.cantonResidencia = cantonResidencia;
    }

    public String getParroquiaResidencia() {
        return parroquiaResidencia;
    }

    public void setParroquiaResidencia(String parroquiaResidencia) {
        this.parroquiaResidencia = parroquiaResidencia;
    }

    public String getDireccionDomicilio() {
        return direccionDomicilio;
    }

    public void setDireccionDomicilio(String direccionDomicilio) {
        this.direccionDomicilio = direccionDomicilio;
    }

    public String getReferenciaDomicilio() {
        return referenciaDomicilio;
    }

    public void setReferenciaDomicilio(String referenciaDomicilio) {
        this.referenciaDomicilio = referenciaDomicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
