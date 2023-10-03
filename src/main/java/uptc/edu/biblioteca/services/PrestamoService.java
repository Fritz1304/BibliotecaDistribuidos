package uptc.edu.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uptc.edu.biblioteca.entities.Libro;
import uptc.edu.biblioteca.entities.Prestamo;
import uptc.edu.biblioteca.entities.Usuario;
import uptc.edu.biblioteca.repositories.IALibroRepository;
import uptc.edu.biblioteca.repositories.IAPrestamoRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PrestamoService {
    @Autowired
    private IAPrestamoRepository IAPrestamoRepository;
    @Autowired
    private IALibroRepository IALibroRepository;

    public List<Prestamo> obtenerPrestamosActivosDeUsuario(String nombreUsuario) {
        // Buscar todos los préstamos activos del usuario
        return IAPrestamoRepository.findByUsuarioAndActivoTrue(nombreUsuario);
    }

    public boolean realizarPrestamo(Usuario usuario, Libro libro) {
        // Verificar si el usuario tiene préstamos activos
        List<Prestamo> prestamosActivos = IAPrestamoRepository.findByUsuarioAndActivoTrue(usuario);
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

        //Definit Fechas
        Date fechaActual = new Date();
        prestamo.setFechaPrestamo(fechaActual);

        // Marcar el libro como no disponible
        libro.setDisponible(false);

        // Guardar el préstamo y actualizar el estado del libro en la base de datos
        IAPrestamoRepository.save(prestamo);
        IALibroRepository.save(libro);

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
        IAPrestamoRepository.save(prestamo);
        IALibroRepository.save(libro);

        return true; // La finalización del préstamo se realizó con éxito.
    }
    // Otros métodos de lógica de negocio relacionados con los préstamos

     */
}