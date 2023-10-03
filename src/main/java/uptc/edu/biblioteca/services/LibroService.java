package uptc.edu.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uptc.edu.biblioteca.entities.Libro;
import uptc.edu.biblioteca.repositories.IALibroRepository;
import java.util.List;
import java.util.Optional;

@Service
public class LibroService {
    @Autowired
    private IALibroRepository iaLibroRepository;

    public List<Libro> listLibro() {return iaLibroRepository.findAll();}

    public Libro buscarLibroPorTitulo(String titulo) {return iaLibroRepository.findByTitulo(titulo);}

    public List<Libro> buscarLibroPorGenero(String genero) {return iaLibroRepository.findByGenero(genero);}

    public List<Libro> buscarLibroPorAutor(String autor) {return  iaLibroRepository.findByAutor(autor);}

    public List<Libro> buscarLibroPorIdioma(String idioma) {return iaLibroRepository.findByIdioma(idioma);}

    public List<Libro> buscarLibroPorDisponible() {return iaLibroRepository.findByDisponible();}

    public Libro addLibro(Libro libro) {iaLibroRepository.save(libro);
    return libro;}

    public ResponseEntity<Libro> editLibroWithID(Long id, Libro newLibro) {
        Optional<Libro> actualLibroOptional = iaLibroRepository.findById(id);
        if (actualLibroOptional.isPresent()){
            Libro actualLibro = actualLibroOptional.get();

            // Validación de entrada
            if (newLibro != null) {
                // Actualizar propiedades si newGame no es nulo
                if (newLibro.getTitulo() != null) {
                    actualLibro.setTitulo(newLibro.getTitulo());
                }
                if (newLibro.getAutor() != null) {
                    actualLibro.setAutor(newLibro.getAutor());
                }
                if (newLibro.getEditorial() != null) {
                    actualLibro.setEditorial(newLibro.getEditorial());
                }
                if (newLibro.getFechaPublicacion() != null) {
                    actualLibro.setFechaPublicacion(newLibro.getFechaPublicacion());
                }
                if (newLibro.getGenero() != null) {
                    actualLibro.setGenero(newLibro.getGenero());
                }
                if (newLibro.getIdioma() != null) {
                    actualLibro.setIdioma(newLibro.getIdioma());
                }
                if (newLibro.getIsbn() != null) {
                    actualLibro.setIsbn(newLibro.getIsbn());
                }

                try {
                    Libro updatedLibro = iaLibroRepository.save(actualLibro);
                    return ResponseEntity.ok(updatedLibro);
                } catch (Exception e) {
                    // Manejo de error en la actualización
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            } else {
                // newGame es nulo
                return ResponseEntity.badRequest().build();
            }
        } else {

            return ResponseEntity.notFound().build();
        }
    }


    // Otros métodos de lógica de negocio relacionados con los libros
}