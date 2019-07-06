/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nicole
 */
@Entity
@Table(name = "pieza")
@NamedQueries({
    @NamedQuery(name = "Pieza.findAll", query = "SELECT p FROM Pieza p")})
public class Pieza implements Serializable {

    @Lob
    @Column(name = "compartida")
    private byte[] compartida;
    @Column(name = "nro_ba\u00f1os")
    private Integer nroBaños;
    @Size(max = 100)
    @Column(name = "nombre_pieza")
    private String nombrePieza;

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPieza")
    private Integer idPieza;
    @JoinColumn(name = "id_Hospedaje", referencedColumnName = "idHospedaje")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Hospedaje idHospedaje;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPieza", fetch = FetchType.LAZY)
    private List<Cama> camaList;

    public Pieza() {
    }

    public Pieza(Integer idPieza) {
        this.idPieza = idPieza;
    }

    public Pieza(Integer idPieza, byte[] compartida, int nroBaños) {
        this.idPieza = idPieza;
        this.compartida = compartida;
        this.nroBaños = nroBaños;
    }

    public Integer getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Integer idPieza) {
        this.idPieza = idPieza;
    }


    public int getNroBaños() {
        return nroBaños;
    }

    public void setNroBaños(int nroBaños) {
        this.nroBaños = nroBaños;
    }

    public String getNombrePieza() {
        return nombrePieza;
    }

    public void setNombrePieza(String nombrePieza) {
        this.nombrePieza = nombrePieza;
    }

    public Hospedaje getIdHospedaje() {
        return idHospedaje;
    }

    public void setIdHospedaje(Hospedaje idHospedaje) {
        this.idHospedaje = idHospedaje;
    }

    public List<Cama> getCamaList() {
        return camaList;
    }

    public void setCamaList(List<Cama> camaList) {
        this.camaList = camaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPieza != null ? idPieza.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pieza)) {
            return false;
        }
        Pieza other = (Pieza) object;
        if ((this.idPieza == null && other.idPieza != null) || (this.idPieza != null && !this.idPieza.equals(other.idPieza))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufro.IXLodging.modelo.Pieza[ idPieza=" + idPieza + " ]";
    }

    public byte[] getCompartida() {
        return compartida;
    }

    public void setCompartida(byte[] compartida) {
        this.compartida = compartida;
    }

    public void setNroBaños(Integer nroBaños) {
        this.nroBaños = nroBaños;
    }
}