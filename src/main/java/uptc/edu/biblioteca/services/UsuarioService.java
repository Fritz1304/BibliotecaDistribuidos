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

    // Agregar un método para listar todos los usuarios
    public List<Usuario> listarTodosLosUsuarios() {
        return IAUsuarioRepository.findAll();
    }

    // Agregar un método para crear un nuevo usuario
    public Usuario crearUsuario(Usuario nuevoUsuario) {
        // Puedes agregar validaciones adicionales antes de guardar el usuario, si es necesario.
        return IAUsuarioRepository.save(nuevoUsuario);
    }

    public Usuario autenticarUsuario(String nombreUsuario, String password) {
        Usuario usuario = IAUsuarioRepository.findByNombreUsuario(nombreUsuario);

        if (usuario != null && usuario.getPassword().equals(password)) {
            return usuario; // Usuario autenticado
        }
        return null; // Autenticación fallida
    }

}