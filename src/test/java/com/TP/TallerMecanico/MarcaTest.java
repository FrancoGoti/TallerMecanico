package com.TP.TallerMecanico;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.TP.TallerMecanico.entidad.Marca;
import com.TP.TallerMecanico.interfaz.IMarca;
import com.TP.TallerMecanico.servicio.IMarcaService;
import com.TP.TallerMecanico.servicio.MarcaImplementacion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.Duration;
import java.util.Optional;


public class MarcaTest {

    @Test
    public void testAgregarMarca() throws InterruptedException {
        System.setProperty("webdriver.edge.driver", "C:\\Users\\franc\\Desktop\\Universidad\\Testing de Software\\Driver_Notes\\msedgedriver.exe"); 
        WebDriver driver = new EdgeDriver();

        try {
            // LOGUEO
            driver.get("http://localhost:8080/login");
            Thread.sleep(1500); // Pausa de 2 segundos
            
            WebElement usernameField = driver.findElement(By.name("username"));
            usernameField.sendKeys("franco");
            Thread.sleep(1000); // Pausa de 1 segundo

            WebElement passwordField = driver.findElement(By.id("password"));
            passwordField.sendKeys("gotas");
            Thread.sleep(1000);

            WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
            loginButton.click();
            Thread.sleep(1500); // Pausa de 2 segundos

            // PRUEBA MARCA
            driver.get("http://localhost:8080/marcas");
            Thread.sleep(1500);

            WebElement botonAgregar = driver.findElement(By.linkText("Agregar"));
            botonAgregar.click();
            Thread.sleep(1500);


            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            WebElement marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Ford");
            Thread.sleep(1000);

            WebElement impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);

            WebElement botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(1500);

            System.out.println("Se ejecutó prueba 1: ingresar una nueva marca");

            // PRUEBA 2: Agregar "Chevrolet"
            driver.get("http://localhost:8080/agregarMarca");
            Thread.sleep(1500);

            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Chevrolet");
            Thread.sleep(1000);

            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(1500);

            System.out.println("Se ejecutó prueba 2: ingresar una nueva marca diferente");

            // PRUEBA 3: Intentar agregar "Ford" nuevamente
            driver.get("http://localhost:8080/agregarMarca");
            Thread.sleep(1500);

            marcaInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("nombre")));
            marcaInput.sendKeys("Ford");
            Thread.sleep(1000);

            impuestoInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("impuesto")));
            impuestoInput.sendKeys("1");
            Thread.sleep(1000);

            botonGuardar = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".btn.btn-success")));
            botonGuardar.click();
            Thread.sleep(1500);

            System.out.println("Se ejecutó prueba 3: ingresar una marca ya existente");

        } finally {
           // Cerrar el navegador
           Thread.sleep(3000);
           driver.quit();
        }
    }


    @Mock
    private IMarca marcaRepository;  // Inyección del mock del repositorio

    @InjectMocks
    private MarcaImplementacion marcaService;  // Inyección del servicio que usará el mock

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa los mocks
    }

    @Test
    public void testGuardarMarca() {
        Marca marca = new Marca();
        marca.setIdMarca((long) 20);
        marca.setNombre("Toyota");
        marca.setImpuesto((double) 15.0);
    
        // Simula el comportamiento del repositorio
        when(marcaRepository.save(any(Marca.class))).thenReturn(marca);
    
        // Se llama al método guardar
        marcaService.guardar(marca);
    
        // Simulamos la recuperación de la marca guardada
        when(marcaRepository.findById(20L)).thenReturn(Optional.of(marca));

        // Recupera la marca que se guardó
        Optional<Marca> optionalMarca = marcaRepository.findById(20L);
        Marca nuevaMarca = optionalMarca.orElse(null); // Extrae el objeto Marca del Optional

        // Verifica que los valores sean correctos
        assertNotNull(nuevaMarca); // Verifica que no sea nulo antes de hacer las aserciones
        assertEquals(20L, nuevaMarca.getIdMarca());
        assertEquals("TOYOTA", nuevaMarca.getNombre()); //Se pone el nombre de la marca en mayus porque asi es como se guarda en la base de datos
        assertEquals(15.0, nuevaMarca.getImpuesto(), 0.01);

        // Verifica que el método save fue invocado una vez
        verify(marcaRepository, times(1)).save(any(Marca.class));
    }

}
