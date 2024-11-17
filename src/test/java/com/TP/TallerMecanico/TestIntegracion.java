package com.TP.TallerMecanico;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.TP.TallerMecanico.entidad.*;
import com.TP.TallerMecanico.interfaz.*;
import com.TP.TallerMecanico.servicio.*;

public class TestIntegracion {

    @Mock private IMarca marcaRepository;  
    @InjectMocks private MarcaImplementacion marcaService;

    @Mock private IModelo modeloRepository;
    @InjectMocks ModeloImplementacion modeloService;

    @Mock private IVehiculo vehiculoRepository;
    @InjectMocks private VehiculoImplementacion vehiculoService;

    @Mock private ICliente clienteDao;
    @InjectMocks private ClienteImplementacion clienteService;
    
    @Mock private ITecnico tecnicoRepository;
    @InjectMocks private TecnicoImplementacion tecnicoService;
    
    @Mock private IOrden ordenRepository;
    @InjectMocks private OrdenImplementacion ordenService;
    
    @Mock private IDetalleOrden detalleOrdenRepository;
    @InjectMocks private DetalleOrdenImplementacion detalleOrdenService;

    @Mock private IEstado estadoDao;
    @InjectMocks EstadoImplementacion estadoService;
    
    @Mock private IServicio servicioRepository;
    @InjectMocks ServicioImplementacion servicioService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); //Se inicializan los mocks antes de su ejecucion
    }

    @Test
    public void testGuardarMarca() {
        Marca marcaPrueba = new Marca();
        marcaPrueba.setIdMarca((long)1);
        marcaPrueba.setNombre("Toyota");
        marcaPrueba.setImpuesto((double) 15.0);
    
        // Simula el comportamiento del repositorio
        when(marcaRepository.save(any(Marca.class))).thenReturn(marcaPrueba);

        // Se llama al método guardar
        marcaService.guardar(marcaPrueba);

        // Verificaciones
        verify(marcaRepository, times(1)).save(any(Marca.class));
        assertEquals("TOYOTA", marcaPrueba.getNombre());
    }

    @Test
    public void testGuardarModelo() {
        //Se crea una nueva marca y se le setean sus atributos
        Marca marcaPrueba = new Marca();
        marcaPrueba.setIdMarca((long)1);
        marcaPrueba.setNombre("Toyota");

        //Se crae un modelo y se le setean sus atributos
        Modelo modeloPrueba = new Modelo();
        modeloPrueba.setIdModelo((long)1);
        modeloPrueba.setNombre("Corolla");
        modeloPrueba.setMarca(marcaPrueba);

        //Repositorio y servicio de marca
        when(marcaRepository.save(any(Marca.class))).thenReturn(marcaPrueba);
        marcaService.guardar(marcaPrueba);

        //Repositorio y servicio de modelo
        when(modeloRepository.save(any(Modelo.class))).thenReturn(modeloPrueba);
        modeloService.guardar(modeloPrueba);

        //Primero se verifica que se haya guardado el modelo 1 vez y luego se comparan los resultados esperados con los obtenidos
        verify(modeloRepository, times(1)).save(any(Modelo.class));
        assertEquals("COROLLA", modeloPrueba.getNombre());
        assertEquals("TOYOTA", marcaPrueba.getNombre());
    }

    @Test
    public void testGuardarCliente() {
        //Se crea un cliente y se le setean sus atributos 
        Cliente clientePrueba = new Cliente();
        clientePrueba.setIdCliente((long)1);
        clientePrueba.setNombre("Lionel");
        clientePrueba.setApellido("Messi");
        clientePrueba.setDni("33016244");

        //Se llaman al repositorio y servicio de cliente para devolver los valores y guardarlos
        when(clienteDao.findByDni(any(String.class))).thenReturn(null);  
        when(clienteDao.findByDniAndEstadoTrue(any(String.class))).thenReturn(null);  
        when(clienteDao.save(any(Cliente.class))).thenReturn(clientePrueba);
        clienteService.guardar(clientePrueba);

        //Se verifica que se haya guardado el cliente una 1 vez y luego se comparan los resultados esperados con los obtenidos
        verify(clienteDao, times(1)).save(any(Cliente.class));
        assertEquals("LIONEL", clientePrueba.getNombre());
        assertEquals("MESSI", clientePrueba.getApellido());
        assertEquals("33016244", clientePrueba.getDni());
    }

    @Test
    public void testGuardarVehiculo() {
        //Se crea un modelo y se le setean sus atributos
        Modelo modeloPrueba = new Modelo();
        modeloPrueba.setIdModelo((long)1);
        modeloPrueba.setNombre("Corolla");

        //Se crea un cliente y se le setean sus atributos
        Cliente clientePrueba = new Cliente();
        clientePrueba.setIdCliente((long)1);
        clientePrueba.setNombre("Lionel");
        clientePrueba.setApellido("Messi");

        //Se crea un vehiculo y se le setean sus atributos 
        Vehiculo vehiculoPrueba = new Vehiculo();
        vehiculoPrueba.setIdVehiculo((long)1);
        vehiculoPrueba.setPatente("aa11bb");
        vehiculoPrueba.setYear("2016");
        vehiculoPrueba.setModelo(modeloPrueba);
        vehiculoPrueba.setCliente(clientePrueba);

        //Repositorio y servicio de modelo
        when(modeloRepository.save(any(Modelo.class))).thenReturn(modeloPrueba);
        modeloService.guardar(modeloPrueba);

        //Repositorio y servicio del cliente
        when(clienteDao.findByDni(any(String.class))).thenReturn(null);  
        when(clienteDao.findByDniAndEstadoTrue(any(String.class))).thenReturn(null);  
        when(clienteDao.save(any(Cliente.class))).thenReturn(clientePrueba);
        clienteService.guardar(clientePrueba);

        //Repositorio y servicio de Vehiculo
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculoPrueba);
        vehiculoService.guardar(vehiculoPrueba);

        //Se verifica que se guarde el vehiculo 1 vez y luego se comparan los resultados esperados con los obtenidos
        verify(vehiculoRepository, times(1)).save(any(Vehiculo.class));
        assertEquals("AA11BB", vehiculoPrueba.getPatente());
        assertEquals("COROLLA", modeloPrueba.getNombre());
        assertEquals("LIONEL", vehiculoPrueba.getCliente().getNombre());
    }

    @Test
    public void testGuardarOrden() {
        //Se crea un tecnico y se le setean sus atributos 
        Tecnico tecnicoPrueba = new Tecnico();
        tecnicoPrueba.setIdTecnico((long)1);
        tecnicoPrueba.setNombre("Albert");
        tecnicoPrueba.setApellido("Fabrega");
        tecnicoPrueba.setLegajo("13560");

        //Se crea un vehiculo y se le setean sus atributos
        Vehiculo vehiculoPrueba = new Vehiculo();
        vehiculoPrueba.setIdVehiculo((long)1);
        vehiculoPrueba.setPatente("aa11bb");

        //Se crea un orden y se le setean sus atributos
        Orden ordenPrueba = new Orden();
        ordenPrueba.setIdOrden((long)1);
        //Se crea una variable fechaDocumento para formatearlo como localDate y poder setearla a la orden
        LocalDate fechaDocumento = LocalDate.of(2024,10,16);
        ordenPrueba.setFechaDocumento(fechaDocumento);
        ordenPrueba.setVehiculo(vehiculoPrueba);
        ordenPrueba.setKilometros("180000");
        ordenPrueba.setTecnico(tecnicoPrueba);

        //Se crea un estado inicial para la orden y se le setean sus atributos
        Estado estadoPrueba = new Estado();
        estadoPrueba.setIdEstado((long)1);
        estadoPrueba.setNombre("EN PROGRESO");
        ordenPrueba.setEstadoActual(estadoPrueba);

        //Repositorio y servicio del tecnico
        when(tecnicoRepository.save(any(Tecnico.class))).thenReturn(tecnicoPrueba);
        tecnicoService.guardar(tecnicoPrueba);


        //Repositorio y servicio de Vehiculo
        when(vehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculoPrueba);
        vehiculoService.guardar(vehiculoPrueba);
        
        //Repositorio y servicio del Estado
        when(estadoDao.save(any(Estado.class))).thenReturn(estadoPrueba);
        estadoService.guardar(estadoPrueba);


        //Repositorio y servicio de la orden
        when(ordenRepository.save(any(Orden.class))).thenReturn(ordenPrueba);
        ordenService.guardar(ordenPrueba);

        //Se verifica que la orden se haya guardado 1 vez y se comparan los resultados esperados con los obtenidos
        verify(ordenRepository, times(1)).save(any(Orden.class));
        assertEquals("EN PROGRESO", ordenPrueba.getEstadoActual().getNombre());
    }

    @Test
    public void testFacturarOrden() {
        //Se crea la orden y se le setean sus atributos
        Orden ordenPrueba = new Orden();
        ordenPrueba.setIdOrden((long)1);
        ordenPrueba.setKilometros("180000");

        // Guardamos la orden
        when(ordenRepository.save(any(Orden.class))).thenReturn(ordenPrueba);
        ordenService.guardar(ordenPrueba);

        // Se crea el Servicio 1: Cambio de Cubiertas y se le setean sus atributos
        Servicio servicioPrueba1 = new Servicio();
        servicioPrueba1.setIdServicio((long)1);
        servicioPrueba1.setNombre("Cambio de Cubiertas");
        servicioPrueba1.setPrecio("1000000");  // Cambia a número si es necesario

        when(servicioRepository.save(any(Servicio.class))).thenReturn(servicioPrueba1);
        servicioService.guardar(servicioPrueba1);

        //Se crea el Servicio 2: Cambio de Aceite y se le setean sus atributos 
        Servicio servicioPrueba2 = new Servicio();
        servicioPrueba2.setIdServicio((long)2);
        servicioPrueba2.setNombre("Cambio de Aceite");
        servicioPrueba2.setPrecio("20000");  // Cambia a número si es necesario

        //Repositorio y servicio del Servicio
        when(servicioRepository.save(any(Servicio.class))).thenReturn(servicioPrueba2);
        servicioService.guardar(servicioPrueba2);

        //Se crean el detalle de orden 1 y se le setean sus atributos
        DetalleOrden detalle1 = new DetalleOrden();
        detalle1.setServicio(servicioPrueba1);
        detalle1.setCantidad("1");  
        detalle1.setSubtotal(1000000);

        //Repositorio y servicio de Detalle de Orden
        when(detalleOrdenRepository.save(any(DetalleOrden.class))).thenReturn(detalle1);
        detalleOrdenService.guardar(detalle1);

        //Se crea el detalle de orden 2 y se le setean sus atributos 
        DetalleOrden detalle2 = new DetalleOrden();
        detalle2.setServicio(servicioPrueba2);
        detalle2.setCantidad("1");  
        detalle2.setSubtotal(20000);

        when(detalleOrdenRepository.save(any(DetalleOrden.class))).thenReturn(detalle2);
        detalleOrdenService.guardar(detalle2);

        //Se crea una Lista de detalles para asignarla a la orden creada con anterioridad
        List<DetalleOrden> listaDetallesOrden = new ArrayList<>();
        listaDetallesOrden.add(detalle1);
        listaDetallesOrden.add(detalle2);
        ordenPrueba.setDetallesOrden(listaDetallesOrden);

        //Se crea el estado Completada
        Estado estadoFinal = new Estado();
        estadoFinal.setNombre("COMPLETADA");

        //Repositorio y servicio del estado
        when(estadoDao.save(any(Estado.class))).thenReturn(estadoFinal);
        estadoService.guardar(estadoFinal);

        //Se setea el estado Completada a la orden para luego poder ejecutar el metodo de facturacion
        ordenPrueba.setEstadoActual(estadoFinal);
        
        //Se ejecuta el metodo facturar orden el cual deberia cambiar el estado de la orden de "Completada" a "Facturada"
        ordenService.facturarOrden(ordenPrueba);
        
        // Verificaciones
        assertEquals(2, ordenPrueba.getDetallesOrden().size());
        assertEquals("FACTURADA", ordenPrueba.getEstadoActual().getNombre());
    }

}
