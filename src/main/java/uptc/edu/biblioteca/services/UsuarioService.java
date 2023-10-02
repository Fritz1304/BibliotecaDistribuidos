package uptc.edu.biblioteca.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uptc.edu.biblioteca.entities.Usuario;
import uptc.edu.biblioteca.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario buscarUsuarioPorNombre(String nombre) {
        return usuarioRepository.findByNombre(nombre);
    }
    // Otros métodos de lógica de negocio relacionados con los usuarios
}