package com.example.ejercicioclase5.Controller;

import com.example.ejercicioclase5.Entity.RazaEspecie;
import com.example.ejercicioclase5.Entity.Responsable;
import com.example.ejercicioclase5.Repository.ResponsableRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class ResponsablesController {
    final ResponsableRepository responsableRepository;
    public ResponsablesController(ResponsableRepository responsableRepository){
         this.responsableRepository = responsableRepository ;
    }
    @GetMapping("/listaResponsables")
    public String listarResponsables(Model model){
        List<Responsable> listaResponsables = responsableRepository.findAll();
        model.addAttribute("lista", listaResponsables);
        return "Responsable/listaResponsables";
    }

    @GetMapping("/verNuevoResponsable")
    public String nuevoResponsables(){
        return "Responsable/nuevoResponsable";
    }

    @PostMapping("/crearResponsable")
    public String crearResponsables(Responsable res){
        responsableRepository.save(res);
        return "redirect:/listaResponsables";
    }

}
