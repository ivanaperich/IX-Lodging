/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ufro.IXLodging.controlador;

import com.ufro.IXLodging.DAO.CredencialDAO;
import com.ufro.IXLodging.modelo.Credencial;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class CredencialController {
    
    
    @Autowired
    CredencialDAO cDao;
    
    @GetMapping("/login")
    public String muestraLogin(
            HttpServletRequest request,
            Model model
    ){
        
        Credencial usuarioLogueado = (Credencial) request.getSession().getAttribute("usuarioLogueado");
        
        if(usuarioLogueado==null){
            model.addAttribute("credencial", new Credencial());
            return "login";
        }else{
            return "index";
        }
    }
    
    @PostMapping("login")
    public String login(
            Model model,  
            @ModelAttribute Credencial c,
            HttpServletRequest request
    ){
        
        Credencial credencialBD = cDao.findByNombreAndPass(c.getNombreUsuario(),c.getContraseña());
        
        if(credencialBD!=null){
            request.getSession().setAttribute("usuarioLogueado", credencialBD);
            return "index";
        }else{
            model.addAttribute("credencial", new Credencial());
            model.addAttribute("error", true);
            model.addAttribute("mensaje_error", "Usuario o Contraseña incorrectos. Intentalo de nuevo");
            return "login";
        } 
        
    }
    
    @PostMapping("logout")
    public String logout(HttpServletRequest request){
        
        request.getSession().invalidate();
        
        return "redirect:/";
    }
}

