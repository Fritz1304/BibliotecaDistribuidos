package uptc.edu.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uptc.edu.biblioteca.entities.Libro;
import uptc.edu.biblioteca.services.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping("/{titulo}")
    public Libro buscarLibro(@PathVariable String titulo) {
        return libroService.buscarLibroPorTitulo(titulo);
    }
    // Otros métodos para consultar libros y otras operaciones relacionadas
}