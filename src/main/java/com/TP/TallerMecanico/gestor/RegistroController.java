package com.TP.TallerMecanico.gestor;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.TP.TallerMecanico.entidad.Rol;
import com.TP.TallerMecanico.entidad.Usuario;
import com.TP.TallerMecanico.interfaz.IRol;
import com.TP.TallerMecanico.interfaz.IUsuario;

@Controller
public class RegistroController {

    @Autowired
    private IUsuario usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IRol rolRepository; // Repositorio para acceder a los roles

    // Método para mostrar el formulario de registro
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // nombre de la vista del formulario de registro
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, 
                           @RequestParam String password, 
                           @RequestParam String confirmPassword) {

    // Verificar si las contraseñas coinciden
    if (!password.equals(confirmPassword)) {
        return "redirect:/register?error=PasswordsDoNotMatch"; // Redirigir si no coinciden
    }

    // Verificar si el usuario ya existe
    if (usuarioRepository.findByUsername(username) != null) {
        return "redirect:/register?error=UserAlreadyExists"; // Redirigir si el usuario ya existe
    }

    // Crear un rol (por ejemplo, "ROLE_USER")
    Rol rolUsuario = rolRepository.findByNombre("ROLE_USER");

    // Encriptar la contraseña usando el PasswordEncoder inyectado
    String encryptedPassword = passwordEncoder.encode(password);

    // Crear el nuevo usuario
    Usuario usuario = new Usuario();
    usuario.setUsername(username);
    usuario.setPassword(encryptedPassword); // Guardar la contraseña encriptada
    usuario.setRoles(Collections.singletonList(rolUsuario)); // Asignar rol

    // Guardar el nuevo usuario en la base de datos
    usuarioRepository.save(usuario);

    return "redirect:/login?success=UserRegistered"; // Redirigir al login tras el registro
}

}





