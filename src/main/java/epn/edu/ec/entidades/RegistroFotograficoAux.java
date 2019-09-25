/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package epn.edu.ec.entidades;

import java.io.Serializable;

/**
 *
 * @author User
 */
public class RegistroFotograficoAux implements Serializable {
    private Integer idRegistroFotografico;
    String imagenAux;
    private Informe idInforme;
    

    public Integer getIdRegistroFotografico() {
        return idRegistroFotografico;
    }

    public void setIdRegistroFotografico(Integer idRegistroFotografico) {
        this.idRegistroFotografico = idRegistroFotografico;
    }

    public Informe getIdInforme() {
        return idInforme;
    }

    public void setIdInforme(Informe idInforme) {
        this.idInforme = idInforme;
    }

    public String getImagenAux() {
        return imagenAux;
    }

    public void setImagenAux(String imagenAux) {
        this.imagenAux = imagenAux;
    }
}
