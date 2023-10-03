package uptc.edu.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import uptc.edu.biblioteca.entities.Prestamo;
import uptc.edu.biblioteca.entities.Usuario;
import uptc.edu.biblioteca.services.PrestamoService;
import uptc.edu.biblioteca.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PrestamoService prestamoService;

    @PostMapping("/iniciar")
    public ResponseEntity<String> iniciarSesion(@RequestParam String nombreUsuario, @RequestParam String password) {
        Usuario usuarioAutenticado = usuarioService.autenticarUsuario(nombreUsuario, password);

        if (usuarioAutenticado != null) {
            List<Prestamo> prestamosActivos = prestamoService.obtenerPrestamosActivosDeUsuario(usuarioAutenticado);
        } else {
            System.out.println("Autenticación fallida.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Autenticación fallida");
        }
        // Agregar una declaración de retorno al final del método en caso de que no se cumplan las condiciones anteriores
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno"); // Ejemplo de respuesta de error
    }

}