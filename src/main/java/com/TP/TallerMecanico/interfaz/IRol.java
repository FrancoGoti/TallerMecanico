package com.TP.TallerMecanico.interfaz;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.TP.TallerMecanico.entidad.Rol;

@Repository
public interface IRol extends JpaRepository<Rol, Long> {
    Rol findByNombre(String nombre);
}