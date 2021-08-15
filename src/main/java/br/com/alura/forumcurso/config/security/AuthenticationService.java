package br.com.alura.forumcurso.config.security;

import br.com.alura.forumcurso.modelo.Usuario;
import br.com.alura.forumcurso.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Override
    //Faz a consulta por e-mail. A checagem de senha é feita de forma interna pelo SpringBoot.
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Usuario> usuario = repository.findByEmail(username);

        if(usuario.isPresent()) {
            return usuario.get();
        }
        throw new UsernameNotFoundException("Dados inválidos!!!!");
    }
}
