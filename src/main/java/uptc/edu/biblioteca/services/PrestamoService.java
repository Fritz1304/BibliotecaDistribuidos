package uptc.edu.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uptc.edu.biblioteca.entities.Libro;
import uptc.edu.biblioteca.entities.Prestamo;
import uptc.edu.biblioteca.entities.Usuario;
import uptc.edu.biblioteca.repositories.LibroRepository;
import uptc.edu.biblioteca.repositories.PrestamoRepository;

import java.util.List;

@Service
public class PrestamoService {
    @Autowired
    private PrestamoRepository prestamoRepository;
    @Autowired
    private LibroRepository libroRepository;

    public List<Prestamo> obtenerPrestamosActivosDeUsuario(Usuario usuario) {
        // Buscar todos los préstamos activos del usuario
        return prestamoRepository.findByUsuarioAndActivoTrue(usuario);
    }
    public boolean realizarPrestamo(Usuario usuario, Libro libro) {
        // Verificar si el usuario tiene préstamos activos
        List<Prestamo> prestamosActivos = prestamoRepository.findByUsuarioAndActivoTrue(usuario);
        if (!prestamosActivos.isEmpty()) {
            return false; // El usuario ya tiene préstamos activos, no se puede prestar otro libro.
        }

        // Verificar si el libro está disponible
        if (!libro.isDisponible()) {
            return false; // El libro no está disponible para préstamo.
        }

        // Crear un nuevo préstamo
        Prestamo prestamo = new Prestamo();
        prestamo.setUsuario(usuario);
        prestamo.setLibro(libro);
        prestamo.setActivo(true);

        // Marcar el libro como no disponible
        libro.setDisponible(false);

        // Guardar el préstamo y actualizar el estado del libro en la base de datos
        prestamoRepository.save(prestamo);
        libroRepository.save(libro);

        return true; // El préstamo se realizó con éxito.
    }

    public boolean finalizarPrestamo(Prestamo prestamo) {
        // Verificar si el préstamo es activo
        if (!prestamo.isActivo()) {
            return false; // El préstamo ya está inactivo, no se puede finalizar nuevamente.
        }

        // Marcar el préstamo como inactivo
        prestamo.setActivo(false);

        // Obtener el libro asociado al préstamo
        Libro libro = prestamo.getLibro();

        // Marcar el libro como disponible
        libro.setDisponible(true);

        // Actualizar el préstamo y el estado del libro en la base de datos
        prestamoRepository.save(prestamo);
        libroRepository.save(libro);

        return true; // La finalización del préstamo se realizó con éxito.
    }
    // Otros métodos de lógica de negocio relacionados con los préstamos
}