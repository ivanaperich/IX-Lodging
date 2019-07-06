/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Nicole
 */
@Entity
@Table(name = "cama")
@NamedQueries({
    @NamedQuery(name = "Cama.findAll", query = "SELECT c FROM Cama c")})
public class Cama implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idCama")
    private Integer idCama;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Column(name = "ocupada")
    private byte[] ocupada;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "idPieza", referencedColumnName = "idPieza")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pieza idPieza;

    public Cama() {
    }

    public Cama(Integer idCama) {
        this.idCama = idCama;
    }

    public Cama(Integer idCama, byte[] ocupada, String tipo) {
        this.idCama = idCama;
        this.ocupada = ocupada;
        this.tipo = tipo;
    }

    public Integer getIdCama() {
        return idCama;
    }

    public void setIdCama(Integer idCama) {
        this.idCama = idCama;
    }

    public byte[] getOcupada() {
        return ocupada;
    }

    public void setOcupada(byte[] ocupada) {
        this.ocupada = ocupada;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pieza getIdPieza() {
        return idPieza;
    }

    public void setIdPieza(Pieza idPieza) {
        this.idPieza = idPieza;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCama != null ? idCama.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cama)) {
            return false;
        }
        Cama other = (Cama) object;
        if ((this.idCama == null && other.idCama != null) || (this.idCama != null && !this.idCama.equals(other.idCama))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufro.IXLodging.modelo.Cama[ idCama=" + idCama + " ]";
    }
    
}
