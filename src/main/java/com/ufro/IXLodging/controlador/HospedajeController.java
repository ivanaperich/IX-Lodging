/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controlador;

import com.ufro.IXLodging.DAO.HospedajeDAO;
import com.ufro.IXLodging.DAO.UsuarioDAO;
import com.ufro.IXLodging.modelo.Credencial;
import com.ufro.IXLodging.modelo.Hospedaje;
import com.ufro.IXLodging.modelo.Usuario;
import java.util.List;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Nicole
 */
@Controller
public class HospedajeController {

    @Autowired
    private HospedajeDAO hDao;
    @Autowired
    UsuarioDAO uDao;

//    @GetMapping("/buscarHospedajes")
//    public String buscaHospedajes (Model model){
//              
//        model.addAttribute("listaHospedajes", Hospedaje());
//        
//        return "buscarHospedajes";
//    }
    @GetMapping("/ingresaTuHospedaje")
    public String ingresaHospedajes(Model model) {

        model.addAttribute("listaHospedajes", new Hospedaje());

        return "ingresaHospedaje";
    }

    @GetMapping("hospedajes/ver")
    public String muestraHospedajes(
            Model model,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        int idUsuario = c.getIdCredencial();
        Usuario u = uDao.findById(idUsuario);

        List<Hospedaje> hospedajes = u.getHospedajeList();
        model.addAttribute("hospedajes", hospedajes);

        return "verHospedajes";
    }

    @GetMapping("hospedajes/nuevo")
    public String mostrarForm(
            Model model,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        model.addAttribute("hospedaje", new Hospedaje());

        return "nuevoHospedaje";
    }

    @PostMapping("hospedajes/nuevo")
    public String nuevoHospedaje(
            @ModelAttribute Hospedaje h,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        int idUsuario = c.getIdCredencial();
        Usuario u = uDao.findById(idUsuario);

        u.getHospedajeList().add(h);
        hDao.save(h);
        uDao.save(u);

        return "redirect:/hospedajes/ver";
    }

}

