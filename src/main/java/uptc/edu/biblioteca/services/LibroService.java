package uptc.edu.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uptc.edu.biblioteca.entities.Libro;
import uptc.edu.biblioteca.repositories.LibroRepository;

@Service
public class LibroService {
    @Autowired
    private LibroRepository libroRepository;

    public Libro buscarLibroPorTitulo(String titulo) {
        return libroRepository.findByTitulo(titulo);
    }
    // Otros métodos de lógica de negocio relacionados con los libros
}