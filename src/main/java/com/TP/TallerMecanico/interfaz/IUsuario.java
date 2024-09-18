package com.TP.TallerMecanico.interfaz;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.TP.TallerMecanico.entidad.Usuario;


@Repository
public interface IUsuario extends JpaRepository<Usuario, Long> {
    
    // Defino los metodos vacios con su tipo de datos
    Usuario findByUsername(String username);
    
    
}
