package uptc.edu.biblioteca.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uptc.edu.biblioteca.entities.Usuario;

public interface IAUsuarioRepository extends JpaRepository<Usuario, Long> {

    @Query(value = "SELECT u FROM Usuario u WHERE u.nombre = :nombre")
    Usuario findByNombre(String nombre);
    // Otros métodos de consulta personalizados si es necesario

    Usuario findByNombreUsuario(String nombreUsuario);


}