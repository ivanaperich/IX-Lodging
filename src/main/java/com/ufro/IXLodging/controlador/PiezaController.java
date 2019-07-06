/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controlador;

import com.ufro.IXLodging.DAO.HospedajeDAO;
import com.ufro.IXLodging.DAO.PiezaDAO;
import com.ufro.IXLodging.DAO.UsuarioDAO;
import com.ufro.IXLodging.modelo.Cama;
import com.ufro.IXLodging.modelo.Credencial;
import com.ufro.IXLodging.modelo.Hospedaje;
import com.ufro.IXLodging.modelo.Pieza;
import com.ufro.IXLodging.modelo.Usuario;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author usuario
 */
public class PiezaController {

    @Autowired
    PiezaDAO pDao;
    @Autowired
    UsuarioDAO uDao;
    @Autowired
    HospedajeDAO hDao;

    @GetMapping("hospedajes/{idHospedaje}/piezas")
    public String verPiezasHospedaje(
            HttpServletRequest request,
            @PathVariable("idHospedaje") int idHospedaje,
            Model model
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        int idUsuario = c.getIdCredencial();
        List<Pieza> piezas = pDao.findByIdUsuario_IdAndIdHospedaje_Id(idUsuario, idHospedaje);
        model.addAttribute("piezas", piezas);
        return "verPiezas";
    }

    @GetMapping("piezas/{idPieza}/ver")
    public String verPieza(
            Model model,
            @PathVariable("idPieza") int idPieza
    ) {

        Pieza p = pDao.findById(idPieza);
        model.addAttribute("pieza", p);

        return "pieza";
    }

    @GetMapping("hospedajes/{idHospedaje}/piezas/nueva")
    public String muestraFormPieza(
            @PathVariable("idHospedaje") int idHospedaje,
            Model model,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");

        int idUsuario = c.getIdCredencial();

        Hospedaje h = hDao.findById(idHospedaje);
        Usuario u = uDao.findById(idUsuario);

        Pieza p = new Pieza();
        p.setIdHospedaje(h);
        p.setIdUsuario(u);

        p.getCamaList().add(new Cama());

        model.addAttribute("pieza", new Pieza());
        return "nuevaPieza";
    }

    @PostMapping("hospedajes/{idHospedaje}/piezas/nueva")
    public String muestraFormPieza(
            Model model,
            @PathVariable("idHospedaje") int idHospedaje,
            @ModelAttribute Pieza p,
            HttpServletRequest request
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("usuarioLogueado");
        int idUsuario = c.getIdCredencial();

        Hospedaje h = hDao.findById(idHospedaje);
        Usuario u = uDao.findById(idUsuario);

        p.setIdUsuario(u);
        p.setIdHospedaje(h);
        p.setFecha(new Date());

        pDao.save(p);

        return "redirect:/hospedajes/" + idHospedaje + "/piezas";
    }

}
