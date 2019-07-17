package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_med_socioeducativa")
@XmlRootElement

public class MedidaSocioeducativa implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_med_socioeduca_pk")
    private Integer idMedidaSocioeducativa;
    
    @Size(max = 50)
    @Column(name = "med_socioeducativa")
    private String medidaSocioeducativa;
    
    @Column(name = "tiempo_meses")
    private Integer tiempoMeses;
    
    @Column(name = "tiempo_dia")
    private Integer tiempoDia;
    
    @Column(name = "tiempo_horas")
    private Integer tiempoHoras;
    
    @Column(name = "cumplio_medida")
    private Boolean cumplioMedida;
    
    @Column(name = "fecha_fin_medida")
    @Temporal(TemporalType.DATE)
    private Date fechaFinMedida;
    
    @Size(max = 50)
    @Column(name = "inst_ejecutora_med")
    private String institucionEjecutoraMedida;
    
    @JoinColumn(name = "id_adolescente_udi_fk", referencedColumnName = "id_adolescente_udi_pk")
    @ManyToOne
    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;

    public MedidaSocioeducativa() {
    }

    public Integer getIdMedidaSocioeducativa() {
        return idMedidaSocioeducativa;
    }

    public void setIdMedidaSocioeducativa(Integer idMedidaSocioeducativa) {
        this.idMedidaSocioeducativa = idMedidaSocioeducativa;
    }

    public String getMedidaSocioeducativa() {
        return medidaSocioeducativa;
    }

    public void setMedidaSocioeducativa(String medidaSocioeducativa) {
        this.medidaSocioeducativa = medidaSocioeducativa;
    }

    public Integer getTiempoMeses() {
        return tiempoMeses;
    }

    public void setTiempoMeses(Integer tiempoMeses) {
        this.tiempoMeses = tiempoMeses;
    }

    public Integer getTiempoDia() {
        return tiempoDia;
    }

    public void setTiempoDia(Integer tiempoDia) {
        this.tiempoDia = tiempoDia;
    }

    public Integer getTiempoHoras() {
        return tiempoHoras;
    }

    public void setTiempoHoras(Integer tiempoHoras) {
        this.tiempoHoras = tiempoHoras;
    }

    public Boolean getCumplioMedida() {
        return cumplioMedida;
    }

    public void setCumplioMedida(Boolean cumplioMedida) {
        this.cumplioMedida = cumplioMedida;
    }

    public Date getFechaFinMedida() {
        return fechaFinMedida;
    }

    public void setFechaFinMedida(Date fechaFinMedida) {
        this.fechaFinMedida = fechaFinMedida;
    }

    public String getInstitucionEjecutoraMedida() {
        return institucionEjecutoraMedida;
    }

    public void setInstitucionEjecutoraMedida(String institucionEjecutoraMedida) {
        this.institucionEjecutoraMedida = institucionEjecutoraMedida;
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    @Override
    public String toString() {
        return "MedidaSocioeducativa{" + "idMedidaSocioeducativa=" + idMedidaSocioeducativa + ", medidaSocioeducativa=" + medidaSocioeducativa + ", tiempoMeses=" + tiempoMeses + ", tiempoDia=" + tiempoDia + ", tiempoHoras=" + tiempoHoras + ", cumplioMedida=" + cumplioMedida + ", fechaFinMedida=" + fechaFinMedida + ", institucionEjecutoraMedida=" + institucionEjecutoraMedida + ", idAdolescenteUdi=" + idAdolescenteInfractorUDI + '}';
    } 
    
}
