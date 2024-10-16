package com.TP.TallerMecanico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import com.TP.TallerMecanico.entidad.DetalleOrden;
import com.TP.TallerMecanico.entidad.Marca;
import com.TP.TallerMecanico.entidad.Orden;

public class TestOrden {
    @Test
	public void testCalcularTotalSinImpuesto() {
        //Creo array
        List<DetalleOrden> listaDetalles = new ArrayList<>();
		//Creo objetos y asigno valores
		Orden orden = new Orden();
        DetalleOrden detalle1 = new DetalleOrden();
        detalle1.setEstado(true);
        detalle1.setSubtotal(4000);
        DetalleOrden detalle2 = new DetalleOrden();
        detalle2.setEstado(true);
        detalle2.setSubtotal(12000);

        listaDetalles.add(detalle1);
        listaDetalles.add(detalle2);
		
        orden.setDetallesOrden(listaDetalles);
		//Se invoca el metodo para realizar el calculo
		int totalCalculado = Integer.parseInt(orden.calcularTotal().replace("$", "").replace(",", ""));

		//Se comprueba si el resultado obtenido esta bien calculado
		assertEquals(16000, totalCalculado);
	}

    @Test
    public void testCalcularTotalConImpuesto(){

        // creo una marca con un impuesto
        Marca marca = new Marca();
        marca.setIdMarca((long) 22);
        marca.setNombre("Peugeot");
        marca.setImpuesto((double) 3.0);

        //Creo array
        List<DetalleOrden> listaDetalles = new ArrayList<>();
		//Creo objetos y asigno valores
		Orden orden = new Orden();
        DetalleOrden detalle1 = new DetalleOrden();
        detalle1.setEstado(true);
        detalle1.setSubtotal(33000);
        DetalleOrden detalle2 = new DetalleOrden();
        detalle2.setEstado(true);
        detalle2.setSubtotal(22000);

        listaDetalles.add(detalle1);
        listaDetalles.add(detalle2);
		
        orden.setDetallesOrden(listaDetalles);
        
    // Calculo el total limpiando el formato, le quitamos los simbolos $ y , 
    int totalCalculado = Integer.parseInt(orden.calcularTotal().replace("$", "").replace(",", ""));

    // Traigo el impuesto de la marca (en porcentaje, por ejemplo, 3%)
    double impuestoMarca = marca.getImpuesto() / 100.0;

    // Calculo el total con el impuesto
    double totalConImpuesto = totalCalculado + (totalCalculado * impuestoMarca);

    // Comparo con el valor esperado (usamos double en la comparación para mayor precisión)
    assertEquals(56650, (int) totalConImpuesto);  // Convertimos a int para hacer la comparación

    }
}
