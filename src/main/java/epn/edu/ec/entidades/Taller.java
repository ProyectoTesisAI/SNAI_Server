/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.entidades;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author User
 */
@Entity
@Table(name = "t_taller")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Taller.findAll", query = "SELECT t FROM Taller t")})
public class Taller implements Serializable {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_taller_pk")
    private Integer idTaller;
    
    @Size(max = 500)
    @Column(name = "tema")
    private String tema;
    
    @Column(name = "numero_taller")
    private Integer numeroTaller;
    
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    
    @Size(max = 1000)
    @Column(name = "objetivo")
    private String objetivo;
    
    @Column(name = "num_total_participantes")
    private Integer numeroTotalParticipantes;
    
    @Size(max = 1000)
    @Column(name = "recomendaciones")
    private String recomendaciones;
    
    @Size(max = 25)
    @Column(name = "tipo")
    private String tipo;
    
    @JoinColumn(name = "id_cai_fk")
    @ManyToOne
    private CAI idCai;
    
    @JoinColumn(name = "id_udi_fk")
    @ManyToOne
    private UDI idUdi;
    
    @JoinColumn(name = "usuario_fk", referencedColumnName = "id_usuario_pk")
    @ManyToOne
    private Usuario idUsuario;

    public Taller() {
    }

    public Integer getIdTaller() {
        return idTaller;
    }

    public void setIdTaller(Integer idTaller) {
        this.idTaller = idTaller;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Integer getNumeroTaller() {
        return numeroTaller;
    }

    public void setNumeroTaller(Integer numeroTaller) {
        this.numeroTaller = numeroTaller;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public Integer getNumeroTotalParticipantes() {
        return numeroTotalParticipantes;
    }

    public void setNumeroTotalParticipantes(Integer numeroTotalParticipantes) {
        this.numeroTotalParticipantes = numeroTotalParticipantes;
    }

    public String getRecomendaciones() {
        return recomendaciones;
    }

    public void setRecomendaciones(String recomendaciones) {
        this.recomendaciones = recomendaciones;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    
    
}
