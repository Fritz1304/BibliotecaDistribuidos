package uptc.edu.biblioteca.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uptc.edu.biblioteca.entities.Libro;
import uptc.edu.biblioteca.services.LibroService;

import java.util.List;

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
    public List<Libro> buscarLibroGenero(@PathVariable String genero) {return libroService.buscarLibroPorGenero(genero);

    }

    @GetMapping("/libroAutor/{autor}")
    public List<Libro> buscarLibroAutor(@PathVariable String autor) {return libroService.buscarLibroPorAutor(autor);

    }

    @GetMapping("/libroIdioma/{idioma}")
    public List<Libro> buscarLibroIdioma(@PathVariable String idioma) {return libroService.buscarLibroPorIdioma(idioma);

    }

    @GetMapping("/libroDisponible")
    public List<Libro> buscarLibroDisponible() {return libroService.buscarLibroPorDisponible();

    }

    @PostMapping("/addLibro/{usuario}")
    public ResponseEntity<?> addLibro(@RequestBody Libro libro, @PathVariable String usuario) {
        if ("admin".equals(usuario)) {
            // Aquí puedes agregar la lógica para guardar el libro en tu servicio correspondiente
            libroService.addLibro(libro);

            // Devuelve una respuesta exitosa
            return ResponseEntity.ok("Libro guardado exitosamente");
        } else {
            // Devuelve un error de falta de permisos con un mensaje personalizado
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para agregar libros");
        }
    }

    @PutMapping("/editLibroWithId/{id}/{usuario}")
    public ResponseEntity<String> editLibroWithId(@PathVariable Long id, @PathVariable String usuario, @RequestBody Libro newLibro) {
        if ("admin".equals(usuario)) {
            // Aquí puedes agregar la lógica para editar el libro en tu servicio correspondiente
            libroService.editLibroWithID(id, newLibro);
            // Devuelve una respuesta exitosa
            return ResponseEntity.ok("Libro editado exitosamente");
        } else {
            // Devuelve un error de falta de permisos con un mensaje personalizado
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No tienes permisos para agregar libros");
        }
    }

    // Otros métodos para consultar libros y otras operaciones relacionadas
}