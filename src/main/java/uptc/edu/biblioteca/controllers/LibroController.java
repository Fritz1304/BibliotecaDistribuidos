package uptc.edu.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.biblioteca.entities.Libro;
import uptc.edu.biblioteca.services.LibroService;

@RestController
@RequestMapping("/libros")
public class LibroController {
    @Autowired
    private LibroService libroService;

    @GetMapping("/listLibros")
    public List<Libro> listLibro(){return libroService.listLibro();}

    @GetMapping("/libroTitulo/{titulo}")
    public Libro buscarLibro(@PathVariable String titulo) {
        return libroService.buscarLibroPorTitulo(titulo);
    }

    @GetMapping("/libroGenero/{genero}")
    public List<Libro> buscarLibroGenero(@PathVariable String genero) {return libroService.buscarLibroPorGenero(genero);}

    @GetMapping("/libroAutor/{autor}")
    public List<Libro> buscarLibroAutor(@PathVariable String autor) {return libroService.buscarLibroPorAutor(autor);}

    @GetMapping("/libroIdioma/{idioma}")
    public List<Libro> buscarLibroIdioma(@PathVariable String idioma) {return libroService.buscarLibroPorIdioma(idioma);}

    @GetMapping("/libroDisponible")
    public List<Libro> buscarLibroDisponible() {return libroService.buscarLibroPorDisponible();}

    @GetMapping("/addLibro")
    public Libro addLibro(@RequestBody Libro libro){return libroService.addLibro(libro);}

    @GetMapping("/editLibroWithId/{id}")
    public ResponseEntity<Libro> editLibroWithId(@PathVariable Long id, @RequestBody Libro newLibro) {return libroService.editLibroWithID(id, newLibro);}

    // Otros métodos para consultar libros y otras operaciones relacionadas
}