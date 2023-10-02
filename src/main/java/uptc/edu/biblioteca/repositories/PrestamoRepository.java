package uptc.edu.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uptc.edu.biblioteca.entities.Prestamo;
import uptc.edu.biblioteca.entities.Usuario;

import java.util.List;

public interface PrestamoRepository extends JpaRepository<Prestamo, Long> {
    List<Prestamo> findByUsuarioAndActivoTrue(Usuario usuario);
    // Otros métodos de consulta personalizados si es necesario
}