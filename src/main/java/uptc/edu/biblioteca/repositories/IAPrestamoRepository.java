package uptc.edu.biblioteca.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import uptc.edu.biblioteca.entities.Prestamo;
import uptc.edu.biblioteca.entities.Usuario;

import java.util.List;


public interface IAPrestamoRepository extends JpaRepository<Prestamo, Long> {


    @Query(value = "SELECT p FROM Prestamo  as p WHERE p.usuario = (SELECT u FROM Usuario as u WHERE u.nombre = :nombreUsuario)")
    List<Prestamo> findByUsuarioAndActivoTrue(String nombreUsuario);

//    @Query(value = "")
//    List<Prestamo> findByUsuarioActivo(Usuario usuario);

    // Otros métodos de consulta personalizados si es necesario


}