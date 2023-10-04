package uptc.edu.biblioteca.controllers;

import ch.qos.logback.core.net.SyslogOutputStream;
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

    @GetMapping("/{nombre}/{rol}")
    public ResponseEntity<?> buscarUsuario(@PathVariable String nombre, @PathVariable String rol) {
        if ("admin".equals(rol)){
            Usuario usuario = usuarioService.buscarUsuarioPorNombre(nombre);

            if (usuario != null) {
                return ResponseEntity.ok(usuario); // Devuelve el usuario si se encontró
            } else {
                return ResponseEntity.notFound().build(); // Devuelve una respuesta 404 si no se encontró el usuario
            }
        }else {
            // Devuelve un error de falta de permisos con un mensaje personalizado
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para agregar libros");
        }

    }

/*
    @GetMapping("/todos/{rol}")
    public ResponseEntity<?> listarUsuarios(@PathVariable String rol) {
        if ("admin".equals(rol)) {
            return usuarioService.listarTodosLosUsuarios();
        }else {
            // Devuelve un error de falta de permisos con un mensaje personalizado
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para agregar libros");
        }

    }

 */

    @GetMapping("/todos/{rol}")
    public ResponseEntity<?> listarUsuarios(@PathVariable String rol) {
        if ("admin".equals(rol)) {
            List<Usuario> usuarios = usuarioService.listarTodosLosUsuarios();

            if (!usuarios.isEmpty()) {
                return ResponseEntity.ok(usuarios); // Devuelve la lista de usuarios si se encontraron usuarios
            } else {
                return ResponseEntity.notFound().build(); // Devuelve una respuesta 404 si no se encontraron usuarios
            }
        } else {
            // Devuelve un mensaje de error si el rol no es "admin"
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para listar usuarios");
        }
    }

    // Método para crear un nuevo usuario
    @PostMapping("/crear/{rol}")
    public ResponseEntity<?> crearUsuario(@PathVariable String rol, @RequestBody Usuario nuevoUsuario) {

        if ("admin".equals(rol))    {

            System.out.println("es admin");
            Usuario usuarioCreado = usuarioService.crearUsuario(nuevoUsuario);

            if (usuarioCreado != null) {
                System.out.println("Usuario Creado no es nulo");
                return ResponseEntity.status(HttpStatus.CREATED).body(usuarioCreado); // Devuelve el usuario creado
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Devuelve una respuesta 400 si no se pudo crear el usuario
            }
        }else {
            // Devuelve un error de falta de permisos con un mensaje personalizado
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para agregar libros");
        }
    }




}