package epn.edu.ec.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "t_datos_provincia_canton")
@XmlRootElement

public class DatosProvinciaCanton implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_datos_provincia_canton_pk")
    private Integer idDatosProvinciaCanton;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "provincia")
    private String provincia;
    
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "canton")
    private String canton;

    public DatosProvinciaCanton() {
    }

    public DatosProvinciaCanton(Integer idDatosProvinciaCanton) {
        this.idDatosProvinciaCanton = idDatosProvinciaCanton;
    }

    public DatosProvinciaCanton(Integer idDatosProvinciaCanton, String provincia, String canton) {
        this.idDatosProvinciaCanton = idDatosProvinciaCanton;
        this.provincia = provincia;
        this.canton = canton;
    }

    public Integer getIdDatosProvinciaCanton() {
        return idDatosProvinciaCanton;
    }

    public void setIdDatosProvinciaCanton(Integer idDatosProvinciaCanton) {
        this.idDatosProvinciaCanton = idDatosProvinciaCanton;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    @Override
    public String toString() {
        return "DatosProvinciaCanton{" + "idDatosProvinciaCanton=" + idDatosProvinciaCanton + ", provincia=" + provincia + ", canton=" + canton + '}';
    }
    
}
