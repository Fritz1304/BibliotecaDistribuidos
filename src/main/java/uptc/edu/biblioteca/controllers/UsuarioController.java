package uptc.edu.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.biblioteca.entities.Usuario;
import uptc.edu.biblioteca.services.UsuarioService;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/{nombre}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable String nombre) {
        Usuario usuario = usuarioService.buscarUsuarioPorNombre(nombre);

        if (usuario != null) {
            return ResponseEntity.ok(usuario); // Devuelve el usuario si se encontró
        } else {
            return ResponseEntity.notFound().build(); // Devuelve una respuesta 404 si no se encontró el usuario
        }
    }


    @GetMapping("/todos")
    public List<Usuario> listarUsuarios() {
        return usuarioService.listarTodosLosUsuarios();
    }

    // Método para crear un nuevo usuario
    @PostMapping("/crear")
    public ResponseEntity<Usuario> crearUsuario(@RequestBody Usuario nuevoUsuario) {
        Usuario usuarioCreado = usuarioService.crearUsuario(nuevoUsuario);

        if (usuarioCreado != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado); // Devuelve el usuario creado
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Devuelve una respuesta 400 si no se pudo crear el usuario
        }
    }




}