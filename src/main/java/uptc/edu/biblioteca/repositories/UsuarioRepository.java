package uptc.edu.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import uptc.edu.biblioteca.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Usuario findByNombre(String nombre);
    // Otros métodos de consulta personalizados si es necesario
}