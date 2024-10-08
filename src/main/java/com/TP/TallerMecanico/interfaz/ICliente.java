package com.TP.TallerMecanico.interfaz;
import com.TP.TallerMecanico.entidad.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ICliente extends CrudRepository<Cliente, Long> {

    boolean existsByIdClienteAndEstadoTrue(Long id);

    @Modifying
    @Query("UPDATE Cliente m SET m.estado = false WHERE m.idCliente = :idCliente") //Query para el Soft Delete
    void marcarComoEliminado(@Param("idCliente") Long idCliente);

    List<Cliente> findByEstadoTrue();
    List<Cliente> findByEstadoFalse();

    @Modifying
    @Query("UPDATE Cliente m SET m.estado = true WHERE m.idCliente = :idCliente")
    void marcarComoActivo(@Param("idCliente") Long idCliente);

    Cliente findByDni(String dni);

    Cliente findByIdCliente(Long idCliente);

    Cliente findByDniAndEstadoTrue(String dni);


    @Query("SELECT c FROM Cliente c WHERE c.nombre LIKE :nombre% AND c.estado = true")
    List<Cliente> findByNombreStartingWithAndEstadoTrue(@Param("nombre") String nombre);


    @Query("SELECT c FROM Cliente c WHERE c.dni LIKE :dni% AND c.estado = true")
    List<Cliente> findByDniStartingWithAndEstadoTrue(@Param("dni") String dni);

    @Query("SELECT c FROM Cliente c WHERE c.dni LIKE :dni% AND c.estado = false")
    List<Cliente> findByDniStartingWithAndEstadoFalse(@Param("dni") String dni);


    //List<Cliente> findByNombre(String nombre);

}