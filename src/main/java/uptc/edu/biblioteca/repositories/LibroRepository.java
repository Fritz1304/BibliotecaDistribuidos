package uptc.edu.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uptc.edu.biblioteca.entities.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Libro findByTitulo(String titulo);
    // Otros métodos de consulta personalizados si es necesario
}