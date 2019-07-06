/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controlador;

import com.ufro.IXLodging.DAO.FeedbackDAO;
import com.ufro.IXLodging.modelo.Feedback;
import com.ufro.IXLodging.modelo.Usuario;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nicole
 */
@Controller
public class FeedbackController {

    @Autowired
    private FeedbackDAO fDao;
    
    @GetMapping("/mostrarFeedbacks")
    public String muestraUsuarios (Model model){
        List<Feedback> lista = (List<Feedback>) fDao.findAll();
        
        model.addAttribute("listaFeedback", lista);
        
        return "mostrarFeedbacks";
    }
}
