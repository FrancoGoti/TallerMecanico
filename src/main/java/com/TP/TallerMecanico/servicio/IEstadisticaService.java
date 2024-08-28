package com.TP.TallerMecanico.servicio;
import java.time.LocalDate;
import java.util.Map;

public interface IEstadisticaService {
    Map<String, Map<String, Double>> obtenerEstadisticasIngresosMensuales(int year);
    
    Map<String, Map<String, Double>> obtenerEstadisticasPorServicioEnPeriodo(LocalDate fechaInicio, LocalDate fechaFin, Long tecnicoId);

    Double calcularMontoTotalEnPeriodo(LocalDate fechaInicio, LocalDate fechaFin, Long tecnicoId);

    Map<String, Double> findServicioMasRecaudo(LocalDate fechaInicio, LocalDate fechaFin, Long tecnicoId);
    Map<String, Double> findServicioMasUtilizado(LocalDate fechaInicio, LocalDate fechaFin, Long tecnicoId);
    Map<String, Double> findMesMasRecaudado(int year);
    Integer obtenerCantidadTotalServicios(LocalDate fechaInicio, LocalDate fechaFin, Long tecnicoId);

    //Sin filtros
    Map<String, Map<String, Double>> obtenerEstadisticasPorServicioEnPeriodoSinFiltro(LocalDate fechaInicio, LocalDate fechaFin);
    Double calcularMontoTotalEnPeriodoSinFiltro(LocalDate fechaInicio, LocalDate fechaFin);
    Map<String, Double> findServicioMasRecaudoSinFiltro(LocalDate fechaInicio, LocalDate fechaFin);
    Map<String, Double> findServicioMasUtilizadoSinFiltro(LocalDate fechaInicio, LocalDate fechaFin);
    Integer obtenerCantidadTotalServiciosSinFiltro(LocalDate fechaInicio, LocalDate fechaFin);

    
}
