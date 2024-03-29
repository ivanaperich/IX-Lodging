/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.DAO;

import com.ufro.IXLodging.modelo.Pieza;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Nicole
 */
public interface PiezaDAO extends CrudRepository<Pieza, Integer> {

    public List<Pieza> findByIdUsuario_Id(int id);

    public List<Pieza> findByIdHospedaje_Id(int id);

    public List<Pieza> findByIdUsuario_IdAndIdHospedaje_Id(int idUsuario, int idHospedaje);

    public Pieza findById(int id);

}
