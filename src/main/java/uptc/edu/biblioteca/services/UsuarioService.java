package uptc.edu.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uptc.edu.biblioteca.entities.Usuario;
import uptc.edu.biblioteca.repositories.IAUsuarioRepository;
import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private IAUsuarioRepository IAUsuarioRepository;

    public Usuario buscarUsuarioPorNombre(String nombre) {
        return IAUsuarioRepository.findByNombre(nombre);
    }
    // Otros métodos de lógica de negocio relacionados con los usuarios
}