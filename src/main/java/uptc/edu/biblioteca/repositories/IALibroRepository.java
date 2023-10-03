package uptc.edu.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uptc.edu.biblioteca.entities.Libro;

import java.util.List;

public interface IALibroRepository extends JpaRepository<Libro, Long> {
    Libro findByTitulo(String titulo);

    @Query(value = "SELECT l FROM Libro l WHERE l.genero = :genero")
    List<Libro> findByGenero(String genero);

    @Query(value = "SELECT l FROM Libro l WHERE l.autor = :autor")
    List<Libro> findByAutor(String autor);

    @Query(value = "SELECT l FROM Libro l WHERE l.idioma = :idioma")
    List<Libro> findByIdioma(String idioma);

    @Query(value = "SELECT l FROM Libro l WHERE l.disponible = true")
    List<Libro> findByDisponible();




    // Otros métodos de consulta personalizados si es necesario
}