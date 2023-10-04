package uptc.edu.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.biblioteca.entities.Libro;
import uptc.edu.biblioteca.entities.Prestamo;
import uptc.edu.biblioteca.entities.Usuario;
import uptc.edu.biblioteca.repositories.IAPrestamoRepository;
import uptc.edu.biblioteca.services.LibroService;
import uptc.edu.biblioteca.services.PrestamoService;
import uptc.edu.biblioteca.services.UsuarioService;

import java.util.List;

@RestController
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private PrestamoService prestamoService;
    @Autowired
    private UsuarioService usuarioService;
    @Autowired
    private LibroService libroService;
    @Autowired
    private IAPrestamoRepository IAPrestamoRepository;

//    @GetMapping("/listPrestamos")
//    public List<Prestamo> listPrestamos(){return prestamoService.listPrestamos();}

    @GetMapping("/activos/{nombreUsuario}")
    public List<Prestamo> obtenerPrestamosActivos(@PathVariable String nombreUsuario) {
        return prestamoService.obtenerPrestamosActivosDeUsuario(nombreUsuario);
    }

    /*
    @PostMapping("/TestRealizar")
    public Prestamo crearPrestamo()

     */

    @GetMapping("/todos/{rol}")
    public ResponseEntity<?> listaPrestamo(@PathVariable String rol) {
        if ("admin".equals(rol)) {
            List<Prestamo> prestamos = prestamoService.listarTodosLosPrestamos();

            if (!prestamos.isEmpty()) {
                return ResponseEntity.ok(prestamos);
            }else {
                return ResponseEntity.notFound().build();
            }
        }else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para agregar libros");
        }
    }

    @PostMapping("/realizar/{nombreUsuario}/{tituloLibro}/{rol}")
    public ResponseEntity<String> realizarPrestamo(@PathVariable String nombreUsuario, @PathVariable String tituloLibro, @PathVariable String rol) {
        if ("admin".equals(rol)){

            Usuario usuario = usuarioService.buscarUsuarioPorNombre(nombreUsuario);
            Libro libro = libroService.buscarLibroPorTitulo(tituloLibro);

            if (usuario == null || libro == null) {
                return ResponseEntity.badRequest().body("Usuario o libro no encontrado");
            }

            boolean prestamoExitoso = prestamoService.realizarPrestamo(usuario, libro);

            if (prestamoExitoso) {
                return ResponseEntity.ok("Préstamo realizado con éxito");
            } else {
                return ResponseEntity.badRequest().body("No se pudo realizar el préstamo");
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para agregar libros");
        }
    }


    @PostMapping("/finalizar/{prestamoId}")
    public ResponseEntity<String> finalizarPrestamo(@PathVariable Long prestamoId) {
        Prestamo prestamo = IAPrestamoRepository.findById(prestamoId).orElse(null);

        if (prestamo == null) {
            return ResponseEntity.badRequest().body("Prestamo no encontrado");
        }

        boolean finalizacionExitosa = prestamoService.finalizarPrestamo(prestamo);

        if (finalizacionExitosa) {
            return ResponseEntity.ok("Préstamo finalizado con éxito");
        } else {
            return ResponseEntity.badRequest().body("No se pudo finalizar el préstamo");
        }
    }

}