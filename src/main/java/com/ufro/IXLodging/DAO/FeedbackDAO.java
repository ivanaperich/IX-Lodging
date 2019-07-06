/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.DAO;

import com.ufro.IXLodging.modelo.Feedback;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Nicole
 */
public interface FeedbackDAO extends CrudRepository<Feedback, Integer> {
    
}
