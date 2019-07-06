/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controlador;

import com.ufro.IXLodging.DAO.HospedajeDAO;
import com.ufro.IXLodging.modelo.Hospedaje;
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
public class HospedajeController {
    
    @Autowired
    private HospedajeDAO hDao;
    
//    @GetMapping("/buscarHospedajes")
//    public String buscaHospedajes (Model model){
//              
//        model.addAttribute("listaHospedajes", Hospedaje());
//        
//        return "buscarHospedajes";
//    }

    @GetMapping("/ingresaTuHospedaje")
    public String ingresaHospedajes (Model model){
              
        model.addAttribute("listaHospedajes", new Hospedaje());
        
        return "ingresaHospedaje";
    }
}
 