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
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_actividades_instru")
@XmlRootElement
public class ActividadesInstrumentos implements Serializable {

    @Id
    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_actividad_inst_pk", nullable = false)
    private AdolescenteInfractorUDI idAdolescenteInfractorUDI;
    
    @Column(name = "plan_ejecucion_med")
    private Boolean planEjecucionMedida;
    
    @Column(name = "plan_global_familia")
    private Boolean planGlobalFamilia;
    
    @Column(name = "plan_vida")
    private Boolean planVida;
    
    @Column(name = "plan_ind_aplic_med")
    private Boolean planIndividualAplicacionMedida;
    

    public ActividadesInstrumentos() {
    }

    public AdolescenteInfractorUDI getIdAdolescenteInfractorUDI() {
        return idAdolescenteInfractorUDI;
    }

    public void setIdAdolescenteInfractorUDI(AdolescenteInfractorUDI idAdolescenteInfractorUDI) {
        this.idAdolescenteInfractorUDI = idAdolescenteInfractorUDI;
    }

    

    public Boolean getPlanGlobalFamilia() {
        return planGlobalFamilia;
    }

    public void setPlanGlobalFamilia(Boolean planGlobalFamilia) {
        this.planGlobalFamilia = planGlobalFamilia;
    }

    public Boolean getPlanVida() {
        return planVida;
    }

    public void setPlanVida(Boolean planVida) {
        this.planVida = planVida;
    }

    public Boolean getPlanEjecucionMedida() {
        return planEjecucionMedida;
    }

    public void setPlanEjecucionMedida(Boolean planEjecucionMedida) {
        this.planEjecucionMedida = planEjecucionMedida;
    }

    public Boolean getPlanIndividualAplicacionMedida() {
        return planIndividualAplicacionMedida;
    }

    public void setPlanIndividualAplicacionMedida(Boolean planIndividualAplicacionMedida) {
        this.planIndividualAplicacionMedida = planIndividualAplicacionMedida;
    }
    
}
