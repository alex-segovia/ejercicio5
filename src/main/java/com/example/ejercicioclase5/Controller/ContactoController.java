package com.example.ejercicioclase5.Controller;

import com.example.ejercicioclase5.Entity.Cuenta;
import com.example.ejercicioclase5.Entity.Mascota;
import com.example.ejercicioclase5.Entity.Servicio;
import com.example.ejercicioclase5.Repository.CuentaRepository;
import com.example.ejercicioclase5.Repository.MascotaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
public class ContactoController {


    final CuentaRepository cuentaRepository;

    final MascotaRepository mascotaRepository;

    public ContactoController(CuentaRepository cuentaRepository, MascotaRepository mascotaRepository){

        this.cuentaRepository = cuentaRepository;
        this.mascotaRepository = mascotaRepository;
    }

    @GetMapping(value = "/listaContactos")
    public String listaContactos(Model model){
        model.addAttribute("listaContactos",cuentaRepository.findAll());
        return "Contacto/listaContacto";
    }

    @GetMapping("/verMascotasContacto")
    public String listarMascotasContacto(@RequestParam("idCuenta") int idCuenta, Model model) {
        List<Mascota> listaMascotasContacto = mascotaRepository.findByCuenta_Id(idCuenta);
        model.addAttribute("listaMascotasContacto", listaMascotasContacto);

        return "Contacto/mascotasContacto";
    }

    @GetMapping(value = "/editarContacto")
    public String editarContacto(@RequestParam("idCuenta") int idCuenta, Model model) {

        Optional<Cuenta> cuentaOptional = cuentaRepository.findById(idCuenta);
        if (cuentaOptional.isPresent()) {
            Cuenta cuenta = cuentaOptional.get();
            model.addAttribute("cuenta", cuenta);
            List<Mascota> listaMascotasContacto = mascotaRepository.findByCuenta_Id(idCuenta);
            model.addAttribute("listaMascotasContacto", listaMascotasContacto);

            return "Contacto/editarContacto";
        } else {
            return "redirect:/listaContactos";
        }
    }



    @GetMapping(value = "/eliminarMascotaContacto")
    public String eliminarMascotaContacto(@RequestParam("idMascota")int idMascota){

            mascotaRepository.eliminarMascotaContacto(idMascota);

        return "redirect:/listaContactos";
    }

    @PostMapping("/guardarCambiosContacto")
    public String guardarCambiosContacto(Cuenta cuenta) {
        cuentaRepository.actualizarCuenta(cuenta.getDireccion(), cuenta.getPassword(), cuenta.getId());

        return "redirect:/listaContactos";
    }

    @GetMapping("/verCrearContacto")
    public String verCrearContacto() {
        return "Contacto/crearContacto";
    }


    @PostMapping("/crearContacto")
    public String crearContacto( @RequestParam("correo") String correo,
                                 @RequestParam("telefono") String telefono,
                                 @RequestParam("direccion") String direccion,
                                 @RequestParam("password") String password) {

        Cuenta cuenta = new Cuenta();
        cuenta.setDireccion(direccion);
        cuenta.setCorreo(correo);
        cuenta.setPassword(password);
        cuenta.setTelefono(telefono);
        cuentaRepository.save(cuenta);

        return "redirect:/listaContactos";
    }


}
