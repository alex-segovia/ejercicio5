package com.example.ejercicioclase5.Controller;

import com.example.ejercicioclase5.Entity.Cuenta;
import com.example.ejercicioclase5.Entity.Mascota;
import com.example.ejercicioclase5.Entity.RazaEspecie;
import com.example.ejercicioclase5.Entity.Servicio;
import com.example.ejercicioclase5.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class MascotaController {
    final MascotaRepository mascotaRepository;
    final ServicioRepository servicioRepository;
    final OpcionServicioRepository opcionServicioRepository;
    final RazaEspecieRepository razaEspecieRepository;
    final CuentaRepository cuentaRepository;
    final OpcionRepository opcionRepository;

    public MascotaController(MascotaRepository mascotaRepository, ServicioRepository servicioRepository, OpcionServicioRepository opcionServicioRepository, RazaEspecieRepository razaEspecieRepository, CuentaRepository cuentaRepository, OpcionRepository opcionRepository){
        this.mascotaRepository = mascotaRepository;
        this.servicioRepository = servicioRepository;
        this.opcionServicioRepository = opcionServicioRepository;
        this.razaEspecieRepository = razaEspecieRepository;
        this.cuentaRepository = cuentaRepository;
        this.opcionRepository = opcionRepository;
    }

    @GetMapping(value = "/mascotas")
    public String vistaMascotas(Model model){
        model.addAttribute("listaMascotas",mascotaRepository.obtenerMascotasServicios());
        return "mascotas";
    }

    @PostMapping(value = "/mascotas/buscar")
    public String buscarMascotas(Model model, @RequestParam(value = "busqueda")String busqueda){
        model.addAttribute("listaMascotas",mascotaRepository.obtenerMascotasServiciosBusqueda('%'+busqueda+'%'));
        model.addAttribute("textoBuscado",busqueda);
        return "mascotas";
    }

    @GetMapping(value = "/mascotas/borrar")
    public String borrarMascota(@RequestParam(value = "id")int id){
        Optional<Mascota> optionalMascota = mascotaRepository.findById(id);
        if(optionalMascota.isPresent()){
            List<Servicio> listaServicios = servicioRepository.servicioPorIdMascota(id);
            if(listaServicios!=null){
                for(Servicio s : listaServicios){
                    opcionServicioRepository.borrarPorIdServicio(s.getId());
                }
                servicioRepository.borrarPorIdMascota(id);
            }
            mascotaRepository.deleteById(id);
        }
        return "redirect:/mascotas";
    }

    @GetMapping(value = "/mascotas/editar")
    public String editarMascota(Model model, @RequestParam(value = "id")int id){
        Optional<Mascota> optionalMascota = mascotaRepository.findById(id);
        if(optionalMascota.isPresent()){
            model.addAttribute("mascota",optionalMascota.get());
            List<RazaEspecie> listaRazaEspecie = razaEspecieRepository.findAll();
            model.addAttribute("listaRazas",listaRazaEspecie);
            return "editarMascota";
        }else{
            return "redirect:/mascotas";
        }
    }

    @PostMapping(value = "/mascotas/guardar")
    public String guardarCambios(@RequestParam(value = "id")int id,
                                 @RequestParam(value = "nombre")String nombre,
                                 @RequestParam(value = "anho")String anho,
                                 @RequestParam(value = "sexo")String sexo,
                                 @RequestParam(value = "razaEspecie")String razaEspecie,
                                 @RequestParam(value = "razaOtros")String razaOtros){
        Optional<Mascota> optionalMascota = mascotaRepository.findById(id);
        if(optionalMascota.isPresent()){
            if(razaOtros.isEmpty()){
                mascotaRepository.editarMascota(nombre,anho,sexo,razaEspecie,null,id);
            }else{
                mascotaRepository.editarMascota(nombre,anho,sexo,razaEspecie,razaOtros,id);
            }

        }
        return "redirect:/mascotas";
    }

    @GetMapping(value = "/mascotas/nueva")
    public String nuevaMascota(Model model) {
        List<RazaEspecie> listaRazaEspecie = razaEspecieRepository.findAll();
        List<Cuenta> listaCuentas = cuentaRepository.findAll();
        model.addAttribute("listaRazas",listaRazaEspecie);
        model.addAttribute("listaCuentas",listaCuentas);
        return "registrarMascota";
    }

    @PostMapping(value = "/mascotas/registrar")
    public String guardarCambios(@RequestParam(value = "nombre")String nombre,
                                 @RequestParam(value = "anho")String anho,
                                 @RequestParam(value = "historia")String historia,
                                 @RequestParam(value = "observaciones")String observaciones,
                                 @RequestParam(value = "sexo")String sexo,
                                 @RequestParam(value = "razaEspecie")String razaEspecie,
                                 @RequestParam(value = "razaOtros")String razaOtros,
                                 @RequestParam(value = "cuenta")String cuenta){

        if(razaOtros.isEmpty()){
            if(cuenta.equals("0")){
                mascotaRepository.registrarMascota(nombre,anho,historia,observaciones,sexo,razaEspecie,null,null);
            }else{
                mascotaRepository.registrarMascota(nombre,anho,historia,observaciones,sexo,razaEspecie,null,cuenta);
            }
        }else{
            if(cuenta.equals("0")){
                mascotaRepository.registrarMascota(nombre,anho,historia,observaciones,sexo,razaEspecie,razaOtros,null);
            }else{
                mascotaRepository.registrarMascota(nombre,anho,historia,observaciones,sexo,razaEspecie,razaOtros,cuenta);
            }
        }
        return "redirect:/mascotas";
    }
}
