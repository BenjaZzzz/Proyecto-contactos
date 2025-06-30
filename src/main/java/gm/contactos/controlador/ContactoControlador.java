package gm.contactos.controlador;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import gm.contactos.modelo.Contacto;
import gm.contactos.servicio.ContactoServicio;

@Controller
public class ContactoControlador {

    private static final Logger logger = LoggerFactory.getLogger(ContactoControlador.class);

    @Autowired
    ContactoServicio contactoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo,
                          @ModelAttribute("mensaje") String mensaje,
                          @ModelAttribute("tipoMensaje") String tipoMensaje) {
        List<Contacto> contactos = contactoServicio.listarContactos();
        modelo.put("contactos", contactos);
        modelo.put("contacto", new Contacto());

        if (mensaje != null && !mensaje.isEmpty()) {
            modelo.put("mensaje", mensaje);
            modelo.put("tipoMensaje", tipoMensaje);
        }

        return "index";
    }

    @PostMapping("/guardar")
    public String guardarContacto(@ModelAttribute Contacto contacto, RedirectAttributes redirectAttrs) {
        contactoServicio.guardarContacto(contacto);

    // Puedes validar si es nuevo o edición
    if (contacto.getIdContacto() != null) {
        redirectAttrs.addFlashAttribute("mensaje", "Contacto actualizado correctamente");
    } else {
        redirectAttrs.addFlashAttribute("mensaje", "Contacto agregado correctamente");
    }
    redirectAttrs.addFlashAttribute("tipoMensaje", "success");

        return "redirect:/";
    }


    @GetMapping("/editar/{id}")
    public String editarContacto(@PathVariable("id") Integer id, ModelMap modelo) {
        Contacto contacto = contactoServicio.buscarContactoPorId(id);
        modelo.put("contacto", contacto);
        modelo.put("contactos", contactoServicio.listarContactos());
        return "index";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminarContacto(@PathVariable("id") Integer id,RedirectAttributes redirectAttrs) {
        Contacto contacto = contactoServicio.buscarContactoPorId(id);
        if (contacto != null) {
        contactoServicio.eliminarContacto(contacto);
        redirectAttrs.addFlashAttribute("mensaje", "Contacto eliminado correctamente");
        redirectAttrs.addFlashAttribute("tipoMensaje", "success");
    } else {
        redirectAttrs.addFlashAttribute("mensaje", "Contacto no encontrado");
        redirectAttrs.addFlashAttribute("tipoMensaje", "error");
    }
        return "redirect:/";
    }
}
