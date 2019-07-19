package epn.edu.ec.entidades;

import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "t_deta_infraccion_cai")
@XmlRootElement

public class DetalleInfraccionCAI implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_deta_infrac_cai_pk")
    private Integer idDetalleInfraccion;

    @Size(max = 1000)
    @Column(name = "tipo_penal")
    private String tipoPenal;

    @Size(max = 100)
    @Column(name = "provincia_infraccion")
    private String provinciaInfraccion;

    @Size(max = 100)
    @Column(name = "canton_infraccion")
    private String cantonInfraccion;

    @Size(max = 50)
    @Column(name = "unidad_judicial")
    private String unidadJudicial;

    @Size(max = 200)
    @Column(name = "nombre_unidad_judicial")
    private String nombreUnidadJudicial;

    @Size(max = 20)
    @Column(name = "numero_causa")
    private String numeroCausa;

    @Size(max = 40)
    @Column(name = "nombre_juez")
    private String nombreJuez;

    @JoinColumn(name = "id_adolescente_cai_fk", referencedColumnName = "id_adolescente_cai_pk")
    @ManyToOne
    private AdolescenteInfractorCAI idAdolescenteInfractorCAI;

    public DetalleInfraccionCAI() {
    }

    public Integer getIdDetalleInfraccion() {
        return idDetalleInfraccion;
    }

    public void setIdDetalleInfraccion(Integer idDetalleInfraccion) {
        this.idDetalleInfraccion = idDetalleInfraccion;
    }

    public String getTipoPenal() {
        return tipoPenal;
    }

    public void setTipoPenal(String tipoPenal) {
        this.tipoPenal = tipoPenal;
    }

    public String getProvinciaInfraccion() {
        return provinciaInfraccion;
    }

    public void setProvinciaInfraccion(String provinciaInfraccion) {
        this.provinciaInfraccion = provinciaInfraccion;
    }

    public String getCantonInfraccion() {
        return cantonInfraccion;
    }

    public void setCantonInfraccion(String cantonInfraccion) {
        this.cantonInfraccion = cantonInfraccion;
    }

    public String getUnidadJudicial() {
        return unidadJudicial;
    }

    public void setUnidadJudicial(String unidadJudicial) {
        this.unidadJudicial = unidadJudicial;
    }

    public String getNombreUnidadJudicial() {
        return nombreUnidadJudicial;
    }

    public void setNombreUnidadJudicial(String nombreUnidadJudicial) {
        this.nombreUnidadJudicial = nombreUnidadJudicial;
    }

    public String getNumeroCausa() {
        return numeroCausa;
    }

    public void setNumeroCausa(String numeroCausa) {
        this.numeroCausa = numeroCausa;
    }

    public String getNombreJuez() {
        return nombreJuez;
    }

    public void setNombreJuez(String nombreJuez) {
        this.nombreJuez = nombreJuez;
    }

    public AdolescenteInfractorCAI getIdAdolescenteInfractorCAI() {
        return idAdolescenteInfractorCAI;
    }

    public void setIdAdolescenteInfractorCAI(AdolescenteInfractorCAI idAdolescenteInfractorCAI) {
        this.idAdolescenteInfractorCAI = idAdolescenteInfractorCAI;
    }
}
