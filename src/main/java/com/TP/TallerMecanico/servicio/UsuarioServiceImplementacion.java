package com.TP.TallerMecanico.servicio;

import java.util.Collection;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.TP.TallerMecanico.entidad.Rol;
import com.TP.TallerMecanico.entidad.Usuario;
import com.TP.TallerMecanico.interfaz.IUsuario;


@Service
public class UsuarioServiceImplementacion implements UserDetailsService {

    @Autowired
    private IUsuario usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Busca el usuario en la base de datos por nombre de usuario
        Usuario user = usuarioRepository.findByUsername(username);

        // Verifica si el usuario existe, si no, lanza una excepción
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado con el nombre de usuario: " + username);
        }

        // Devuelve el objeto UserDetails de Spring Security con el username, password y los roles convertidos
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Rol> roles) {
        return roles.stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());
    }
}

