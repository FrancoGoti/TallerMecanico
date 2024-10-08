package com.TP.TallerMecanico.servicio;

import java.time.LocalDate;
import java.util.List;
import com.TP.TallerMecanico.entidad.Estado;
import com.TP.TallerMecanico.entidad.Orden;
import com.TP.TallerMecanico.entidad.Tecnico;
import com.TP.TallerMecanico.interfaz.IOrden;
import com.TP.TallerMecanico.interfaz.IEstado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrdenImplementacion implements IOrdenService {

    //A continuacion se instancia una interfaz, gracias al @Autowired de Spring, podemos instanciar clases abstractas e inyectarle
    //los metodos de una clase que implemente esta interfaz

    @Autowired
    private IOrden ordenDao;

    @Autowired
    private IEstado estadoDao;

    //A continuacion todos los metodos de la clase
    @Override
    @Transactional(readOnly = true)
    public List<Orden> listarOrdenesFecha(LocalDate fechaDesde, LocalDate fechaHasta){
        if (fechaDesde==null) {
            return ordenDao.filtrarOrdenPorFechaHasta(fechaHasta);
        }else if (fechaHasta == null) {
            return ordenDao.filtrarOrdenPorFechaDesde(fechaDesde);
        }else{
            return ordenDao.filtrarOrdenPorFechaDesdeYFechaHasta(fechaDesde, fechaHasta);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Orden> listarOrdenesTecnico(Tecnico tecnico){
        return ordenDao.findByTecnicoAndEstadoTrue(tecnico);
    }


    @Override
    @Transactional(readOnly = true)
    //Metodo para listar todos los ordenes activos
    public List<Orden> listarOrdenes() { 
        return ordenDao.findByEstadoTrue(); //Devuelve una lista de ordenes
    }

    @Override
    @Transactional
    public void guardar(Orden orden) {
        orden.setFechaRegistro(LocalDate.now());

        Estado estadoEnProgreso = estadoDao.findByNombre("EN PROGRESO");

        if (estadoEnProgreso == null){
            Estado enProgreso = new Estado();
            enProgreso.setNombre("EN PROGRESO");
            estadoDao.save(enProgreso);
            orden.setEstadoActual(enProgreso);
        } else {
            orden.setEstadoActual(estadoEnProgreso);
        }
        ordenDao.save(orden);
    }

    @Override
    @Transactional
    public void actualizar(Orden orden){
        orden.setFechaRegistro(LocalDate.now());
        ordenDao.save(orden);
    }

    @Override
    @Transactional
    public void eliminar(Orden orden) { //Metodo para eliminar un orden (borrado logico)
        ordenDao.marcarComoEliminado(orden.getIdOrden());
    }

    @Override
    @Transactional(readOnly = true)
    //Metodo para buscar un orden en base a su id
    public Orden buscarOrden(Long orden) {
        return ordenDao.findById(orden).orElse(null);
    }

    @Override
    //Metodo para activar un orden
    public void activarOrden(Orden orden) {

        //Llamamos al metodo marcarComoActivo del ordenDao para cambiar el estado del orden a true
        ordenDao.marcarComoActivo(orden.getIdOrden());
    }

    public void facturarOrden(Orden orden){
        Estado estadoFacturada = estadoDao.findByNombre("FACTURADA");
        if (estadoFacturada == null){
            Estado facturada = new Estado();
            facturada.setNombre("FACTURADA");
            estadoDao.save(facturada);
            orden.setEstadoActual(facturada);
        } else {
            orden.setEstadoActual(estadoFacturada);
        }
        ordenDao.save(orden);
    }

    @Override
    @Transactional   
    public void actualizarKilometraje(Orden orden){
        Long ordenId = orden.getIdOrden();

        Orden ordenViejo = ordenDao.findByIdOrdenAndEstadoTrue(ordenId);

        String kilometrajeNuevo = orden.getKilometros();
        String kilometrajeViejo = ordenViejo.getKilometros();

        if (Integer.parseInt(kilometrajeNuevo)> Integer.parseInt(kilometrajeViejo )) {
            ordenViejo.setKilometros(kilometrajeNuevo);
        }
    }
}