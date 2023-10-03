package uptc.edu.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
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

    @PostMapping("/realizar/{nombreUsuario}/{tituloLibro}")
    public ResponseEntity<String> realizarPrestamo(@PathVariable String nombreUsuario, @PathVariable String tituloLibro) {
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
    }

/*
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

 */
}