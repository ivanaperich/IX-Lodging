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
@Table(name = "hospedaje")
@NamedQueries({
    @NamedQuery(name = "Hospedaje.findAll", query = "SELECT h FROM Hospedaje h")})
public class Hospedaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idHospedaje")
    private Integer idHospedaje;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "dueño")
    private String dueño;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "descripcion")
    private String descripcion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 00)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "ciudad")
    private String ciudad;
    @Basic(optional = false)
    @Column(name = "calificacion")
    private float calificacion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHospedaje", fetch = FetchType.LAZY)
    private List<Feedback> feedbackList;
    @JoinColumn(name = "id_Usuario", referencedColumnName = "idUsuario")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Usuario idUsuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idHospedaje", fetch = FetchType.LAZY)
    private List<Pieza> piezaList;

    public Hospedaje() {
    }

    public Hospedaje(Integer idHospedaje) {
        this.idHospedaje = idHospedaje;
    }

    public Hospedaje(Integer idHospedaje, String dueño, String nombre, String descripcion, String direccion, String ciudad, float calificacion) {
        this.idHospedaje = idHospedaje;
        this.dueño = dueño;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.calificacion = calificacion;
    }

    public Integer getIdHospedaje() {
        return idHospedaje;
    }

    public void setIdHospedaje(Integer idHospedaje) {
        this.idHospedaje = idHospedaje;
    }

    public String getDueño() {
        return dueño;
    }

    public void setDueño(String dueño) {
        this.dueño = dueño;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public float getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(float calificacion) {
        this.calificacion = calificacion;
    }

    public List<Feedback> getFeedbackList() {
        return feedbackList;
    }

    public void setFeedbackList(List<Feedback> feedbackList) {
        this.feedbackList = feedbackList;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario idUsuario) {
        this.idUsuario = idUsuario;
    }

    public List<Pieza> getPiezaList() {
        return piezaList;
    }

    public void setPiezaList(List<Pieza> piezaList) {
        this.piezaList = piezaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idHospedaje != null ? idHospedaje.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Hospedaje)) {
            return false;
        }
        Hospedaje other = (Hospedaje) object;
        if ((this.idHospedaje == null && other.idHospedaje != null) || (this.idHospedaje != null && !this.idHospedaje.equals(other.idHospedaje))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ufro.IXLodging.modelo.Hospedaje[ idHospedaje=" + idHospedaje + " ]";
    }
    
}
