package uptc.edu.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uptc.edu.biblioteca.entities.Usuario;
import uptc.edu.biblioteca.services.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{nombre}")
    public Usuario buscarUsuario(@PathVariable String nombre) {
        return usuarioService.buscarUsuarioPorNombre(nombre);
    }
    // Otros métodos para consultar usuarios y otras operaciones relacionadas
}