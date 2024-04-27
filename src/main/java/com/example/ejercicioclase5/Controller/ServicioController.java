package com.example.ejercicioclase5.Controller;

import com.example.ejercicioclase5.Dto.ServicioResponsableOpcionDto;
import com.example.ejercicioclase5.Entity.*;
import com.example.ejercicioclase5.Repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ServicioController {
    final MascotaRepository mascotaRepository;
    final ServicioRepository servicioRepository;
    final OpcionRepository opcionRepository;

    final ResponsableRepository responsableRepository;
    private final CuentaRepository cuentaRepository;

    public ServicioController(ServicioRepository servicioRepository,MascotaRepository mascotaRepository, OpcionRepository opcionRepository, ResponsableRepository responsableRepository, CuentaRepository cuentaRepository
    ){
        this.servicioRepository = servicioRepository;
        this.mascotaRepository = mascotaRepository;
        this.opcionRepository = opcionRepository;
        this.responsableRepository = responsableRepository;
        this.cuentaRepository = cuentaRepository;

    }

    @GetMapping(value = "/servicios")
    public String detallesServicios(Model model, @RequestParam(value = "id", required = false)int id){
        Optional<Mascota> optionalMascota = mascotaRepository.findById(id);
        if(optionalMascota.isPresent()){
            model.addAttribute("listaServicios",servicioRepository.listarServiciosPorMascota(id));
            model.addAttribute("mascota",optionalMascota.get());
            return "detallesServicios";
        }else{
            return "redirect:/mascotas";
        }
    }

    @GetMapping("/nuevoServicio")
    public String verNuevoServicio(Model model){
        List<Responsable> listaResponsable = responsableRepository.findAll();
        List<Mascota> listaMascota = mascotaRepository.findAll();
        List<Cuenta> listaCuentas = cuentaRepository.findAll();
        model.addAttribute("listaMascota",listaMascota);
        model.addAttribute("listaCuentas",listaCuentas);
        model.addAttribute("listaResponsable",listaResponsable);

        return "/servicios/nuevoServicio";
    }
    @PostMapping("/nuevoServicioPost")
    public String guardarNuevoTransportista(@RequestParam(value = "idMascota") Integer mascota_idmascota,
                                            @RequestParam(value = "idCuenta")Integer cuenta_idcuenta,
                                            @RequestParam(value = "duracion")Integer duracion,
                                            @RequestParam(value = "entrega")String entrega,
                                            @RequestParam(value = "responsable") Integer responsable_idresponsable
    ){

        servicioRepository.nuevoServicio(mascota_idmascota, cuenta_idcuenta, duracion, entrega, responsable_idresponsable);
        return "redirect:/servicios";
    }
    @GetMapping("/listaServicios")
    public String listaServicios(Model model){

        List<ServicioResponsableOpcionDto> listaServiciosResponsablePrecio = servicioRepository.listarServicioResponsableOpcion();
        model.addAttribute("listaServiciosResponsablePrecio", listaServiciosResponsablePrecio);
        return "/servicios/listaServicios";
    }

    @GetMapping("/nuevoServicio")
    public String verNuevoServicio(){
        return "/servicios/nuevoServicio";

    }

    @GetMapping("/editarServicio")
    public String editarServicio(@RequestParam("idServicio") int idServicio,
                                 @RequestParam("idOpcion") int idOpcion,
                                 @RequestParam("idResponsable") int idResponsable,
                                 Model model){

        Opcion opcion = opcionRepository.encontrarOpcionporId(idOpcion);
        List<Responsable> listaResponsable = responsableRepository.findAll();
        Optional<Servicio> optServicio = servicioRepository.encontrarServicioporId(idServicio);

        if(optServicio.isPresent()){
            Servicio servicio = optServicio.get();
            model.addAttribute("servicio", servicio);
            model.addAttribute("opcion", opcion);
            model.addAttribute("listaResponsable", listaResponsable);
            return "/servicios/editarServicio";

        }else {
            return "/servicios/listaServicio";
        }


    }
    @PostMapping("/editarServicioPost")
    public String editarServicioPost(@RequestParam("idServicio") int idServicio,
                                     @RequestParam("idOpcion") int idOpcion,
                                     @RequestParam("idResponsable") int idResponsable,
                                     @RequestParam("precioNuevo") float precioNuevo){

        servicioRepository.actualizarServicio(idServicio, idResponsable);
        opcionRepository.actualizarPrecio(precioNuevo, idOpcion);
        return "redirect:/listaServicios";
    }
    /*
    @PostMapping("/nuevoServicioPost")
    public String nuevoServicio(){

        return "redirect:/";
    }*/

}
