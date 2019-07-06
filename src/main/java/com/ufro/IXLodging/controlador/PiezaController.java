/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controlador;

import com.ufro.IXLodging.DAO.HospedajeDAO;
import com.ufro.IXLodging.DAO.PiezaDAO;
import com.ufro.IXLodging.DAO.UsuarioDAO;
import com.ufro.IXLodging.modelo.Credencial;
import com.ufro.IXLodging.modelo.Pieza;
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
    public String verRecetasPaciente(
            HttpServletRequest request,
            @PathVariable("idHospedaje") int idHospedaje,
            Model model
    ) {

        Credencial c = (Credencial) request.getSession().getAttribute("medicoLogueado");

        int idUsuario = c.getIdCredencial();
        List<Pieza> piezas = pDao.findByIdUsuario_IdAndIdHospedaje_Id(idUsuario, idHospedaje);
        model.addAttribute("piezas", piezas);
        return "verPiezas";
    }
}

@GetMapping("recetas/emitidas")
        public String verRecetasEmitidas(
        HttpServletRequest request,
        Model model
    ){
        
        Credencial c = (Credencial) request.getSession().getAttribute("medicoLogueado");
        
        if(c ==null){
            
            return "redirect:/login";
        }else{
            int idMedico = c.getIdMedico().getId();
            List<Receta> recetas = rDao.findByIdMedico_Id(idMedico);
            model.addAttribute("recetas", recetas);
            return "verRecetas";
        }
    }
    
    @GetMapping("recetas/{idReceta}/ver")
        public String verReceta(
        Model model,
        @PathVariable("idReceta") int idReceta
    ){
        
        
        Receta r = rDao.findById(idReceta);
        model.addAttribute("receta", r);
        
        return "receta";
    }
    
    
    @GetMapping("pacientes/{idPaciente}/recetas/nueva")
        public String muestraFormReceta(
        @PathVariable("idPaciente") int idPaciente,
        Model model,
        HttpServletRequest request
    ){
        
        Credencial c = (Credencial) request.getSession().getAttribute("medicoLogueado");
        
        if(c==null){
            return "redirect:/login";
        }else{
            int idMedico = c.getIdMedico().getId();

            Paciente p = pDao.findById(idPaciente);
            Medico m = mDao.findById(idMedico);

            Receta r = new Receta();
            r.setIdPaciente(p);
            r.setIdMedico(m);

            for (int i = 1; i <= 5; i++) {
                r.getItemRecetaList().add(new ItemReceta());
            }

            model.addAttribute("receta", new Receta());
            return "nuevaReceta";
        }
        
    }
    
    
    @PostMapping("pacientes/{idPaciente}/recetas/nueva")
        public String muestraFormReceta(
        Model model,
        @PathVariable("idPaciente") int idPaciente,
        @ModelAttribute Receta r,
        HttpServletRequest request
    ){
        
        Credencial c = (Credencial) request.getSession().getAttribute("medicoLogueado");
        int idMedico = c.getIdMedico().getId();
        if(c ==null){
            
            return "redirect:/login";
        }else{
            
            Paciente p = pDao.findById(idPaciente);
            Medico m = mDao.findById(idMedico);
            
            r.setIdMedico(m);
            r.setIdPaciente(p);
            r.setFecha(new Date());
            
            rDao.save(r);
            
            return "redirect:/pacientes/"+idPaciente+"/recetas";
        }
        
    }
    
    
    @PostMapping("recetas/{idReceta}/eliminar")
        public String eliminarReceta(
    
        @PathVariable("idReceta") int idReceta
    ){
        
        Receta r = rDao.findById(idReceta);
        
        int idPaciente=r.getIdPaciente().getId();
        
        rDao.delete(r);
        
        return "redirect:/pacientes/"+idPaciente+"/recetas";
    }
    
    
    
}
